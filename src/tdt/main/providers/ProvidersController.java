/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import tdt.db.DBHandler;
import tdt.model.Provider;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ProvidersController implements Initializable {

    private ObservableList<Provider> list;

    @FXML
    private TableView<Provider> table;
    @FXML
    private TableColumn<Provider, Integer> id;
    @FXML
    private TableColumn<Provider, String> name;
    @FXML
    private TableColumn<Provider, String> cif;
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputCif;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private VBox vBox;
    @FXML
    private HBox hBox;
    @FXML

    private TableColumn<?, ?> cif1;
    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");
    private ArrayList<Provider> selections = new ArrayList<>();
    private DBHandler db;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Get Providers from DB
        db = new DBHandler();
        list = db.getProviders();
        addButton.setOnAction(e -> addProvider());
        deleteButton.setOnAction(e -> deleteProviders());
        // Set the rowData
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        cif.setCellValueFactory(new PropertyValueFactory<>("cif"));

        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        // Populate tabview
        TableViewSelectionModel<Provider> selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        table.setItems(list);

        table.setRowFactory(tv -> {
            TableRow<Provider> row = new TableRow<>();

            row.setOnDragDetected(event -> {

                if (!row.isEmpty() && event.isControlDown()) {
                    Integer index = row.getIndex();

                    selections.clear();//important...

                    ObservableList<Provider> items = table.getSelectionModel().getSelectedItems();

                    for (Provider iI : items) {
                        selections.add(iI);
                    }

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
                    if (row.getIndex() != ((Integer) drag.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        event.consume();
                    }
                }
            });

            row.setOnDragDropped(event -> {
                Dragboard drag = event.getDragboard();

                if (drag.hasContent(SERIALIZED_MIME_TYPE)) {

                    int dropIndex;
                    Provider dI = null;

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

                    for (Provider sI : selections) {
                        table.getItems().remove(sI);
                    }

                    if (dI != null) {
                        dropIndex = table.getItems().indexOf(dI) + delta;
                    } else if (dropIndex != 0) {
                        dropIndex = table.getItems().size();
                    }

                    table.getSelectionModel().clearSelection();

                    for (Provider sI : selections) {
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
        String name = inputName.getText().trim();
        String cif = inputCif.getText().trim();
        Provider p = new Provider(name, cif);
        int id = db.addProvider(p);
        if (id != 0) {
            p.setId(id);
            list.add(p);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Error saving Provider");
            alert.showAndWait();
        }
        inputName.clear();
        inputCif.clear();
    }

    private void deleteProviders() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Seguro que quiere eliminar la Agencia? ");
        alert.setTitle("Borrado de Agencia");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            System.out.println(" Type=>"+ type + " || " + ButtonType.OK);
            if (type == okButton) {
                ObservableList<Provider> allProviders = table.getItems();
                Provider selectedProvider = table.getSelectionModel().getSelectedItem();
                db.deleteProvider(selectedProvider);
                allProviders.remove(selectedProvider);
            
            } else {
                alert.close();
            }
        });

    }

}
