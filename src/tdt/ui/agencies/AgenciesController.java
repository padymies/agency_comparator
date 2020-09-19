package tdt.ui.agencies;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tdt.db.dao.IAgencyDao;
import tdt.db.daoImpl.AgencyImpl;
import tdt.model.Agency;
import tdt.services.AlertService;
import tdt.services.ConfigStage;

public class AgenciesController implements Initializable {

    private ObservableList<Agency> list;

    @FXML
    private TableView<Agency> table;
    @FXML
    private TableColumn<Agency, String> name;
    @FXML
    private TableColumn<?, ?> bundles;
    @FXML
    private TableColumn<?, ?> surcharge_fuel;
    @FXML
    private TableColumn<?, ?> minimum_refund;
    @FXML
    private TableColumn<?, ?> comision;
    @FXML
    private TableColumn<Agency, String> big_shipment;
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputBundles;
    @FXML
    private TextField inputSurcharge;
    @FXML
    private TextField inputMinimum;
    @FXML
    private TextField inputComision;
    @FXML
    private ComboBox<String> cmbBigShipment;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private VBox vBox;
    @FXML
    private HBox hBox;

    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

    private ArrayList<Agency> selections;

    private IAgencyDao agencyDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConfigStage.setIcon(addButton, "add.png", 12);
        ConfigStage.setIcon(deleteButton, "delete.png", 12);
      
        
        ObservableList<String> cmbList = FXCollections.observableArrayList();

        cmbList.add("Sí");

        cmbList.add("No");

        
        cmbBigShipment.setItems(cmbList);

        cmbBigShipment.getSelectionModel().select("No");

        selections = new ArrayList<>();

        agencyDao = new AgencyImpl();

        list = agencyDao.getAgencies();

        addButton.setOnAction(e -> addProvider());

        deleteButton.setOnAction(e -> deleteProviders());

        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        bundles.setCellValueFactory(new PropertyValueFactory<>("bundles"));

        surcharge_fuel.setCellValueFactory(new PropertyValueFactory<>("surchargeFuel"));

        minimum_refund.setCellValueFactory(new PropertyValueFactory<>("minimumRefund"));

        comision.setCellValueFactory(new PropertyValueFactory<>("comision"));

        big_shipment.setCellValueFactory(param -> {

            Agency ag = (Agency) param.getValue();

            if (ag.isBigShipment()) {

                return new SimpleStringProperty("Sí");
            }

            return new SimpleStringProperty("No");

        });

        hBox.setPadding(new Insets(10, 10, 10, 10));

        hBox.setSpacing(10);

        // Populate tabview
        TableViewSelectionModel<Agency> selectionModel = table.getSelectionModel();

        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        table.setItems(list);

        MenuItem delete = new MenuItem("Borrar fila");

        delete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                deleteProviders();
            }

        });

        table.setContextMenu(new ContextMenu(delete));

    }

    private void addProvider() {

        String name = inputName.getText().trim();

        ObservableList<String> agenciesName = agencyDao.getAgencyNames();

        if (!agenciesName.contains(name)) {

            int bundle = 0;

            double surcharge = 0;

            double minimum = 0;

            double comision = 0;

            try {
                if (!inputBundles.getText().isEmpty()) {
                    bundle = Integer.parseInt(inputBundles.getText().trim());

                }
                if (!inputSurcharge.getText().isEmpty()) {
                    surcharge = Double.parseDouble(inputSurcharge.getText().trim());

                }
                if (!inputMinimum.getText().isEmpty()) {
                    minimum = Double.parseDouble(inputMinimum.getText().trim());

                }
                if (!inputComision.getText().isEmpty()) {
                    comision = Double.parseDouble(inputComision.getText().trim());

                }
                String selected = cmbBigShipment.getSelectionModel().getSelectedItem();

                boolean bigShipment = false;

                if (selected.equals("Sí")) {

                    bigShipment = true;
                }

                Agency agency = new Agency(name, bundle, surcharge, minimum, bigShipment, comision);

                int agencyId = agencyDao.addAgency(agency);

                if (agencyId != -1) {

                    agency.setAgencyId(agencyId);

                    list.add(agency);

                    inputName.clear();
                    inputBundles.clear();
                    inputSurcharge.clear();
                    inputMinimum.clear();
                    inputComision.clear();
                } else {

                    AlertService alert = new AlertService((Alert.AlertType.ERROR), "Creación de agencia", "No se ha podido crear la agencia \n",
                            "- Error de guardado en la base de datos");

                    alert.showAndWait();
                }

            } catch (NumberFormatException e) {
                AlertService alert = new AlertService((Alert.AlertType.ERROR), "Creación de agencia", "No se ha podido crear la agencia \n",
                        "- Los bultos debe ser un número entero\n- El recargo combustibloe, mínimo reembolso y la comisión deben ser un número entero o decimal.");

                alert.showAndWait();
            }

        } else {
            AlertService alert = new AlertService((Alert.AlertType.ERROR), "Creación de agencia", "No se ha podido crear la agencia \n",
                    "- No puede haber dos agencias con el mismo nombre ");

            alert.showAndWait();
        }

    }

    private void deleteProviders() {

        Agency selectedAgency = table.getSelectionModel().getSelectedItem();

        AlertService alert = new AlertService((Alert.AlertType.CONFIRMATION), "Borrado de agencia", "Seguro que quiere eliminar la Agencia " + selectedAgency.getName() +"?",
                "");

        ButtonType okButton = new ButtonType("Sí", ButtonBar.ButtonData.YES);

        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(okButton, noButton);

        alert.showAndWait().ifPresent(type -> {

            if (type == okButton) {

                ObservableList<Agency> allProviders = table.getItems();

                agencyDao.deleteAgency(selectedAgency.getAgencyId());

                allProviders.remove(selectedAgency);

            } else {
                alert.close();
            }
        });

    }

}
