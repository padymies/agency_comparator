package tdt.ui.providers;

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
import tdt.db.daoImpl.AgencyImpl;
import tdt.model.Agency;
import tdt.services.AlertService;
import tdt.services.ConfigStage;
import tdt.db.dao.IAgencyDao;

public class ProvidersController implements Initializable {

    private ObservableList<Agency> list;

    @FXML
    private TableView<Agency> table;
    @FXML
    private TableColumn<Agency, String> nombre;
    @FXML
    private TableColumn<?, ?> bultos;
    @FXML
    private TableColumn<?, ?> recargo_combustible;
    @FXML
    private TableColumn<?, ?> minimo_reembolso;
    @FXML
    private TableColumn<?, ?> comision;
    @FXML
    private TableColumn<Agency, String> envio_grande;
    @FXML
    private TextField inputNombre;
    @FXML
    private TextField inputBultos;
    @FXML
    private TextField inputRecargo;
    @FXML
    private TextField inputMinimo;
    @FXML
    private TextField inputComision;
    @FXML
    private ComboBox<String> cmbGrande;
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

    private IAgencyDao agenciaDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConfigStage.setIcon(addButton, "add.png", 12);
        ConfigStage.setIcon(deleteButton, "delete.png", 12);
      
        
        ObservableList<String> cmbList = FXCollections.observableArrayList();

        cmbList.add("Sí");

        cmbList.add("No");

        
        cmbGrande.setItems(cmbList);

        cmbGrande.getSelectionModel().select("No");

        selections = new ArrayList<>();

        agenciaDao = new AgencyImpl();

        list = agenciaDao.getAgencies();

        addButton.setOnAction(e -> addProvider());

        deleteButton.setOnAction(e -> deleteProviders());

        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        bultos.setCellValueFactory(new PropertyValueFactory<>("bultos"));

        recargo_combustible.setCellValueFactory(new PropertyValueFactory<>("recargo_combustible"));

        minimo_reembolso.setCellValueFactory(new PropertyValueFactory<>("minimo_reembolso"));

        comision.setCellValueFactory(new PropertyValueFactory<>("comision"));

        envio_grande.setCellValueFactory(param -> {

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

        MenuItem borrar = new MenuItem("Borrar fila");

        borrar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                deleteProviders();
            }

        });

        table.setContextMenu(new ContextMenu(borrar));

    }

    private void addProvider() {

        String nom = inputNombre.getText().trim();

        ObservableList<String> nombreAgencias = agenciaDao.getAgencyNames();

        if (!nombreAgencias.contains(nom)) {

            int bult = 0;

            double recargo = 0;

            double minimoReem = 0;

            double comision = 0;

            try {
                if (!inputBultos.getText().isEmpty()) {
                    bult = Integer.parseInt(inputBultos.getText().trim());

                }
                if (!inputRecargo.getText().isEmpty()) {
                    recargo = Double.parseDouble(inputRecargo.getText().trim());

                }
                if (!inputMinimo.getText().isEmpty()) {
                    minimoReem = Double.parseDouble(inputMinimo.getText().trim());

                }
                if (!inputComision.getText().isEmpty()) {
                    comision = Double.parseDouble(inputComision.getText().trim());

                }
                String selected = cmbGrande.getSelectionModel().getSelectedItem();

                boolean envioGrande = false;

                if (selected.equals("Sí")) {

                    envioGrande = true;
                }

                Agency agencia = new Agency(nom, bult, recargo, minimoReem, envioGrande, comision);

                int idAgencia = agenciaDao.addAgency(agencia);

                if (idAgencia != -1) {

                    agencia.setAgencyId(idAgencia);

                    list.add(agencia);

                    inputNombre.clear();
                    inputBultos.clear();
                    inputRecargo.clear();
                    inputMinimo.clear();
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

        Agency selectedAgencia = table.getSelectionModel().getSelectedItem();

        AlertService alert = new AlertService((Alert.AlertType.CONFIRMATION), "Borrado de agencia", "Seguro que quiere eliminar la Agencia " + selectedAgencia.getName() +"?",
                "");

        ButtonType okButton = new ButtonType("Sí", ButtonBar.ButtonData.YES);

        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(okButton, noButton);

        alert.showAndWait().ifPresent(type -> {

            if (type == okButton) {

                ObservableList<Agency> allProviders = table.getItems();

                agenciaDao.deleteAgency(selectedAgencia.getAgencyId());

                allProviders.remove(selectedAgencia);

            } else {
                alert.close();
            }
        });

    }

}
