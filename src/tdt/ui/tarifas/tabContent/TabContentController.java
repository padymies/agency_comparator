/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.ui.tarifas.tabContent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import tdt.db.dao.IAgenciaDao;
import tdt.db.dao.IProvinciaDao;
import tdt.db.dao.ITarifaDao;
import tdt.db.dao.IZonaDao;
import tdt.db.daoImpl.AgenciaImpl;
import tdt.db.daoImpl.ProvinciaImpl;
import tdt.db.daoImpl.TarifaImpl;
import tdt.db.daoImpl.ZonaImpl;
import tdt.model.Agencia;
import tdt.model.AgenciaZona;
import tdt.model.Provincia;
import tdt.model.Tarifa;
import tdt.model.Zona;
import tdt.services.AlertService;
import tdt.services.ConfigStage;
import tdt.ui.tarifas.tabContent.importForm.importForm;
import tdt.ui.tarifas.tabContent.listItem.listItemAgenciaBase;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class TabContentController implements Initializable {

    @FXML
    private AnchorPane tabContent;
    @FXML
    private ListView<AgenciaZona> listZonas;
    @FXML
    private TextField txtNombreZona;
    @FXML
    private TextField txtPais;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TableView<Tarifa> tableTarifas;
    @FXML
    private TextField txtKilos;
    @FXML
    private TextField txtPrecio;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnBorrar;
    @FXML
    private Button btnAddTarifa;
    @FXML
    private Button btnAgenciaZona;
    @FXML
    private Label lbTarifaZona;
    @FXML
    private Label lbTarifaAgencia;
    @FXML
    private TableColumn<Tarifa, Integer> columnKg;
    @FXML
    private TableColumn<Tarifa, Double> columnPrecio;
    @FXML
    private ComboBox<String> comboAgencias;
    @FXML
    private HBox hboxNewForm;
    @FXML
    private TextField newIncrem;
    @FXML
    private TextField newMaxKilos;
    @FXML
    private Button btnNewAgencia;
    @FXML
    private Button btnCancelNew;
    @FXML
    private Button btnImportar;

    private Button btnActualizarTarifa;

    private Zona zona;

    private AgenciaZona agenciaZona;

    private Tab tab;

    private IZonaDao zonaDao;

    private ITarifaDao tarifaDao;

    private IAgenciaDao agenciaDao;

    private IProvinciaDao provinciaDao;

    private ObservableList<AgenciaZona> listaAgenciaZona;

    private ObservableList<Tarifa> tarifas;

    private ObservableList<Provincia> provinciasDeZona;

    private ObservableList<Provincia> provinciasSinZona;

    @FXML
    private TextArea txtProvincias;

    public AnchorPane getTabContent() {
        return tabContent;
    }

    public void setTabContent(AnchorPane tabContent) {
        this.tabContent = tabContent;
    }

    public ListView<?> getListZonas() {
        return listZonas;
    }

    public void setListZonas(ListView<AgenciaZona> listZonas) {
        this.listZonas = listZonas;
    }

    public TextField getTxtNombreZona() {
        return txtNombreZona;
    }

    public void setTxtNombreZona(TextField txtNombreZona) {
        this.txtNombreZona = txtNombreZona;
    }

    public TextField getTxtPais() {
        return txtPais;
    }

    public void setTxtPais(TextField txtPais) {
        this.txtPais = txtPais;
    }

    public TextArea getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(TextArea txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public TableView<Tarifa> getTableTarifas() {
        return tableTarifas;
    }

    public void setTableTarifas(TableView<Tarifa> tableTarifas) {
        this.tableTarifas = tableTarifas;
    }

    public TextField getTxtKilos() {
        return txtKilos;
    }

    public void setTxtKilos(TextField txtKilos) {
        this.txtKilos = txtKilos;
    }

    public TextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(TextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public Button getBtnActualizarTarifa() {
        return btnActualizarTarifa;
    }

    public void setBtnActualizarTarifa(Button btnActualizarTarifa) {
        this.btnActualizarTarifa = btnActualizarTarifa;
    }

    /**
     * Initializes the controller class.
     */
    public TabContentController(int idZona) {

        zonaDao = new ZonaImpl();

        tarifaDao = new TarifaImpl();

        agenciaDao = new AgenciaImpl();

        provinciaDao = new ProvinciaImpl();

        if (idZona != -1) { // ES EDICION deZONA

            zona = zonaDao.obtenerZona(idZona);

            listaAgenciaZona = tarifaDao.obtenerAgenciasPorZona(idZona);

            provinciasDeZona = provinciaDao.obtenerProvinciasDeZona(idZona);

            provinciasSinZona = provinciaDao.obtenerProvinciasSinZonaAsociada();

            provinciasDeZona.forEach(provincia -> {
                Platform.runLater(() -> {
                    txtProvincias.appendText(" " + provincia.getNombre() + ";  ");
                });

            });
        } else { // ES ZONA NUEVA

            listaAgenciaZona = FXCollections.observableArrayList();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        listaAgenciaZona.forEach(agenciaZona -> {
            listZonas.setCellFactory(new Callback<ListView<AgenciaZona>, ListCell<AgenciaZona>>() {

                @Override
                public ListCell<AgenciaZona> call(ListView<AgenciaZona> param) {
                    return new TabContentController.AgenciaZonaCell();
                }
            });

            listZonas.setItems(listaAgenciaZona);

        });

        listZonas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        listZonas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AgenciaZona>() { // MOSTRAR TARIFAS AL PULSAR UNA AGENCIA
            @Override
            public void changed(ObservableValue<? extends AgenciaZona> observable, AgenciaZona oldValue, AgenciaZona newValue) {

                if (newValue != null && newValue != oldValue) {

                    agenciaZona = newValue;

                    lbTarifaZona.setText(zona.getNombre());

                    lbTarifaAgencia.setText(newValue.getNombreAgencia());

                    tarifas = tarifaDao.obtenerTarifasPorZonaAgencia(newValue.getIdZona(), newValue.getIdAgencia());

                    ObservableList<Tarifa> tarifasSinDuplicados = quitarPreciosDuplicados(tarifas);

                    columnKg.setCellValueFactory(new PropertyValueFactory<Tarifa, Integer>("kg"));

                    columnPrecio.setCellValueFactory(new PropertyValueFactory<Tarifa, Double>("precio"));

                    btnImportar.setDisable(false);

                    // TODO: QUITAR ESTO: era una prueba para no repetir tarifas con mismo precio y distinto kg
                    tableTarifas.setItems(tarifasSinDuplicados);

                } else {
                    btnImportar.setDisable(true);

                }
            }

        });

        btnGuardar.setOnAction(event -> {
            guardarZona(event);
        });

        btnBorrar.setOnAction(event -> {
            borrarZona(event);
        });

        btnAgenciaZona.setOnAction((event) -> {
            addAgenciaZona(event);

        });

        btnCancelNew.setOnAction(event -> {

            cancelNewAgencia(event);

        });

        btnNewAgencia.setOnAction(event -> {
            addNewAgencia(event);

        });

        btnAddTarifa.setOnAction(event -> {
            addTarifa(event);
        });

        comboAgencias.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                btnNewAgencia.setDisable(false);
            }

        });

    }

    // ======================== ZONAS ===================================//
    private void borrarZona(ActionEvent event) {

        TabPane tabPane = (TabPane) tabContent.getParent().getParent();

        Tab tab = tabPane.getSelectionModel().getSelectedItem();

        tabPane.getTabs().remove(tab);

        if (zona != null) {

            zonaDao.borrarZona(zona.getIdZona());

            AlertService alert = new AlertService((Alert.AlertType.INFORMATION), "Borrar Zona", "Se ha borrado la zona: " + zona.getNombre(), null);

            alert.showAndWait();
        }

    }

    private void guardarZona(ActionEvent event) {

        if (txtNombreZona.getText().trim().isEmpty()) {

            AlertService alert = new AlertService(Alert.AlertType.ERROR, "Añadir Agencia", "Debe escribir el nombre de la zona.",
                    "Para crear una zona debe escribir, al menos, el nombre de la zona");

            alert.showAndWait();
        } else {

            if (zona == null) { // CREAMOS UNA NUEVA

                zona = new Zona(txtNombreZona.getText().trim(), txtPais.getText().trim(), txtDescripcion.getText().trim());

                int id = zonaDao.añadirZona(zona);

                if (id != -1) {

                    TabPane tabPane = (TabPane) tabContent.getParent().getParent();

                    Tab tab = tabPane.getSelectionModel().getSelectedItem();

                    tab.setText(zona.getNombre());

                    tab.setClosable(false);

                    AlertService alert = new AlertService((Alert.AlertType.INFORMATION), "Nueva Zona", "Se ha creado una nueva zona: " + zona.getNombre(), "");

                    zona.setIdZona(id);

                    alert.showAndWait();
                }

            } else { // ACTUALIZAMOS LA ZONA

                boolean result = updateZona();

            }
        }
    }

    private boolean updateZona() {
        boolean result = zonaDao.actualizarZona(zona);

        if (result) {

            TabPane tabPane = (TabPane) tabContent.getParent().getParent();

            Tab tab = tabPane.getSelectionModel().getSelectedItem();

            tab.setText(zona.getNombre());

            AlertService alert = new AlertService((Alert.AlertType.INFORMATION), "Actualización de Zona", "Se ha actualizado la zona: " + zona.getNombre(), "");

            alert.showAndWait();
        }
        return result;
    }

    // ======================== FIN ZONAS ===================================//
    // ======================== TARIFAS =====================================//
    private void addTarifa(ActionEvent event) {

        int kg = -1;

        try {

            kg = Integer.parseInt(txtKilos.getText().trim());

        } catch (NumberFormatException e) {

            AlertService alert = new AlertService((Alert.AlertType.ERROR), "Actualización de Tarifa", "No se han podido guardar los cambios: ",
                    "Los kilos deben ser un número entero");

            alert.showAndWait();
        }

        double precio = -1;

        try {

            precio = Double.parseDouble(txtPrecio.getText().trim());

        } catch (NumberFormatException e) {

            AlertService alert = new AlertService((Alert.AlertType.ERROR), "Actualización de Tarifa", "No se han podido guardar los cambios: ",
                    "El precio deben ser un número entero o decimal");

            alert.showAndWait();

        }

        if (kg != -1 && precio != -1) {

            AgenciaZona selected = listZonas.getSelectionModel().getSelectedItem();

            final int KgComparator = kg;

            int size = tableTarifas.getItems().filtered(tarifa -> tarifa.getKg() == KgComparator).size();

            Tarifa newTarifa = new Tarifa(kg, selected.getIdAgencia(), selected.getIdZona(), precio);

            if (size == 0) { // INSERCION

                boolean result = tarifaDao.añadirTarifa(newTarifa);

                if (result) {
                    tableTarifas.getItems().add(newTarifa);

                }

            } else { // ACTUALIZACION
                boolean result = tarifaDao.actualizarTarifa(newTarifa);

                if (result) {

                    Tarifa tar = tableTarifas.getItems().filtered(tarifa -> tarifa.getKg() == KgComparator).get(0);

                    int index = tableTarifas.getItems().indexOf(tar);

                    tableTarifas.getItems().remove(tar);

                    tableTarifas.getItems().add(index, newTarifa);

                    tableTarifas.refresh();

                }
            }
        }

    }
    // ========================FIN TARIFAS ==================================//

    // =========================== AGENCIAS ===============================  //
    private void addAgenciaZona(ActionEvent event) {

        if (txtNombreZona.getText().trim().isEmpty()) {

            AlertService alert = new AlertService(Alert.AlertType.ERROR, "Añadir Agencia", "Debe escribir el nombre de la nueva zona.",
                    "Para añadir una agencia a una zona nueva, debe escribir, al menos, el nombre de la zona");

            alert.showAndWait();
        } else {

            if (zona == null) { // ES UNA ZONA NUEVA Y TENEMOS QUE GUARDARLA ANTES

                guardarZona(null);

            }

            btnAgenciaZona.setVisible(false);

            ObservableList<Agencia> listAgencias = agenciaDao.obtenerAgencias();

            ArrayList<String> agenciasAgregadas = new ArrayList<>();

            listaAgenciaZona.forEach(action -> {
                agenciasAgregadas.add(action.getNombreAgencia());
            });

            listAgencias.forEach(predicate -> {

                if (!agenciasAgregadas.contains(predicate.getNombre())) {
                    comboAgencias.getItems().add(predicate.getNombre());
                }

            });

            hboxNewForm.setVisible(true);

        }

    }

    private void addNewAgencia(ActionEvent event) {

        if (listaAgenciaZona.isEmpty()) {

            listZonas.setCellFactory(new Callback<ListView<AgenciaZona>, ListCell<AgenciaZona>>() {

                @Override
                public ListCell<AgenciaZona> call(ListView<AgenciaZona> param) {
                    return new TabContentController.AgenciaZonaCell();
                }
            });
            listZonas.setItems(listaAgenciaZona);
        }

        ObservableList<Agencia> listAgencias = agenciaDao.obtenerAgencias();

        Agencia selected = listAgencias.filtered(item -> item.getNombre().equals(comboAgencias.getSelectionModel().getSelectedItem())).get(0);

        double incremento = 0;

        int maxKilos = 0;

        if (!newIncrem.getText().isEmpty()) {

            try {
                incremento = Double.parseDouble(newIncrem.getText().trim());

            } catch (NumberFormatException e) {
                AlertService alert = new AlertService((Alert.AlertType.ERROR), "Actualización de agencia", "No se han podido guardar los cambios: ", "El incremento debe ser un número entero o decimal");
                alert.showAndWait();
            }

        }
        if (!newMaxKilos.getText().isEmpty()) {
            try {
                maxKilos = Integer.parseInt(newMaxKilos.getText().trim());

            } catch (NumberFormatException e) {
                AlertService alert = new AlertService((Alert.AlertType.ERROR), "Actualización de agencia", "No se han podido guardar los cambios: ", "El maximo de kilos debe ser un número entero");
                alert.showAndWait();
            }

        }

        boolean result = tarifaDao.añadirAgenciaZona(selected.getId_agencia(), zona.getIdZona(), incremento, maxKilos);

        if (result) {

            listaAgenciaZona.add(new AgenciaZona(selected.getId_agencia(), zona.getIdZona(), incremento, maxKilos, selected.getNombre()));

            hboxNewForm.setVisible(false);

            btnAgenciaZona.setVisible(true);

            comboAgencias.getItems().remove(comboAgencias.getSelectionModel().getSelectedItem());

            comboAgencias.getSelectionModel().clearSelection();

        }
    }

    private void cancelNewAgencia(ActionEvent event) {

        hboxNewForm.setVisible(false);

        btnAgenciaZona.setVisible(true);

        comboAgencias.getSelectionModel().clearSelection();
    }

    @FXML
    private void importarTarifa(ActionEvent event) {

        importForm form = new importForm() {
            @Override
            protected void importar(ActionEvent actionEvent) {

                ObservableList<Tarifa> listaTarifas = tarifaDao.copiarTarifa(
                        importComboAgencia.getSelectionModel().getSelectedItem().toString(), importComboZona.getSelectionModel().getSelectedItem().toString());

                if (!tableTarifas.getItems().isEmpty()) {

                    AlertService alert = new AlertService((Alert.AlertType.CONFIRMATION), "Importación de Tarifa", "La agencia  " + agenciaZona.getNombreAgencia()
                            + " tiene tarifas guardadas para esta zona. ¿Quiere sobreescribir los datos?",
                            "");

                    Optional<ButtonType> result = alert.showAndWait();

                    if (!result.isPresent()) {

                    } else if (result.get() == ButtonType.OK) {
                        boolean deleteResult = tarifaDao.borrarTarifasDeAgencia(agenciaZona.getIdZona(), agenciaZona.getIdAgencia());

                        boolean insertResult = false;

                        if (deleteResult) {

                            insertResult = tarifaDao.pegarTarifa(agenciaZona.getIdZona(), agenciaZona.getIdAgencia(), listaTarifas);

                        }

                        if (insertResult) {

                            tableTarifas.setItems(listaTarifas);

                        }

                    }
                } else {
                    boolean insertResult = tarifaDao.pegarTarifa(agenciaZona.getIdZona(), agenciaZona.getIdAgencia(), listaTarifas);

                    if (insertResult) {

                        tableTarifas.setItems(listaTarifas);

                    }
                }

                ((Node) actionEvent.getSource()).getScene().getWindow().hide();

            }

            @Override
            protected void zonaChange(Object oldValue, Object newValue) {
                if (newValue != null) {

                    ObservableList<String> listaAgencias = tarifaDao.obtenerNombresAgenciasPorZona(newValue.toString());

                    importComboAgencia.setDisable(false);

                    importComboAgencia.setItems(listaAgencias);

                }
            }

            @Override
            protected void agenciaChange(Object oldValue, Object newValue) {
                if (newValue != null) {

                    getImportBtn().setDisable(false);

                }
            }

        };

        Stage stage = new Stage();

        ConfigStage.configStage(stage, "Importar tarifa", Modality.APPLICATION_MODAL);

        stage.setScene(new Scene(form));

        stage.show();

    }

    

    // ===========================FIN AGENCIAS ===============================  //
    public class AgenciaZonaCell extends ListCell<AgenciaZona> {

        @Override
        public void updateItem(AgenciaZona agenciaZona, boolean empty) {
            super.updateItem(agenciaZona, empty);

            if (agenciaZona != null && !empty) {

                listItemAgenciaBase cell = new listItemAgenciaBase(agenciaZona.getIdAgencia(), agenciaZona.getIdZona()) {
                    @Override
                    protected void borrarItemAgencia(ActionEvent actionEvent) {
                        AlertService alert = new AlertService(Alert.AlertType.CONFIRMATION, "Borrado de Agencia-Zona", "Seguro que quiere eliminar la Agencia " + agenciaZona.getNombreAgencia() + " para esta zona?", "");

                        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);

                        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

                        alert.getButtonTypes().setAll(okButton, noButton);

                        alert.showAndWait().ifPresent(type -> {

                            if (type == okButton) {

                                boolean result = tarifaDao.borrarAgenciaZona(agenciaZona.getIdAgencia(), agenciaZona.getIdZona());

                                if (result) {
                                    listZonas.getItems().remove(agenciaZona);

                                }

                            } else {
                                alert.close();
                            }
                        });

                    }

                    @Override
                    protected void editarItemAgencia(ActionEvent actionEvent) {
                        if (btnEditar.getText().equals("Editar")) {
                            btnEditar.setText("Guardar");
                            txtIncremento.setDisable(false);
                            txtMaxKilos.setDisable(false);

                        } else {
                            btnEditar.setText("Editar");

                            txtIncremento.setDisable(true);

                            txtMaxKilos.setDisable(true);

                            double incremento = 0;

                            int maxKilos = 0;

                            if (!txtIncremento.getText().trim().isEmpty()) {
                                try {
                                    incremento = Double.parseDouble(txtIncremento.getText().trim());

                                } catch (NumberFormatException e) {
                                    AlertService alert = new AlertService((Alert.AlertType.ERROR), "Actualización de agencia", "No se han podido gruardar los cambios: ", "El incremento debe ser un número entero o decimal");
                                    alert.showAndWait();
                                }
                            }
                            if (!txtMaxKilos.getText().trim().isEmpty()) {
                                try {
                                    maxKilos = Integer.parseInt(txtMaxKilos.getText().trim());

                                } catch (NumberFormatException e) {
                                    AlertService alert = new AlertService((Alert.AlertType.ERROR), "Actualización de agencia", "No se han podido gruardar los cambios: ", "El máximo de kilos debe ser un número entero.");
                                    alert.showAndWait();
                                }

                            }

                            boolean result = tarifaDao.actualizarAgenciaZona(agenciaZona.getIdAgencia(), agenciaZona.getIdZona(), incremento, maxKilos);

                            if (result) {

                                System.out.println("Actualizado");
                            } else {

                            }
                        }

                    }

                };

                cell.getTextAgencia().setText(agenciaZona.getNombreAgencia());

                if (agenciaZona.getIncremento() > 0) {
                    cell.getTxtIncremento().setText(String.valueOf(agenciaZona.getIncremento()));

                }

                if (agenciaZona.getMaxKilos() > 0) {
                    cell.getTxtMaxKilos().setText(String.valueOf(agenciaZona.getMaxKilos()));
                }

                setGraphic(cell);
            } else {
                setGraphic(null);
            }

        }

    }

    private ObservableList<Tarifa> quitarPreciosDuplicados(ObservableList<Tarifa> tarifas) {
        ObservableList<Tarifa> tarifasSinDuplicados = FXCollections.observableArrayList();

        if (tarifas.size() > 0) {

            for (int i = 1; i < tarifas.size(); i++) {
                Tarifa current = tarifas.get(i - 1);
                Tarifa next = tarifas.get(i);
                if (current.getPrecio() != next.getPrecio()) {
                    tarifasSinDuplicados.add(current);
                }
            }
            tarifasSinDuplicados.add(tarifas.get(tarifas.size() - 1));
        }
        return tarifasSinDuplicados;
    }

}
