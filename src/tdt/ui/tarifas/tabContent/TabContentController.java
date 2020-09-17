/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.ui.tarifas.tabContent;

import java.io.IOException;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
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
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import tdt.db.daoImpl.AgencyImpl;
import tdt.db.daoImpl.CityImpl;
import tdt.db.daoImpl.RateImpl;
import tdt.db.daoImpl.ZoneImpl;
import tdt.model.Agency;
import tdt.model.AgencyZone;
import tdt.model.City;
import tdt.model.Rate;
import tdt.model.Zone;
import tdt.services.AlertExceptionService;
import tdt.services.AlertService;
import tdt.services.ConfigStage;
import tdt.ui.tarifas.tabContent.importForm.importForm;
import tdt.ui.tarifas.tabContent.listItem.listItemAgenciaBase;
import tdt.db.dao.IAgencyDao;
import tdt.db.dao.ICityDao;
import tdt.db.dao.IRateDao;
import tdt.db.dao.IZoneDao;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class TabContentController implements Initializable {

    @FXML
    private AnchorPane tabContent;
    @FXML
    private ListView<AgencyZone> listZonas;
    @FXML
    private TextField txtNombreZona;
    @FXML
    private TextField txtPais;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TableView<Rate> tableTarifas;
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
    private TableColumn<Rate, Integer> columnKg;
    @FXML
    private TableColumn<Rate, Double> columnPrecio;
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

    private Zone zona;

    private AgencyZone agenciaZona;

    private Tab tab;

    private IZoneDao zonaDao;

    private IRateDao tarifaDao;

    private IAgencyDao agenciaDao;

    private ICityDao provinciaDao;

    private ObservableList<AgencyZone> listaAgenciaZona;

    private ObservableList<Rate> tarifas;

    private ObservableList<City> provinciasDeZona;

    private ObservableList<City> provinciasSinZona;

    private int idZona;

    @FXML
    private TextArea txtProvincias;
    @FXML
    private TextField newPlazoEntrega;
    @FXML
    private Button btnProvincias;

    public AnchorPane getTabContent() {
        return tabContent;
    }

    public void setTabContent(AnchorPane tabContent) {
        this.tabContent = tabContent;
    }

    public ListView<?> getListZonas() {
        return listZonas;
    }

    public void setListZonas(ListView<AgencyZone> listZonas) {
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

    public TableView<Rate> getTableTarifas() {
        return tableTarifas;
    }

    public void setTableTarifas(TableView<Rate> tableTarifas) {
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

        zonaDao = new ZoneImpl();

        tarifaDao = new RateImpl();

        agenciaDao = new AgencyImpl();

        provinciaDao = new CityImpl();

        if (idZona != -1) { // ES EDICION deZONA

            this.idZona = idZona;

            zona = zonaDao.getZone(idZona);

            listaAgenciaZona = tarifaDao.getAgenciesByZone(idZona);

            updateTxAreaProvincias();
        } else { // ES ZONA NUEVA

            listaAgenciaZona = FXCollections.observableArrayList();

        }

    }

    private void updateTxAreaProvincias() {
        provinciasDeZona = provinciaDao.getCitiesByZone(this.idZona);
        provinciasSinZona = provinciaDao.getCitiesNoZone();
        Platform.runLater(() -> {
            txtProvincias.clear();

            provinciasDeZona.forEach(provincia -> {
                txtProvincias.appendText(" " + provincia.getName() + ";  ");
            });

        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConfigStage.setIcon(btnAddTarifa, "add.png", 12);
        ConfigStage.setIcon(btnGuardar, "check.png", 14);
        ConfigStage.setIcon(btnBorrar, "delete.png", 14);
        ConfigStage.setIcon(btnImportar, "import.png", 14);
        ConfigStage.setIcon(btnAgenciaZona, "add.png", 12);
        ConfigStage.setIcon(btnNewAgencia, "check.png", 12);
        ConfigStage.setIcon(btnCancelNew, "cancel.png", 12);

        listaAgenciaZona.forEach(agenciaZona -> {
            listZonas.setCellFactory(new Callback<ListView<AgencyZone>, ListCell<AgencyZone>>() {

                @Override
                public ListCell<AgencyZone> call(ListView<AgencyZone> param) {
                    return new TabContentController.AgenciaZonaCell();
                }
            });

            listZonas.setItems(listaAgenciaZona);

        });

        listZonas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        listZonas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AgencyZone>() { // MOSTRAR TARIFAS AL PULSAR UNA AGENCIA
            @Override
            public void changed(ObservableValue<? extends AgencyZone> observable, AgencyZone oldValue, AgencyZone newValue) {

                if (newValue != null && newValue != oldValue) {

                    agenciaZona = newValue;

                    lbTarifaZona.setText(zona.getName());

                    lbTarifaAgencia.setText(newValue.getAgencyName());

                    tarifas = tarifaDao.getRateByAgencyZone(newValue.getZoneId(), newValue.getAgencyId());

//                    ObservableList<Tarifa> tarifasSinDuplicados = quitarPreciosDuplicados(tarifas);
                    columnKg.setCellValueFactory(new PropertyValueFactory<Rate, Integer>("kg"));

                    columnPrecio.setCellValueFactory(new PropertyValueFactory<Rate, Double>("precio"));

                    btnImportar.setDisable(false);

                    // TODO: QUITAR ESTO: era una prueba para no repetir tarifas con mismo precio y distinto kg
//                    tableTarifas.setItems(tarifasSinDuplicados);
                    tableTarifas.setItems(tarifas);

                } else {
                    btnImportar.setDisable(true);

                }
            }

        });

        MenuItem borrar = new MenuItem("Borrar fila");

        borrar.setStyle("-fx-pref-width: 100");

        borrar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Rate tar = tableTarifas.getSelectionModel().getSelectedItem();
                tar.setZoneId(zona.getZoneId());
                boolean result = tarifaDao.deleteRate(tar);
                if (result) {
                    tableTarifas.getItems().remove(tar);
                }
            }

        });

        ContextMenu c = new ContextMenu();

        c.getItems().add(borrar);

        tableTarifas.setContextMenu(c);

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

            AlertService alert = new AlertService((Alert.AlertType.CONFIRMATION), "Borrado de Zona", "Seguro que quiere eliminar la Zona: " + zona.getName() + "?",
                    "");

            ButtonType okButton = new ButtonType("Sí", ButtonBar.ButtonData.YES);

            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

            alert.getButtonTypes().setAll(okButton, noButton);

            alert.showAndWait().ifPresent(type -> {

                if (type == okButton) {

                    zonaDao.deleteZone(zona.getZoneId());

                    AlertService alerta = new AlertService((Alert.AlertType.INFORMATION), "Zona eliminada", "Se ha borrado la zona: " + zona.getName(), "");

                    alerta.showAndWait();

                } else {
                    alert.close();
                }
            });

        }

    }

    private void guardarZona(ActionEvent event) {

        if (txtNombreZona.getText().trim().isEmpty()) {

            AlertService alert = new AlertService(Alert.AlertType.ERROR, "Añadir Agencia", "Debe escribir el nombre de la zona.",
                    "Para crear una zona debe escribir, al menos, el nombre de la zona");

            alert.showAndWait();
        } else {

            if (zona == null) { // CREAMOS UNA NUEVA

                zona = new Zone(txtNombreZona.getText().trim(), txtPais.getText().trim(), txtDescripcion.getText().trim());

                int id = zonaDao.addZone(zona);

                if (id != -1) {

                    TabPane tabPane = (TabPane) tabContent.getParent().getParent();

                    Tab tab = tabPane.getSelectionModel().getSelectedItem();

                    tab.setText(zona.getName());

                    tab.setClosable(false);

                    AlertService alert = new AlertService((Alert.AlertType.INFORMATION), "Nueva Zona", "Se ha creado una nueva zona: " + zona.getName(), "");

                    zona.setZoneId(id);

                    this.idZona = id;

                    alert.showAndWait();

                }

            } else { // ACTUALIZAMOS LA ZONA

                boolean result = updateZona();

            }
        }
    }

    private boolean updateZona() {
        boolean result = zonaDao.updateZone(zona);

        if (result) {

            TabPane tabPane = (TabPane) tabContent.getParent().getParent();

            Tab tab = tabPane.getSelectionModel().getSelectedItem();

            tab.setText(zona.getName());

            AlertService alert = new AlertService((Alert.AlertType.INFORMATION), "Actualización de Zona", "Se ha actualizado la zona: " + zona.getName(), "");

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

            AgencyZone selected = listZonas.getSelectionModel().getSelectedItem();

            final int KgComparator = kg;

            int size = tableTarifas.getItems().filtered(tarifa -> tarifa.getKg() == KgComparator).size();

            Rate newTarifa = new Rate(kg, selected.getAgencyId(), selected.getZoneId(), precio);

            if (size == 0) { // INSERCION

                boolean result = tarifaDao.addRate(newTarifa);

                if (result) {
                    tableTarifas.getItems().add(newTarifa);

                }

            } else { // ACTUALIZACION
                boolean result = tarifaDao.updateRate(newTarifa);

                if (result) {

                    Rate tar = tableTarifas.getItems().filtered(tarifa -> tarifa.getKg() == KgComparator).get(0);

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

            ObservableList<Agency> listAgencias = agenciaDao.getAgencies();

            ArrayList<String> agenciasAgregadas = new ArrayList<>();

            listaAgenciaZona.forEach(action -> {
                agenciasAgregadas.add(action.getAgencyName());
            });

            ObservableList<String> nombreAgencias = FXCollections.observableArrayList();

            listAgencias.forEach(predicate -> {

                if (!agenciasAgregadas.contains(predicate.getName())) {
                    nombreAgencias.add(predicate.getName());
                }

            });

            comboAgencias.setItems(nombreAgencias);
            hboxNewForm.setVisible(true);

        }

    }

    private void addNewAgencia(ActionEvent event) {

        if (listaAgenciaZona.isEmpty()) {

            listZonas.setCellFactory(new Callback<ListView<AgencyZone>, ListCell<AgencyZone>>() {

                @Override
                public ListCell<AgencyZone> call(ListView<AgencyZone> param) {
                    return new TabContentController.AgenciaZonaCell();
                }
            });
            listZonas.setItems(listaAgenciaZona);
        }

        ObservableList<Agency> listAgencias = agenciaDao.getAgencies();

        Agency selected = listAgencias.filtered(item -> item.getName().equals(comboAgencias.getSelectionModel().getSelectedItem())).get(0);

        double incremento = 0;

        int plazoEntrega = 0;

        int maxKilos = 0;

        if (!newIncrem.getText().isEmpty()) {

            try {
                incremento = Double.parseDouble(newIncrem.getText().trim());

            } catch (NumberFormatException e) {
                AlertService alert = new AlertService((Alert.AlertType.ERROR), "Actualización de agencia", "No se han podido guardar los cambios: ", "El incremento debe ser un número entero o decimal");
                alert.showAndWait();
            }

        }
        if (!newPlazoEntrega.getText().isEmpty()) {

            try {
                plazoEntrega = Integer.parseInt(newPlazoEntrega.getText().trim());

            } catch (NumberFormatException e) {
                AlertService alert = new AlertService((Alert.AlertType.ERROR), "Actualización de agencia", "No se han podido guardar los cambios: ", "El plazo de entrega debe ser un número entero");
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

        boolean result = tarifaDao.addZoneAgency(selected.getAgencyId(), zona.getZoneId(), incremento, plazoEntrega, maxKilos);

        if (result) {

            listaAgenciaZona.add(new AgencyZone(selected.getAgencyId(), zona.getZoneId(), incremento, plazoEntrega, maxKilos, selected.getName()));

            hboxNewForm.setVisible(false);

            btnAgenciaZona.setVisible(true);

            comboAgencias.getItems().remove(comboAgencias.getSelectionModel().getSelectedItem());

            comboAgencias.getSelectionModel().clearSelection();

        }
    }

    private void cancelNewAgencia(ActionEvent event) {

        hboxNewForm.setVisible(false);

        btnAgenciaZona.setVisible(true);

        comboAgencias.setItems(null);
    }

    @FXML
    private void importarTarifa(ActionEvent event) {

        importForm form = new importForm() {
            @Override
            protected void importar(ActionEvent actionEvent) {

                ObservableList<Rate> listaTarifas = tarifaDao.copyRate(
                        importComboAgencia.getSelectionModel().getSelectedItem().toString(), importComboZona.getSelectionModel().getSelectedItem().toString());

                if (!tableTarifas.getItems().isEmpty()) {

                    AlertService alert = new AlertService((Alert.AlertType.CONFIRMATION), "Importación de Tarifa", "La agencia  " + agenciaZona.getAgencyName()
                            + " tiene tarifas guardadas para esta zona. ¿Quiere sobreescribir los datos?",
                            "");

                    Optional<ButtonType> result = alert.showAndWait();

                    if (!result.isPresent()) {

                    } else if (result.get() == ButtonType.OK) {
                        boolean deleteResult = tarifaDao.deleteRatesFromAgency(agenciaZona.getZoneId(), agenciaZona.getAgencyId());

                        boolean insertResult = false;

                        if (deleteResult) {

                            insertResult = tarifaDao.pasteRate(agenciaZona.getZoneId(), agenciaZona.getAgencyId(), listaTarifas);

                        }

                        if (insertResult) {

                            tableTarifas.setItems(listaTarifas);

                        }

                    }
                } else {
                    boolean insertResult = tarifaDao.pasteRate(agenciaZona.getZoneId(), agenciaZona.getAgencyId(), listaTarifas);

                    if (insertResult) {

                        tableTarifas.setItems(listaTarifas);

                    }
                }

                ((Node) actionEvent.getSource()).getScene().getWindow().hide();

            }

            @Override
            protected void zonaChange(Object oldValue, Object newValue) {
                if (newValue != null) {

                    ObservableList<String> listaAgencias = tarifaDao.getAgenciesNameByZone(newValue.toString());

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

    @FXML
    private void goToProvincias(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tdt/ui/provincias/provincias.fxml"));

            Parent root = (Parent) fxmlLoader.load();

            Stage stageMapFIle = new Stage();

            ConfigStage.configStage(stageMapFIle, "Provincias", Modality.APPLICATION_MODAL);

            Scene escena = new Scene(root);

            stageMapFIle.setScene(escena);

            stageMapFIle.show();

            stageMapFIle.setOnHiding(new EventHandler<WindowEvent>() { // Actualiza las provincias de las zonas en el textArea

                @Override
                public void handle(WindowEvent event) {
                    updateTxAreaProvincias();
                }
            });

        } catch (IOException e) {
            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido cargar la ventana provincias", e);

            alert.showAndWait();
        }
    }

    // ===========================FIN AGENCIAS ===============================  //
    public class AgenciaZonaCell extends ListCell<AgencyZone> {

        @Override
        public void updateItem(AgencyZone agenciaZona, boolean empty) {
            super.updateItem(agenciaZona, empty);

            if (agenciaZona != null && !empty) {

                listItemAgenciaBase cell = new listItemAgenciaBase(agenciaZona.getAgencyId(), agenciaZona.getZoneId()) {
                    @Override
                    protected void borrarItemAgencia(ActionEvent actionEvent) {
                        AlertService alert = new AlertService(Alert.AlertType.CONFIRMATION, "Borrado de Agencia-Zona", "Seguro que quiere eliminar la Agencia " + agenciaZona.getAgencyName() + " para esta zona?", "");

                        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);

                        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

                        alert.getButtonTypes().setAll(okButton, noButton);

                        alert.showAndWait().ifPresent(type -> {

                            if (type == okButton) {

                                boolean result = tarifaDao.deleteZoneAgency(agenciaZona.getAgencyId(), agenciaZona.getZoneId());

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
                            txtPlazoEntrega.setDisable(false);
                            txtMaxKilos.setDisable(false);

                        } else {
                            btnEditar.setText("Editar");

                            txtIncremento.setDisable(true);

                            txtPlazoEntrega.setDisable(true);

                            txtMaxKilos.setDisable(true);

                            double incremento = 0;

                            int plazoEntrega = 0;

                            int maxKilos = 0;

                            if (!txtIncremento.getText().trim().isEmpty()) {
                                try {
                                    incremento = Double.parseDouble(txtIncremento.getText().trim());

                                } catch (NumberFormatException e) {
                                    AlertService alert = new AlertService((Alert.AlertType.ERROR), "Actualización de agencia", "No se han podido gruardar los cambios: ", "El incremento debe ser un número entero o decimal");
                                    alert.showAndWait();
                                }
                            }
                            if (!txtPlazoEntrega.getText().trim().isEmpty()) {
                                try {
                                    plazoEntrega = Integer.parseInt(txtPlazoEntrega.getText().trim());

                                } catch (NumberFormatException e) {
                                    AlertService alert = new AlertService((Alert.AlertType.ERROR), "Actualización de agencia", "No se han podido gruardar los cambios: ", "El plazo de entrega debe ser un número entero.");
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

                            boolean result = tarifaDao.updateZoneAgency(agenciaZona.getAgencyId(), agenciaZona.getZoneId(), incremento, plazoEntrega, maxKilos);

                            if (result) {

                                System.out.println("Actualizado");
                            } else {

                            }
                        }

                    }

                };

                cell.getTextAgencia().setText(agenciaZona.getAgencyName());

                if (agenciaZona.getIncrease() > 0) {
                    cell.getTxtIncremento().setText(String.valueOf(agenciaZona.getIncrease()));

                }

                if (agenciaZona.getDeliveryTime() > 0) {
                    cell.getTxtPlazoEntrega().setText(String.valueOf(agenciaZona.getDeliveryTime()));

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

    private ObservableList<Rate> quitarPreciosDuplicados(ObservableList<Rate> tarifas) {
        ObservableList<Rate> tarifasSinDuplicados = FXCollections.observableArrayList();

        if (tarifas.size() > 0) {

            for (int i = 1; i < tarifas.size(); i++) {
                Rate current = tarifas.get(i - 1);
                Rate next = tarifas.get(i);
                if (current.getPrice() != next.getPrice()) {
                    tarifasSinDuplicados.add(current);
                }
            }
            tarifasSinDuplicados.add(tarifas.get(tarifas.size() - 1));
        }
        return tarifasSinDuplicados;
    }

}
