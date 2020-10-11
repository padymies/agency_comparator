package tdt.ui.exclusions;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import tdt.db.dao.IAgencyDao;
import tdt.db.dao.IExclusionDao;
import tdt.db.daoImpl.AgencyImpl;
import tdt.db.daoImpl.ExclusionsImpl;
import tdt.model.Exclusion;
import tdt.services.AlertService;
import tdt.services.ConfigStage;

public class ExclusionsController implements Initializable {

    final String INCLUSION = "Forzar envío por esta agencia";

    final String EXCLUSION = "Excluir esta agencia";

    final String NO_SEND = "Código postal sin envío permitido";

    @FXML
    private TableColumn<Exclusion, String> postalCode;
    @FXML
    private TableColumn<Exclusion, String> agency;
    @FXML
    private TableColumn<Exclusion, String> action;
    @FXML
    private TableView<Exclusion> tableEx;

    private IExclusionDao exclusionsDao;

    private IAgencyDao agencyDao;

    private ObservableList exclusionsList;

    private ObservableList<String> agencyNames;
    @FXML
    private TextField txtCP;
    @FXML
    private ComboBox<String> cmbAgency;
    @FXML
    private ComboBox<Integer> cmbAction;
    @FXML
    private Button btnAdd;
    @FXML
    private HBox hBox;
    @FXML
    private Button btnDelete;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConfigStage.setIcon(btnAdd, "add.png", 12);

        ConfigStage.setIcon(btnDelete, "delete.png", 12);

        agencyDao = new AgencyImpl();

        exclusionsDao = new ExclusionsImpl();

        exclusionsList = exclusionsDao.getExclusions();

        agencyNames = agencyDao.getAgencyNames();

        postalCode.setCellValueFactory(new PropertyValueFactory("postalCode"));

        postalCode.setCellFactory(TextFieldTableCell.forTableColumn());

        postalCode.setOnEditCommit(value -> {
            if (!value.getNewValue().trim().isEmpty()) {
                Exclusion ex = value.getRowValue();

                ex.setPostalCode(value.getNewValue());
                exclusionsDao.updateExclusions(ex);
            }

        });

        agency.setCellValueFactory(param -> {

            Exclusion ex = param.getValue();

            return new SimpleStringProperty(ex.getAgencyName());
        });

        ObservableList<String> comboList = FXCollections.observableArrayList();
        comboList.add("N/A");
        agencyNames.forEach(name -> {
            comboList.add(name);
        });

        agency.setCellFactory(t -> new ComboBoxTableCell(comboList) {
            @Override
            public void startEdit() {
                super.startEdit();
            }
        });

        agency.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Exclusion, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Exclusion, String> t) {
                Exclusion ex = t.getRowValue();
                //      if (ex.getInclusion_exclusion() == 0) {
                //        ex.setAgencyName("N/A");
                //      tableEx.refresh();
                //} else {
                ex.setAgencyName(t.getNewValue());
                //}
                exclusionsDao.updateExclusions(ex);
            }

        });
        agency.setEditable(true);

        ObservableList<Integer> options = FXCollections.observableArrayList();

        options.add(1);

        options.add(-1);

        options.add(0);

        agency.setCellFactory(ComboBoxTableCell.forTableColumn(agencyConverter(), agencyNames));

        action.setCellValueFactory(param -> {

            Exclusion ex = param.getValue();

            switch (ex.getInclusion_exclusion()) {
                case 1:
                    return new SimpleStringProperty(INCLUSION);
                case -1:
                    return new SimpleStringProperty(EXCLUSION);
                default:
                    return new SimpleStringProperty(NO_SEND);

            }

        });

          ObservableList<String> actionList = FXCollections.observableArrayList();

        actionList.add(INCLUSION);
        actionList.add(EXCLUSION);
        actionList.add(NO_SEND);

        action.setCellFactory(t -> new ComboBoxTableCell(actionList) {
            @Override
            public void startEdit() {
                super.startEdit();
            }
        });

        action.setOnEditCommit(value -> {
            Exclusion exclusion = value.getRowValue();
            int result = 0;
            if (value.getNewValue().equalsIgnoreCase(INCLUSION)) {
                result = 1;
            } else if (value.getNewValue().equalsIgnoreCase(EXCLUSION)) {
                result = -1;
            }
            exclusion.setInclusion_exclusion(result);

            exclusionsDao.updateExclusions(exclusion);
        });

        MenuItem delete = new MenuItem("Borrar fila");

        delete.setStyle("-fx-pref-width: 100");

        delete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                deleteExclusion(event);
            }

        });

        ContextMenu context = new ContextMenu();

        context.getItems().add(delete);

        tableEx.setContextMenu(context);

        tableEx.setItems(exclusionsList);

        tableEx.setEditable(true);

        exclusionsDao = new ExclusionsImpl();

        cmbAction.setConverter(accionConverter());

        cmbAction.setItems(options);

        cmbAgency.setItems(agencyNames);

        cmbAgency.getItems().add(0, "N/A");

    }

    private StringConverter accionConverter() {

        return new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {

                if (null == object) {
                    return NO_SEND;
                } else {
                    switch (object) {
                        case 1:
                            return INCLUSION;
                        case -1:
                            return EXCLUSION;
                        default:
                            return NO_SEND;
                    }
                }
            }

            @Override
            public Integer fromString(String string) {

                if (string.equalsIgnoreCase(INCLUSION)) {
                    return 1;
                } else if (string.equalsIgnoreCase(EXCLUSION)) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
    }

    private StringConverter agencyConverter() {

        return new StringConverter<String>() {
            @Override
            public String toString(String object) {

                if (null == object) {
                    return "N/A";
                }
                return object;
            }

            @Override
            public String fromString(String string) {

                if (string.equals("N/A")) {
                    return null;
                }
                return string;
            }
        };
    }

    @FXML
    private void addExclusion(ActionEvent event) {

        ObservableList<Exclusion> exclusions = exclusionsDao.getExclusions();

        if (!txtCP.getText().isEmpty() && !cmbAction.getSelectionModel().isEmpty()
                && (cmbAction.getSelectionModel().isSelected(2) || !cmbAgency.getSelectionModel().isEmpty())) {

            String cp = txtCP.getText().trim();

            boolean existName = !exclusions.stream().noneMatch(exclusion -> exclusion.getPostalCode().equals(cp));

            String agency = cmbAgency.getValue();

            int action = cmbAction.getValue();

            if (!existName) {

                if (agency.equals("N/A") && action != 0) {

                    AlertService alertEmptyAgency = new AlertService(Alert.AlertType.ERROR, "Creación de Exclusiones", "Debe seleccionar la agencia", "");
                    alertEmptyAgency.show();

                } else if (!agency.equals("N/A") && action == 0) {

                    AlertService alertNoSend = new AlertService(Alert.AlertType.ERROR, "Creación de Exclusiones", "Debe seleccionar sin agencia: N/A", "");
                    alertNoSend.show();

                } else {

                    Exclusion exclusion = new Exclusion(cp, agency, action);

                    int result = exclusionsDao.addExclusion(exclusion);

                    if (result != -1) {
                        tableEx.getItems().add(exclusion);
                    }
                    cmbAction.setValue(null);
                    cmbAgency.setValue(null);
                    txtCP.clear();
                }
            } else {
                AlertService alertRepeatName = new AlertService(Alert.AlertType.ERROR, "Creación de Exclusiones", "No se ha podido crear "
                        + "la exclusión.", "\n No puede haber un codigo postal duplicado.");
                alertRepeatName.show();

            }
        } else {
            AlertService alert = new AlertService(Alert.AlertType.ERROR, "Creación de Exclusiones", "No se ha podido crear "
                    + "una nueva exclusión.", "\n Debe rellenar los campos: \n\n-Código postal.\n\n-Agencia. (si la acción es 'Código postal sin envío permitido', no es necesario).\n\n-Acción. ");
            alert.showAndWait();
        }

    }

    @FXML
    private void deleteExclusion(ActionEvent event) {

        Exclusion item = (Exclusion) exclusionsList.get(tableEx.getSelectionModel().getSelectedIndex());

        if (item != null) {

            AlertService alert = new AlertService((Alert.AlertType.CONFIRMATION), "Borrado de Exclusión", "Seguro que quiere eliminar la exclusión para el código " + item.getPostalCode() + "?",
                    "");

            ButtonType okButton = new ButtonType("Sí", ButtonBar.ButtonData.YES);

            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

            alert.getButtonTypes().setAll(okButton, noButton);

            alert.showAndWait().ifPresent(type -> {

                if (type == okButton) {

                    exclusionsDao.deleteExclusion(item.getId());

                    tableEx.getItems().remove(item);

                } else {
                    alert.close();
                }
            });
        }
    }

}
