package tdt.main.providers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tdt.db.dao.IAgenciaDao;
import tdt.db.daoImpl.AgenciaImpl;
import tdt.model.Agencia;

public class ProvidersController implements Initializable {

    private ObservableList<Agencia> list;

    @FXML
    private TableView<Agencia> table;
    @FXML
    private TableColumn<Agencia, Integer> id;
    @FXML
    private TableColumn<Agencia, String> nombre;
    @FXML
    private TableColumn<?, ?> plazo_entrega;
    @FXML
    private TableColumn<?, ?> bultos;
    @FXML
    private TableColumn<?, ?> recargo_combustible;
    @FXML
    private TableColumn<?, ?> minimo_reembolso;
    @FXML
    private TableColumn<?, ?> envio_grande;
    @FXML
    private TextField inputNombre;
    @FXML
    private TextField inputPlazo;
    @FXML
    private TextField inputBultos;
    @FXML
    private TextField inputRecargo;
    @FXML
    private TextField inputMinimo;
    @FXML
    private TextField inputGrande;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private VBox vBox;
    @FXML
    private HBox hBox;
    
    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

    private ArrayList<Agencia> selections;

    private IAgenciaDao agenciaDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        selections = new ArrayList<>();
        // Get Providers from DB
        agenciaDao = new AgenciaImpl();

        list = agenciaDao.obtenerAgencias();

        addButton.setOnAction(e -> addProvider());

        deleteButton.setOnAction(e -> deleteProviders());

        // Set the rowData
        id.setCellValueFactory(new PropertyValueFactory<>("id_agencia"));

        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        plazo_entrega.setCellValueFactory(new PropertyValueFactory<>("plazo_entrega"));
    
        bultos.setCellValueFactory(new PropertyValueFactory<>("bultos"));
        
        recargo_combustible.setCellValueFactory(new PropertyValueFactory<>("recargo_combustible"));
        
        minimo_reembolso.setCellValueFactory(new PropertyValueFactory<>("minimo_reembolso"));
        
        envio_grande.setCellValueFactory(new PropertyValueFactory<>("envio_grande"));

        hBox.setPadding(new Insets(10, 10, 10, 10));
        
        hBox.setSpacing(10);

        // Populate tabview
        TableViewSelectionModel<Agencia> selectionModel = table.getSelectionModel();
       
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        table.setItems(list);

        table.setRowFactory(tv -> {

            TableRow<Agencia> row = new TableRow<>();

            row.setOnDragDetected(event -> {

                if (!row.isEmpty() && event.isControlDown()) {
                    Integer index = row.getIndex();

                    selections.clear();//important...

                    ObservableList<Agencia> items = table.getSelectionModel().getSelectedItems();

                    items.forEach((iI) -> {
                        selections.add(iI);
                    });

                    Dragboard drag = row.startDragAndDrop(TransferMode.MOVE);

                    drag.setDragView(row.snapshot(null, null));

                    ClipboardContent cc = new ClipboardContent();

                    cc.put(SERIALIZED_MIME_TYPE, index);

                    drag.setContent(cc);

                    event.consume();
                }
            });

            row.setOnDragOver(event -> {

                Dragboard drag = event.getDragboard();

                if (drag.hasContent(SERIALIZED_MIME_TYPE)) {

                    if (row.getIndex() != ((Integer) drag.getContent(SERIALIZED_MIME_TYPE))) {

                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

                        event.consume();
                    }
                }
            });

            row.setOnDragDropped(event -> {

                Dragboard drag = event.getDragboard();

                if (drag.hasContent(SERIALIZED_MIME_TYPE)) {

                    int dropIndex;
                    Agencia dI = null;

                    if (row.isEmpty()) {

                        dropIndex = table.getItems().size();
                    } else {

                        dropIndex = row.getIndex();

                        dI = table.getItems().get(dropIndex);
                    }
                    int delta = 0;

                    if (dI != null) {

                        while (selections.contains(dI)) {

                            delta = 1;

                            --dropIndex;

                            if (dropIndex < 0) {

                                dI = null;

                                dropIndex = 0;

                                break;
                            }
                            dI = table.getItems().get(dropIndex);
                        }
                    }

                    for (Agencia sI : selections) {
                        table.getItems().remove(sI);
                    }

                    if (dI != null) {
                        dropIndex = table.getItems().indexOf(dI) + delta;
                    } else if (dropIndex != 0) {
                        dropIndex = table.getItems().size();
                    }

                    table.getSelectionModel().clearSelection();

                    for (Agencia sI : selections) {
                        //draggedIndex = selections.get(i);
                        table.getItems().add(dropIndex, sI);
                        table.getSelectionModel().select(dropIndex);
                        dropIndex++;

                    }

                    event.setDropCompleted(true);
                    selections.clear();
                    event.consume();
                }
            });

            return row;
        });

    }

    private void addProvider() {
       
        String nom = inputNombre.getText().trim();
        
        int plazoEntrega = Integer.parseInt(inputPlazo.getText().trim());
        
        int bult = Integer.parseInt(inputBultos.getText().trim());
        
        double recargo = Double.parseDouble(inputRecargo.getText().trim());
        
        double minimoReem = Double.parseDouble(inputMinimo.getText().trim());
        
        boolean envioGrande = Boolean.parseBoolean(inputGrande.getText().trim());
        
        Agencia agencia = new Agencia(nom, plazoEntrega, bult, recargo, minimoReem, envioGrande);
        
        int idAgencia = agenciaDao.añadirAgencia(agencia);
        
        if (idAgencia != -1) {
          
            agencia.setId_agencia(idAgencia);
            
            list.add(agencia);
            
        } else {
           
            Alert alert = new Alert(AlertType.ERROR);
            
            alert.setTitle("Information Dialog");
            
            alert.setHeaderText(null);
            
            alert.setContentText("Error saving Agencia");
            
            alert.showAndWait();
        }
        inputNombre.clear();
        inputPlazo.clear();
        inputBultos.clear();
        inputRecargo.clear();
        inputMinimo.clear();
        inputGrande.clear();
    }

    private void deleteProviders() {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        
        alert.setContentText("Seguro que quiere eliminar la Agencia? ");
        
        alert.setTitle("Borrado de Agencia");
        
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        
        alert.getButtonTypes().setAll(okButton, noButton);
        
        alert.showAndWait().ifPresent(type -> {
        
            if (type == okButton) {
                
                ObservableList<Agencia> allProviders = table.getItems();
                
                Agencia selectedAgencia = table.getSelectionModel().getSelectedItem();
                
                agenciaDao.borrarAgencia(selectedAgencia.getId_agencia());
                
                allProviders.remove(selectedAgencia);

            } else {
                alert.close();
            }
        });

    }

}
