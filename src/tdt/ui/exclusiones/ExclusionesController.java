package tdt.ui.exclusiones;

import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import tdt.db.dao.IAgenciaDao;
import tdt.db.dao.IExclusionesDao;
import tdt.db.daoImpl.AgenciaImpl;
import tdt.db.daoImpl.ExclusionesImpl;
import tdt.model.Exclusion;
import tdt.services.AlertService;

public class ExclusionesController implements Initializable {

    @FXML
    private TableColumn<Exclusion, String> cp;
    @FXML
    private TableColumn<Exclusion, String> agencia;
    @FXML
    private TableColumn<Exclusion, Integer> accion;
    @FXML
    private TableView<Exclusion> tableEx;

    private IExclusionesDao exclusionesDao;

    private IAgenciaDao agenciaDao;

    private ObservableList listaExclusiones;

    private ObservableList<String> nombreAgencias;
    @FXML
    private TextField txtCP;
    @FXML
    private ComboBox<String> cmbAgencia;
    @FXML
    private ComboBox<Integer> cmbAccion;
    @FXML
    private Button btnAdd;
    @FXML
    private HBox hBox;
    @FXML
    private Button btnDelete;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        agenciaDao = new AgenciaImpl();

        exclusionesDao = new ExclusionesImpl();

        listaExclusiones = exclusionesDao.obtenerExclusiones();

        nombreAgencias = agenciaDao.obtenerNombresAgencias();

        cp.setCellValueFactory(new PropertyValueFactory("cp"));

        agencia.setCellValueFactory(new PropertyValueFactory("nombreAgencia"));

        accion.setCellValueFactory(new PropertyValueFactory("inclusion_exclusion"));

        ObservableList<Integer> options = FXCollections.observableArrayList();
        options.add(1);
        options.add(-1);
        options.add(0);

        agencia.setCellFactory(ComboBoxTableCell.forTableColumn(agenciaConverter(), nombreAgencias));

        accion.setCellFactory(ComboBoxTableCell.forTableColumn(accionConverter(), options));

        agencia.setOnEditCommit((value) -> {

        });
        accion.setOnEditCommit((value) -> {

        });

        MenuItem borrar = new MenuItem("Borrar fila");
        borrar.setStyle("-fx-pref-width: 100");
        borrar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                deleteExclusion(event);
            }

        });

        ContextMenu c = new ContextMenu();
        c.getItems().add(borrar);

        tableEx.setContextMenu(c);

        tableEx.setItems(listaExclusiones);

        tableEx.setEditable(
                true);

        exclusionesDao = new ExclusionesImpl();

        cmbAccion.setConverter(accionConverter());

        cmbAccion.setItems(options);

        cmbAgencia.setItems(nombreAgencias);
    }

    private StringConverter accionConverter() {

        final String INCLUSION = "Forzar envío por esta agencia";

        final String EXCLUSION = "Excluir esta agencia";

        final String NO_ENVIO = "Código postal sin envío permitido";

        return new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {

                if (null == object) {
                    return NO_ENVIO;
                } else {
                    switch (object) {
                        case 1:
                            return INCLUSION;
                        case -1:
                            return EXCLUSION;
                        default:
                            return NO_ENVIO;
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

    private StringConverter agenciaConverter() {

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
        if (!txtCP.getText().isEmpty() && !cmbAccion.getSelectionModel().isEmpty()
                && (cmbAccion.getSelectionModel().isSelected(2) || !cmbAgencia.getSelectionModel().isEmpty())) {

            String cp = txtCP.getText().trim();

            String agencia = cmbAgencia.getValue();

            int accion = cmbAccion.getValue();

            Exclusion ex = new Exclusion(cp, agencia, accion);

            int result = exclusionesDao.añadirExclusion(ex);

            if (result != -1) {
                tableEx.getItems().add(ex);
            }

        } else {
            AlertService alert = new AlertService(Alert.AlertType.ERROR, "Creación de Exclusiones", "No se ha podido crear "
                    + "una nueva exclusión.", "\n Debe rellenar los campos: \n\n-Código postal.\n\n-Agencia. (si la acción es 'Código postal sin envío permitido', no es necesario).\n\n-Acción. ");
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteExclusion(ActionEvent event) {

        Exclusion item = (Exclusion) listaExclusiones.get(tableEx.getSelectionModel().getSelectedIndex());

        if (item != null) {

            AlertService alert = new AlertService((Alert.AlertType.CONFIRMATION), "Borrado de Exclusión", "Seguro que quiere eliminar la exclusión para el código " + item.getCp() + "?",
                    "");

            ButtonType okButton = new ButtonType("Sí", ButtonBar.ButtonData.YES);

            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

            alert.getButtonTypes().setAll(okButton, noButton);

            alert.showAndWait().ifPresent(type -> {

                if (type == okButton) {

                    exclusionesDao.borrarExclusion(item.getId());

                    tableEx.getItems().remove(item);

                } else {
                    alert.close();
                }
            });
        }
    }

}
