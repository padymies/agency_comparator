package tdt.ui.albaranes;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tdt.db.dao.IAgenciaDao;
import tdt.db.dao.IZonaDao;
import tdt.db.daoImpl.AgenciaImpl;
import tdt.db.daoImpl.ZonaImpl;
import tdt.model.Albaran;
import tdt.model.Zona;
import tdt.services.AlbaranService;
import tdt.services.AlertExceptionService;
import tdt.services.AlertService;
import tdt.services.ComparatorService;
import tdt.services.ConfigStage;
import tdt.services.FileService;
import tdt.services.ValidatorService;
import tdt.ui.albaranes.form.AlbaranFormController;
import tdt.ui.salidaComparacion.SalidaController;

public class AlbaranesController implements Initializable {

    @FXML
    private ListView<Albaran> listView;

    @FXML
    private Button btnComparar;

    @FXML
    private TextField txtBuscar;

    private IAgenciaDao agenciaDao;

    private IZonaDao zonaDao;

    private ObservableList<String> nombreAgencias;

    private ObservableList<String> nombreZonas;

    private FilteredList<Albaran> filteredList;
    @FXML
    private Label lbFiltro;
    @FXML
    private CheckBox chkSelectAll;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image icon = new Image("file:resources/img/" + "filter.png");

        ImageView imgV = new ImageView(icon);

        imgV.setFitHeight(16);

        imgV.setFitWidth(16);

        lbFiltro.setGraphic(imgV);

        ConfigStage.setIcon(btnComparar, "analisis.png", 26);

        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        agenciaDao = new AgenciaImpl();

        nombreAgencias = agenciaDao.obtenerNombresAgencias();

        zonaDao = new ZonaImpl();

        nombreZonas = zonaDao.obtenerNombresZonas();

        txtBuscar.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.ENTER) {

                buscarAlbaran();
            }
        });

        chkSelectAll.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    listView.getSelectionModel().selectAll();
                } else {
                    listView.getSelectionModel().clearSelection();
                }
            }
        });

    }

    private void buscarAlbaran() {

        String busqueda = txtBuscar.getText();

        filteredList.setPredicate(data -> {

            if (busqueda == null || busqueda.isEmpty()) {
                return true;
            }

            String lowerCaseBusqueda = busqueda.toLowerCase();

            String nombre = data.getNombreDestino().toLowerCase();

            String poblacion = data.getPoblaDestino().toLowerCase();

            String zona = "";

            if (data.getZona() != null) {
                zona = data.getZona().getNombre().toLowerCase();
            } else {
                zona = "No se ha encontrado una Zona";
            }

            return (data.getRef().matches("(.*)" + lowerCaseBusqueda + "(.*)")
                    || nombre.matches("(.*)" + lowerCaseBusqueda + "(.*)")
                    || zona.matches("(.*)" + lowerCaseBusqueda + "(.*)")
                    || poblacion.matches("(.*)" + lowerCaseBusqueda + "(.*)"));
        });

    }

    public void trannsferLista(ObservableList<Albaran> albaranes) {

        if (albaranes.size() > 0) {

            listView.setCellFactory((ListView<Albaran> param) -> new AlbaranCell());

            filteredList = new FilteredList<>(albaranes, data -> true);

            listView.setItems(filteredList);

        }

    }

    @FXML
    private void iniciarComparacion(ActionEvent event) throws Exception {

        ObservableList<Albaran> filas = listView.getItems();

        filas.forEach(item -> {
            FileService.actualizarAlbaran(item); // SOBREESCRIBIMOS EL ARCHIVO CON LAS MODIFICACIONES HECHAS

        });
        ObservableList<Albaran> filasSeleccionadas = listView.getSelectionModel().getSelectedItems();

        if (filasSeleccionadas.isEmpty()) {

            AlertService noSelectedInfo = new AlertService(Alert.AlertType.INFORMATION, "Info", "\nNo hay filas seleccionadas. ", "");

            noSelectedInfo.showAndWait();

        } else {

            ArrayList<Albaran> albaranes = new ArrayList<>();

            filasSeleccionadas.forEach(action -> {
                if (ValidatorService.albaranValidator(action)) {
                    albaranes.add(action);

                }
            });

            ComparatorService service = new ComparatorService();

            service.compararAlbaranes(albaranes);

            int comparados = (int) filasSeleccionadas.stream().filter(predicate -> predicate.getMEJOR_AGENCIA() != null).count();
            int noComparados = (int) filasSeleccionadas.stream().filter(predicate -> predicate.getMEJOR_AGENCIA() == null).count();

            Map<String, List<Albaran>> result = albaranes.stream().filter(predicate -> predicate.getMEJOR_AGENCIA() != null)
                    .collect(Collectors.groupingBy(Albaran::getMEJOR_AGENCIA));

            boolean resultado = FileService.writeOutFiles(result);

            if (resultado) {

                try {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tdt/ui/salidaComparacion/salida.fxml"));

                    Parent root1 = (Parent) fxmlLoader.load();

                    SalidaController salidaController = fxmlLoader.getController();

                    salidaController.loadData(result);

                    Stage stageAlbaranes = new Stage();

                    ConfigStage.configStage(stageAlbaranes, "Albaranes comparados", Modality.APPLICATION_MODAL);

                    stageAlbaranes.setScene(new Scene(root1));

                    stageAlbaranes.show();

                    ((Node) event.getSource()).getScene().getWindow().hide();

                } catch (IOException e) {
                    AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Resultado", e);

                    alert.showAndWait();
                }

            }

        }
    }

    public class AlbaranCell extends ListCell<Albaran> {

        @Override
        public void updateItem(Albaran albaran, boolean empty) {

            super.updateItem(albaran, empty);

            if (albaran != null) {

                CellListViewBase cell = new CellListViewBase(albaran.getRef());

                cell.ref.setText(albaran.getRef());

                cell.txtNombreDestino.setText(albaran.getNombreDestino());

                Zona zona = AlbaranService.setAlbaranZona(albaran, cell.lbZona, listView);

                albaran.setZona(zona);

                if (albaran.getZona() != null) {
                    cell.lbZona.setText(albaran.getZona().getNombre());

                }

                cell.lbCP.setText(albaran.getPostalDestino());

                cell.txtPais.setText(albaran.getPais());

                cell.txtPais.focusedProperty().addListener((observable, oldValue, newValue) -> {

                    albaran.setPais(cell.txtPais.getText());

                    Zona nuevaZona = AlbaranService.setAlbaranZona(albaran, cell.lbZona, listView);

                    if (nuevaZona != null) {

                        albaran.setZona(nuevaZona);

                        cell.lbZona.setText(albaran.getZona().getNombre());

                    }

                });

                cell.txtPoblacion.setText(albaran.getPoblaDestino());

                cell.txtBultos.setText("1");

                cell.txtBultos.focusedProperty().addListener((args, oldValue, newValue) -> {

                    if (!newValue && !cell.txtBultos.getText().isEmpty()) {

                        if (ValidatorService.integerValidate(cell.txtBultos)) {

                            albaran.setBultos(cell.txtBultos.getText());

                        }

                    }

                });

                cell.btnEditar.setOnAction((ActionEvent event) -> {

                    showAlbaranForm(albaran);
                });

                cell.chkAgencia.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                    if (newValue) {

                        cell.comboAgencia.setDisable(false);

                        cell.comboAgencia.setItems(nombreAgencias);

                    } else {

                        cell.comboAgencia.setItems(null);

                        cell.comboAgencia.setDisable(true);

                        Zona newZona = AlbaranService.setAlbaranZona(albaran, cell.lbZona, listView);

                        albaran.setZona(newZona);

                        cell.lbZona.setText(albaran.getZona().getNombre());

                        cell.lbZona.setStyle(null);

                        albaran.setMEJOR_AGENCIA(null);

                    }
                });

                cell.comboAgencia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (newValue != null) {
                            // FORZADO AGENCIA
                            cell.lbZona.setText("Forzada a " + newValue);

                            cell.lbZona.getStyleClass().clear();

                            cell.lbZona.setStyle("-fx-text-fill: blue");

                            albaran.setMEJOR_AGENCIA(newValue);

                        } else {
                            // NO FORZADO AGENCIA
                            AlbaranService.setAlbaranZona(albaran, cell.lbZona, listView);

                            albaran.setMEJOR_AGENCIA(null);

                        }
                    }

                });
                setGraphic(cell);

                if (!ValidatorService.albaranValidator(albaran)) {
                    cell.getStyleClass().add("invalidRow");
                } else {
                    cell.getStyleClass().clear();

                }
            /*    if (!cell.isValidCP() || !cell.isValidPais() || !cell.isValidPoblacion() || !cell.isValidRef()) {
                    cell.getStyleClass().add("invalidRow");
                } else {
                    cell.getStyleClass().clear();

                }
*/
            } else {
                setGraphic(null); // ESTO ES IMPORTANTE PARA ACTUALIZAR LA LISTA. SINO, NUNCA SE ELIMINAN LAS FILAS QUE TENGAN QUE BORRARSE
            }

        }

    }

    private void showAlbaranForm(Albaran albaran) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("form/albaranForm.fxml"));

            Parent albaranRoot = (Parent) fxmlLoader.load();

            Stage stageForm = new Stage();

            ConfigStage.configStage(stageForm, "Albaran: " + albaran.getRef(), Modality.APPLICATION_MODAL);

            AlbaranFormController albaranFormController = fxmlLoader.getController();

            albaranFormController.transferAlbaran(albaran);

            stageForm.setScene(new Scene(albaranRoot));

            stageForm.show();

            stageForm.setOnHidden(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    listView.refresh();
                }
            });

        } catch (IOException e) {
            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Formulario de albaran", e);

            alert.showAndWait();
        }
    }

    public class CellListViewBase extends HBox {

        protected final Label ref;
        protected final Label txtNombreDestino;
        protected final Label lbZona;
        protected final TextField txtPais;
        protected final Label txtPoblacion;
        protected final Label lbCP;
        protected final CheckBox chkAgencia;
        protected final ComboBox comboAgencia;
        protected final TextField txtBultos;
        protected final Button btnEditar;

        private boolean validRef;
        private boolean validCP;
        private boolean validPais;
        private boolean validPoblacion;
        private boolean validNombre;

        public boolean isValidRef() {
            return validRef;
        }

        public boolean isValidCP() {
            return validCP;
        }

        public boolean isValidPais() {
            return validPais;
        }

        public boolean isValidPoblacion() {
            return validPoblacion;
        }

        public CellListViewBase(String id) {

            setId(id);

            ref = new Label();
            txtNombreDestino = new Label();
            lbZona = new Label();
            txtPais = new TextField();
            txtPoblacion = new Label();
            lbCP = new Label();
            chkAgencia = new CheckBox();
            comboAgencia = new ComboBox();
            txtBultos = new TextField();
            btnEditar = new Button();

            validRef = true;
            validCP = true;
            validPoblacion = true;
            validPais = true;
            validNombre = true;

            txtNombreDestino.textProperty().addListener(listener -> {
                validNombre = !txtNombreDestino.getText().isEmpty();
            });

            txtPoblacion.textProperty().addListener(listener -> {
                validPoblacion = !txtPoblacion.getText().isEmpty();
            });
            ref.textProperty().addListener(listener -> {
                validRef = !ref.getText().isEmpty();
            });
            lbCP.textProperty().addListener(listener -> {
                validCP = !lbCP.getText().isEmpty();

            });
            txtPais.textProperty().addListener(listener -> {

                validPais = !txtPais.getText().isEmpty();

                if (!validPais || !validPoblacion || !validCP || !validRef || !validNombre) {
                    this.getStyleClass().add("invalidRow");
                } else {
                    this.getStyleClass().clear();

                }

            });
            txtPoblacion.textProperty().addListener(listener -> {
                validPoblacion = !txtPoblacion.getText().isEmpty();

            });

            setAlignment(javafx.geometry.Pos.CENTER_LEFT);
            setPrefHeight(35.0);
            setPrefWidth(1000.0);

            ref.setPrefHeight(25.0);
            ref.setPrefWidth(91.0);
            ref.setText("numero ref");
            ref.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            ref.setTextOverrun(javafx.scene.control.OverrunStyle.CLIP);
            HBox.setMargin(ref, new Insets(0.0, 0.0, 0.0, 10.0));

            txtNombreDestino.setPrefHeight(17.0);
            txtNombreDestino.setPrefWidth(166.0);
            txtNombreDestino.setText("Nombre Destino");
            txtNombreDestino.setTextOverrun(javafx.scene.control.OverrunStyle.CLIP);
            HBox.setMargin(txtNombreDestino, new Insets(0.0, 0.0, 0.0, 10.0));

            lbZona.setId("lbZona");
            lbZona.setPrefHeight(17.0);
            lbZona.setPrefWidth(200.0);
            lbZona.setText("Zona");
            HBox.setMargin(lbZona, new Insets(0.0, 0.0, 0.0, 10.0));

            txtPais.setPrefHeight(25.0);
            txtPais.setPrefWidth(110.0);
            txtPais.setPromptText("Pais");
            txtPais.setText("txtPais");
            HBox.setMargin(txtPais, new Insets(0.0, 0.0, 0.0, 10.0));

            txtPoblacion.setLayoutX(143.0);
            txtPoblacion.setLayoutY(19.0);
            txtPoblacion.setPrefHeight(17.0);
            txtPoblacion.setPrefWidth(136.0);
            txtPoblacion.setText("Poblacion");
            txtPoblacion.setTextOverrun(javafx.scene.control.OverrunStyle.CLIP);
            HBox.setMargin(txtPoblacion, new Insets(0.0, 0.0, 0.0, 10.0));

            lbCP.setId("lbCP");
            lbCP.setPrefHeight(17.0);
            lbCP.setPrefWidth(55.0);
            lbCP.setText("29003");
            HBox.setMargin(lbCP, new Insets(0.0, 0.0, 0.0, 10.0));

            chkAgencia.setId("chkAgencia");
            chkAgencia.setMnemonicParsing(false);
            HBox.setMargin(chkAgencia, new Insets(0.0, 0.0, 0.0, 10.0));

            comboAgencia.setDisable(true);
            comboAgencia.setId("comboAgencia");
            comboAgencia.setPrefWidth(150.0);
            comboAgencia.setPromptText("Forzar Agencia");
            HBox.setMargin(comboAgencia, new Insets(0.0, 0.0, 0.0, 2.0));

            txtBultos.setPrefHeight(25.0);
            txtBultos.setPrefWidth(39.0);
            txtBultos.setText("Bultos");
            HBox.setMargin(txtBultos, new Insets(0.0, 0.0, 0.0, 10.0));

//            btnEditar.setAlignment(javafx.geometry.Pos.CENTER);
//            btnEditar.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
            btnEditar.setMnemonicParsing(false);
            btnEditar.setPrefHeight(26.0);
            btnEditar.setPrefWidth(83.0);
            btnEditar.setText("Editar");
//            btnEditar.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            btnEditar.setFont(new Font(13.0));
            HBox.setMargin(btnEditar, new Insets(0.0, 0.0, 0.0, 10.0));
            setOpaqueInsets(new Insets(0.0));
            setPadding(new Insets(0.0, 5.0, 0.0, 5.0));

            ConfigStage.setIcon(btnEditar, "edit.png", 14);

            getChildren().add(ref);
            getChildren().add(txtNombreDestino);
            getChildren().add(lbZona);
            getChildren().add(txtPais);
            getChildren().add(txtPoblacion);
            getChildren().add(lbCP);
            getChildren().add(chkAgencia);
            getChildren().add(comboAgencia);
            getChildren().add(txtBultos);
            getChildren().add(btnEditar);

        }
    }

}
