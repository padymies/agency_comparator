package tdt.ui.rates.tabContent;

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
import tdt.db.dao.IAgencyDao;
import tdt.db.dao.ICityDao;
import tdt.db.dao.IRateDao;
import tdt.db.dao.IZoneDao;
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
import tdt.ui.rates.tabContent.importForm.importForm;
import tdt.ui.rates.tabContent.listItem.listItemAgencyBase;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class TabContentController implements Initializable {

    @FXML
    private AnchorPane tabContent;
    @FXML
    private ListView<AgencyZone> zoneList;
    @FXML
    private TextField txtZoneName;
    @FXML
    private TextField txtCountry;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TableView<Rate> rateTable;
    @FXML
    private TextField txtKilos;
    @FXML
    private TextField txtPrice;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnAddRate;
    @FXML
    private Button btnAgencyZone;
    @FXML
    private Label lbRateZone;
    @FXML
    private Label lbRateAgency;
    @FXML
    private TableColumn<Rate, Integer> columnKg;
    @FXML
    private TableColumn<Rate, Double> columnPrice;
    @FXML
    private ComboBox<String> comboAgencies;
    @FXML
    private HBox hboxNewForm;
    @FXML
    private TextField newIncrem;
    @FXML
    private TextField newMaxKilos;
    @FXML
    private Button btnNewAgency;
    @FXML
    private Button btnCancelNew;
    @FXML
    private Button btnImport;

    private Button btnUpdateRate;

    private Zone zone;

    private AgencyZone agencyZone;

    private Tab tab;

    private IZoneDao zoneDao;

    private IRateDao rateDao;

    private IAgencyDao agencyDao;

    private ICityDao cityDao;

    private ObservableList<AgencyZone> listaAgencyZone;

    private ObservableList<Rate> rates;

    private ObservableList<City> zoneOfCities;

    private ObservableList<City> citiesNoZone;

    private int idZone;

    @FXML
    private TextArea txtCities;
    @FXML
    private TextField newDeliveryTime;
    @FXML
    private Button btnCities;

    public AnchorPane getTabContent() {
        return tabContent;
    }

    public void setTabContent(AnchorPane tabContent) {
        this.tabContent = tabContent;
    }

    public ListView<?> getZoneList() {
        return zoneList;
    }

    public void setZoneList(ListView<AgencyZone> zoneList) {
        this.zoneList = zoneList;
    }

    public TextField getTxtZoneName() {
        return txtZoneName;
    }

    public void setTxtZoneName(TextField txtZoneName) {
        this.txtZoneName = txtZoneName;
    }

    public TextField getTxtCountry() {
        return txtCountry;
    }

    public void setTxtCountry(TextField txtCountry) {
        this.txtCountry = txtCountry;
    }

    public TextArea getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(TextArea txtDescription) {
        this.txtDescription = txtDescription;
    }

    public TableView<Rate> getRateTable() {
        return rateTable;
    }

    public void setRateTable(TableView<Rate> rateTable) {
        this.rateTable = rateTable;
    }

    public TextField getTxtKilos() {
        return txtKilos;
    }

    public void setTxtKilos(TextField txtKilos) {
        this.txtKilos = txtKilos;
    }

    public TextField getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(TextField txtPrice) {
        this.txtPrice = txtPrice;
    }

    public Button getBtnUpdateRate() {
        return btnUpdateRate;
    }

    public void setBtnUpdateRate(Button btnUpdateRate) {
        this.btnUpdateRate = btnUpdateRate;
    }

    /**
     * Initializes the controller class.
     */
    public TabContentController(int zoneId) {

        zoneDao = new ZoneImpl();

        rateDao = new RateImpl();

        agencyDao = new AgencyImpl();

        cityDao = new CityImpl();

        if (zoneId != -1) { // ES EDICION deZONA

            this.idZone = zoneId;

            zone = zoneDao.getZone(zoneId);

            listaAgencyZone = rateDao.getAgenciesByZone(zoneId);

            updateTxAreaCities();
        } else { // ES ZONA NUEVA

            listaAgencyZone = FXCollections.observableArrayList();

        }

    }

    private void updateTxAreaCities() {
        zoneOfCities = cityDao.getCitiesByZone(this.idZone);
        citiesNoZone = cityDao.getCitiesNoZone();
        Platform.runLater(() -> {
            txtCities.clear();

            zoneOfCities.forEach(city -> {
                txtCities.appendText(" " + city.getName() + ";  ");
            });

        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConfigStage.setIcon(btnAddRate, "add.png", 12);
        ConfigStage.setIcon(btnSave, "check.png", 14);
        ConfigStage.setIcon(btnDelete, "delete.png", 14);
        ConfigStage.setIcon(btnImport, "import.png", 14);
        ConfigStage.setIcon(btnAgencyZone, "add.png", 12);
        ConfigStage.setIcon(btnNewAgency, "check.png", 12);
        ConfigStage.setIcon(btnCancelNew, "cancel.png", 12);

        txtCities.setEditable(false);
        
        listaAgencyZone.forEach(agencyZone -> {
            zoneList.setCellFactory(new Callback<ListView<AgencyZone>, ListCell<AgencyZone>>() {

                @Override
                public ListCell<AgencyZone> call(ListView<AgencyZone> param) {
                    return new TabContentController.AgencyZoneCell();
                }
            });

            zoneList.setItems(listaAgencyZone);

        });

        zoneList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        zoneList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AgencyZone>() { // MOSTRAR TARIFAS AL PULSAR UNA AGENCIA
            @Override
            public void changed(ObservableValue<? extends AgencyZone> observable, AgencyZone oldValue, AgencyZone newValue) {

                if (newValue != null && newValue != oldValue) {

                    agencyZone = newValue;

                    lbRateZone.setText(zone.getName());

                    lbRateAgency.setText(newValue.getAgencyName());

                    rates = rateDao.getRateByAgencyZone(newValue.getZoneId(), newValue.getAgencyId());

                    columnKg.setCellValueFactory(new PropertyValueFactory<Rate, Integer>("kg"));

                    columnPrice.setCellValueFactory(new PropertyValueFactory<Rate, Double>("price"));

                    btnImport.setDisable(false);

                    rateTable.setItems(rates);

                } else {
                    btnImport.setDisable(true);

                }
            }

        });

        MenuItem delete = new MenuItem("Borrar fila");

        delete.setStyle("-fx-pref-width: 100");

        delete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Rate rate = rateTable.getSelectionModel().getSelectedItem();
                rate.setZoneId(zone.getZoneId());
                boolean result = rateDao.deleteRate(rate);
                if (result) {
                    rateTable.getItems().remove(rate);
                }
            }

        });

        ContextMenu context = new ContextMenu();

        context.getItems().add(delete);

        rateTable.setContextMenu(context);

        btnSave.setOnAction(event -> {
            saveZone(event);
        });

        btnDelete.setOnAction(event -> {
            deleteZone(event);
        });

        btnAgencyZone.setOnAction((event) -> {
            addAgencyZone(event);

        });

        btnCancelNew.setOnAction(event -> {

            cancelNewAgency(event);

        });

        btnNewAgency.setOnAction(event -> {
            addNewAgency(event);

        });

        btnAddRate.setOnAction(event -> {
            addRate(event);
        });

        comboAgencies.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                btnNewAgency.setDisable(false);
            }

        });

        txtZoneName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue && zone != null) {
                    zone.setName(txtZoneName.getText().trim());
                }
            }

        });
        txtDescription.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue && zone != null) {
                    zone.setDescription(txtDescription.getText().trim());
                }
            }

        });
        txtCountry.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue && zone != null) {
                    zone.setCountry(txtCountry.getText().trim());
                }
            }

        });

    }

    // ======================== ZONAS ===================================//
    private void deleteZone(ActionEvent event) {

        TabPane tabPane = (TabPane) tabContent.getParent().getParent();

        Tab tab = tabPane.getSelectionModel().getSelectedItem();

        tabPane.getTabs().remove(tab);

        if (zone != null) {

            AlertService alert = new AlertService((Alert.AlertType.CONFIRMATION), "Borrado de Zona", "Seguro que quiere eliminar la Zona: " + zone.getName() + "?",
                    "");

            ButtonType okButton = new ButtonType("Sí", ButtonBar.ButtonData.YES);

            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

            alert.getButtonTypes().setAll(okButton, noButton);

            alert.showAndWait().ifPresent(type -> {

                if (type == okButton) {

                    zoneDao.deleteZone(zone.getZoneId());

                    AlertService deleteZoneAlert = new AlertService((Alert.AlertType.INFORMATION), "Zona eliminada", "Se ha borrado la zona: " + zone.getName(), "");

                    deleteZoneAlert.showAndWait();

                } else {
                    alert.close();
                }
            });

        }

    }

    private void saveZone(ActionEvent event) {

        if (txtZoneName.getText().trim().isEmpty()) {

            AlertService alert = new AlertService(Alert.AlertType.ERROR, "Añadir Agencia", "Debe escribir el nombre de la zona.",
                    "Para crear una zona debe escribir, al menos, el nombre de la zona");

            alert.showAndWait();
        } else {

            if (zone == null) { // CREAMOS UNA NUEVA

                zone = new Zone(txtZoneName.getText().trim(), txtCountry.getText().trim(), txtDescription.getText().trim());

                int id = zoneDao.addZone(zone);

                if (id != -1) {

                    TabPane tabPane = (TabPane) tabContent.getParent().getParent();

                    Tab tab = tabPane.getSelectionModel().getSelectedItem();

                    tab.setText(zone.getName());

                    tab.setClosable(false);

                    AlertService alert = new AlertService((Alert.AlertType.INFORMATION), "Nueva Zona", "Se ha creado una nueva zona: " + zone.getName(), "");

                    zone.setZoneId(id);

                    this.idZone = id;

                    alert.showAndWait();

                }

            } else { // ACTUALIZAMOS LA ZONA

                boolean result = updateZone();

            }
        }
    }

    private boolean updateZone() {
        boolean result = zoneDao.updateZone(zone);

        if (result) {

            TabPane tabPane = (TabPane) tabContent.getParent().getParent();

            Tab tab = tabPane.getSelectionModel().getSelectedItem();

            tab.setText(zone.getName());

            AlertService alert = new AlertService((Alert.AlertType.INFORMATION), "Actualización de Zona", "Se ha actualizado la zona: " + zone.getName(), "");

            alert.showAndWait();
        }
        return result;
    }

    // ======================== FIN ZONAS ===================================//
    // ======================== TARIFAS =====================================//
    private void addRate(ActionEvent event) {

        int kg = -1;

        try {

            kg = Integer.parseInt(txtKilos.getText().trim());

        } catch (NumberFormatException e) {

            AlertService alert = new AlertService((Alert.AlertType.ERROR), "Actualización de Tarifa", "No se han podido guardar los cambios: ",
                    "Los kilos deben ser un número entero");

            alert.showAndWait();
        }

        double price = -1;

        try {

            price = Double.parseDouble(txtPrice.getText().trim());

        } catch (NumberFormatException e) {

            AlertService alert = new AlertService((Alert.AlertType.ERROR), "Actualización de Tarifa", "No se han podido guardar los cambios: ",
                    "El precio deben ser un número entero o decimal");

            alert.showAndWait();

        }

        if (kg != -1 && price != -1) {

            AgencyZone selected = zoneList.getSelectionModel().getSelectedItem();

            if (selected != null) {
                final int KgComparator = kg;

                int size = rateTable.getItems().filtered(rate -> rate.getKg() == KgComparator).size();

                Rate newRate = new Rate(kg, selected.getAgencyId(), selected.getZoneId(), price);

                if (size == 0) { // INSERCION

                    boolean result = rateDao.addRate(newRate);

                    if (result) {
                        rateTable.getItems().add(newRate);

                    }

                } else { // ACTUALIZACION
                    boolean result = rateDao.updateRate(newRate);

                    if (result) {

                        Rate rate = rateTable.getItems().filtered(item -> item.getKg() == KgComparator).get(0);

                        int index = rateTable.getItems().indexOf(rate);

                        rateTable.getItems().remove(rate);

                        rateTable.getItems().add(index, newRate);

                        rateTable.refresh();

                    }
                }
            } else {
                AlertService alert = new AlertService((Alert.AlertType.WARNING), "Añadir Tarifa", "No hay agencia seleccionada",
                        "Seleccione una agencia para poder modificar sus tarifas");

                alert.showAndWait();
            }
        }

    }
    // ========================FIN TARIFAS ==================================//

    // =========================== AGENCIAS ===============================  //
    private void addAgencyZone(ActionEvent event) {

        if (txtZoneName.getText().trim().isEmpty()) {

            AlertService alert = new AlertService(Alert.AlertType.ERROR, "Añadir Agencia", "Debe escribir el nombre de la nueva zona.",
                    "Para añadir una agencia a una zona nueva, debe escribir, al menos, el nombre de la zona");

            alert.showAndWait();
        } else {

            if (zone == null) { // ES UNA ZONA NUEVA Y TENEMOS QUE GUARDARLA ANTES

                saveZone(null);

            }

            btnAgencyZone.setVisible(false);

            ObservableList<Agency> agencyList = agencyDao.getAgencies();

            ArrayList<String> addedAgencies = new ArrayList<>();

            listaAgencyZone.forEach(action -> {
                addedAgencies.add(action.getAgencyName());
            });

            ObservableList<String> agencyNames = FXCollections.observableArrayList();

            agencyList.forEach(predicate -> {

                if (!addedAgencies.contains(predicate.getName())) {
                    agencyNames.add(predicate.getName());
                }

            });

            comboAgencies.setItems(agencyNames);
            hboxNewForm.setVisible(true);

        }

    }

    private void addNewAgency(ActionEvent event) {

        if (listaAgencyZone.isEmpty()) {

            zoneList.setCellFactory(new Callback<ListView<AgencyZone>, ListCell<AgencyZone>>() {

                @Override
                public ListCell<AgencyZone> call(ListView<AgencyZone> param) {
                    return new TabContentController.AgencyZoneCell();
                }
            });
            zoneList.setItems(listaAgencyZone);
        }

        ObservableList<Agency> agencyList = agencyDao.getAgencies();

        Agency selected = agencyList.filtered(item -> item.getName().equals(comboAgencies.getSelectionModel().getSelectedItem())).get(0);

        double increment = 0;

        int deliveryTime = 0;

        int maxKilos = 0;

        if (!newIncrem.getText().isEmpty()) {

            try {
                increment = Double.parseDouble(newIncrem.getText().trim());

            } catch (NumberFormatException e) {
                AlertService alert = new AlertService((Alert.AlertType.ERROR), "Actualización de agencia", "No se han podido guardar los cambios: ", "El incremento debe ser un número entero o decimal");
                alert.showAndWait();
            }

        }
        if (!newDeliveryTime.getText().isEmpty()) {

            try {
                deliveryTime = Integer.parseInt(newDeliveryTime.getText().trim());

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

        boolean result = rateDao.addZoneAgency(selected.getAgencyId(), zone.getZoneId(), increment, deliveryTime, maxKilos);

        if (result) {

            listaAgencyZone.add(new AgencyZone(selected.getAgencyId(), zone.getZoneId(), increment, deliveryTime, maxKilos, selected.getName()));

            hboxNewForm.setVisible(false);

            btnAgencyZone.setVisible(true);

            comboAgencies.getItems().remove(comboAgencies.getSelectionModel().getSelectedItem());

            comboAgencies.getSelectionModel().clearSelection();

        }
    }

    private void cancelNewAgency(ActionEvent event) {

        hboxNewForm.setVisible(false);

        btnAgencyZone.setVisible(true);

        comboAgencies.setItems(null);
    }

    @FXML
    private void rateImport(ActionEvent event) {

        importForm form = new importForm() {
            @Override
            protected void doImport(ActionEvent actionEvent) {

                ObservableList<Rate> rateList = rateDao.copyRate(
                        importComboAgency.getSelectionModel().getSelectedItem().toString(), importComboZone.getSelectionModel().getSelectedItem().toString());

                if (!rateTable.getItems().isEmpty()) {

                    AlertService alert = new AlertService((Alert.AlertType.CONFIRMATION), "Importación de Tarifa", "La agencia  " + agencyZone.getAgencyName()
                            + " tiene tarifas guardadas para esta zona. ¿Quiere sobreescribir los datos?",
                            "");

                    Optional<ButtonType> result = alert.showAndWait();

                    if (!result.isPresent()) {

                    } else if (result.get() == ButtonType.OK) {
                        boolean deleteResult = rateDao.deleteRatesFromAgency(agencyZone.getZoneId(), agencyZone.getAgencyId());

                        boolean insertResult = false;

                        if (deleteResult) {

                            insertResult = rateDao.pasteRate(agencyZone.getZoneId(), agencyZone.getAgencyId(), rateList);

                        }

                        if (insertResult) {

                            rateTable.setItems(rateList);

                        }

                    }
                } else {
                    boolean insertResult = rateDao.pasteRate(agencyZone.getZoneId(), agencyZone.getAgencyId(), rateList);

                    if (insertResult) {

                        rateTable.setItems(rateList);

                    }
                }

                ((Node) actionEvent.getSource()).getScene().getWindow().hide();

            }

            @Override
            protected void zoneChange(Object oldValue, Object newValue) {
                if (newValue != null) {

                    ObservableList<String> agencyList = rateDao.getAgenciesNameByZone(newValue.toString());

                    importComboAgency.setDisable(false);

                    importComboAgency.setItems(agencyList);

                }
            }

            @Override
            protected void agencyChange(Object oldValue, Object newValue) {
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
    private void goToCities(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tdt/ui/cities/cities.fxml"));

            Parent root = (Parent) fxmlLoader.load();

            Stage cityStage = new Stage();

            ConfigStage.configStage(cityStage, "Provincias", Modality.APPLICATION_MODAL);

            Scene scene = new Scene(root);

            cityStage.setScene(scene);

            cityStage.show();

            cityStage.setOnHiding(new EventHandler<WindowEvent>() { // Actualiza las provincias de las zonas en el textArea

                @Override
                public void handle(WindowEvent event) {
                    updateTxAreaCities();
                }
            });

        } catch (IOException e) {
            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido cargar la ventana provincias", e);

            alert.showAndWait();
        }
    }

    // ===========================FIN AGENCIAS ===============================  //
    public class AgencyZoneCell extends ListCell<AgencyZone> {

        @Override
        public void updateItem(AgencyZone agencyZone, boolean empty) {
            super.updateItem(agencyZone, empty);

            if (agencyZone != null && !empty) {

                listItemAgencyBase cell = new listItemAgencyBase(agencyZone.getAgencyId(), agencyZone.getZoneId()) {

                    @Override
                    protected void deleteItemAgency(ActionEvent actionEvent) {
                        AlertService alert = new AlertService(Alert.AlertType.CONFIRMATION, "Borrado de Agencia-Zona", "Seguro que quiere eliminar la Agencia " + agencyZone.getAgencyName() + " para esta zona?", "");

                        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);

                        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

                        alert.getButtonTypes().setAll(okButton, noButton);

                        alert.showAndWait().ifPresent(type -> {

                            if (type == okButton) {

                                boolean result = rateDao.deleteZoneAgency(agencyZone.getAgencyId(), agencyZone.getZoneId());

                                if (result) {
                                    zoneList.getItems().remove(agencyZone);

                                }

                            } else {
                                alert.close();
                            }
                        });

                    }

                    @Override
                    protected void editItemAgency(ActionEvent actionEvent) {
                        if (btnEdit.getText().equals("Editar")) {
                            btnEdit.setText("Guardar");
                            txtIncrement.setDisable(false);
                            txtDeliveryTime.setDisable(false);
                            txtMaxKilos.setDisable(false);

                        } else {
                            btnEdit.setText("Editar");

                            txtIncrement.setDisable(true);

                            txtDeliveryTime.setDisable(true);

                            txtMaxKilos.setDisable(true);

                            double increment = 0;

                            int deliveryTime = 0;

                            int maxKilos = 0;

                            if (!txtIncrement.getText().trim().isEmpty()) {
                                try {
                                    increment = Double.parseDouble(txtIncrement.getText().trim());

                                } catch (NumberFormatException e) {
                                    AlertService alert = new AlertService((Alert.AlertType.ERROR), "Actualización de agencia", "No se han podido gruardar los cambios: ", "El incremento debe ser un número entero o decimal");
                                    alert.showAndWait();
                                }
                            }
                            if (!txtDeliveryTime.getText().trim().isEmpty()) {
                                try {
                                    deliveryTime = Integer.parseInt(txtDeliveryTime.getText().trim());

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

                            boolean result = rateDao.updateZoneAgency(agencyZone.getAgencyId(), agencyZone.getZoneId(), increment, deliveryTime, maxKilos);

                        }

                    }

                };

                cell.getTextAgency().setText(agencyZone.getAgencyName());

                if (agencyZone.getIncrease() > 0) {
                    cell.getTxtIncrement().setText(String.valueOf(agencyZone.getIncrease()));

                }

                if (agencyZone.getDeliveryTime() > 0) {
                    cell.getTxtDeliveryTime().setText(String.valueOf(agencyZone.getDeliveryTime()));

                }

                if (agencyZone.getMaxKilos() > 0) {
                    cell.getTxtMaxKilos().setText(String.valueOf(agencyZone.getMaxKilos()));
                }

                setGraphic(cell);
            } else {
                setGraphic(null);
            }

        }

    }

}
