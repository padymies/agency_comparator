package tdt.ui.notes;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tdt.db.dao.IAgencyDao;
import tdt.db.dao.IZoneDao;
import tdt.db.daoImpl.AgencyImpl;
import tdt.db.daoImpl.ZoneImpl;
import tdt.model.Note;
import tdt.model.Zone;
import tdt.services.AlertExceptionService;
import tdt.services.AlertService;
import tdt.services.ComparatorService;
import tdt.services.ConfigStage;
import tdt.services.FileService;
import tdt.services.NoteService;
import tdt.services.ValidatorService;
import tdt.ui.comparator_output.OutputController;
import tdt.ui.notes.form.NotesFormController;

public class NotesController implements Initializable {

    @FXML
    private ListView<Note> listView;

    @FXML
    private Button btnComparator;

    @FXML
    private TextField txtFind;

    private IAgencyDao agencyDao;

    private IZoneDao zoneDao;

    private ObservableList<String> agencyNames;

    private ObservableList<String> zoneNames;

    private FilteredList<Note> filteredList;
    @FXML
    private Label lbFilter;
    @FXML
    private CheckBox chkSelectAll;
    @FXML
    private Text noNotesText;
    @FXML
    private Label lbCountSelected;
    @FXML
    private Label lbselected;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image icon = new Image("file:resources/img/" + "filter.png");

        ImageView imgV = new ImageView(icon);

        imgV.setFitHeight(16);

        imgV.setFitWidth(16);

        lbFilter.setGraphic(imgV);

        ConfigStage.setIcon(btnComparator, "analisis.png", 26);

        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setCount();
                if (chkSelectAll.isSelected()) {
                    chkSelectAll.setSelected(false);
                    listView.getSelectionModel().clearSelection();
                }

            }

        });

        agencyDao = new AgencyImpl();

        agencyNames = agencyDao.getAgencyNames();

        zoneDao = new ZoneImpl();

        zoneNames = zoneDao.getZoneNames();

        txtFind.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.ENTER) {

                findNote();
            }
        });

        chkSelectAll.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    listView.getSelectionModel().selectAll();
                    listView.scrollTo(listView.getItems().size() - 1);
                } else {
                    listView.getSelectionModel().clearSelection();
                }
                setCount();
            }
        });

    }

    private void setCount() {
        int count = listView.getSelectionModel().getSelectedItems().size();
        if (count > 0) {
            lbCountSelected.setText(String.valueOf(count));
            lbselected.setVisible(true);
        } else {
            lbCountSelected.setText("");
            lbselected.setVisible(false);
        }
    }

    private void findNote() {

        String current = txtFind.getText();

        filteredList.setPredicate(data -> {

            if (current == null || current.isEmpty()) {
                return true;
            }

            String lowerCaseBusqueda = current.toLowerCase();

            String name = data.getDestinationName().toLowerCase();

            String city = data.getDestinationCity().toLowerCase();

            String zone = "";

            if (data.getZone() != null) {
                zone = data.getZone().getName().toLowerCase();
            } else {
                zone = "No se ha encontrado una Zona";
            }

            return (data.getRef().toLowerCase().matches("(.*)" + lowerCaseBusqueda + "(.*)")
                    || name.matches("(.*)" + lowerCaseBusqueda + "(.*)")
                    || zone.matches("(.*)" + lowerCaseBusqueda + "(.*)")
                    || city.matches("(.*)" + lowerCaseBusqueda + "(.*)"));
        });

    }

    public void trannsferList(ObservableList<Note> notes) {
        if (notes.size() > 0) {
            FXCollections.sort(notes, Comparator.comparing(note -> note.getRef()));
            parseCountryFailure(notes);
            noNotesText.setVisible(false);

            listView.setVisible(true);

            listView.setCellFactory((ListView<Note> param) -> new NoteCell());

            filteredList = new FilteredList<>(notes, data -> true);

            listView.setItems(filteredList);

        } else {
            listView.setVisible(false);
            noNotesText.setVisible(true);
        }

    }

    private void parseCountryFailure(ObservableList<Note> notes) {

        final String SPAIN_CODE = "724";

        notes.forEach(note -> {
            if (note.getCountry().equals(SPAIN_CODE)) {
                note.setCountry("España");
                FileService.updateNote(note);
            }
        });

    }

    @FXML
    private void compare(ActionEvent event) {


        ObservableList<Note> items = listView.getItems();
        items.forEach(item -> {
            FileService.updateNote(item); // SOBREESCRIBIMOS EL ARCHIVO CON LAS MODIFICACIONES HECHAS

        });
        ObservableList<Note> selectedRows = listView.getSelectionModel().getSelectedItems();

        int selectedCount = selectedRows.size();

        if (selectedRows.isEmpty()) {

            AlertService noSelectedInfo = new AlertService(Alert.AlertType.INFORMATION, "Info", "\nNo hay filas seleccionadas. ", "");

            noSelectedInfo.showAndWait();

        } else {

            ArrayList<Note> notes = new ArrayList<>();

            for (Note note : selectedRows) {
                boolean validation = ValidatorService.noteValidator(note);
                if (validation) {
                    notes.add(note);

                } 
            }

            ComparatorService service = new ComparatorService();

            if (notes.size() > 0) {

                service.noteCompare(notes);

                Map<String, List<Note>> result = notes.stream().filter(predicate -> predicate.getBEST_AGENCY() != null)
                        .collect(Collectors.groupingBy(Note::getBEST_AGENCY));

                boolean fileResult = FileService.writeOutFiles(result);

                if (fileResult && result.size() > 0) {

                    try {

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tdt/ui/comparator_output/output.fxml"));

                        Parent root1 = (Parent) fxmlLoader.load();

                        OutputController outputController = fxmlLoader.getController();

                        outputController.loadData(result);

                        ObservableList<Note> unprocessedNotes = listView.getItems().filtered(note -> note.getBEST_AGENCY() == null);

                        outputController.setUnprocessedNotes(unprocessedNotes);

                        Stage notesStage = new Stage();

                        ConfigStage.configStage(notesStage, "Albaranes comparados", Modality.APPLICATION_MODAL);

                        notesStage.setScene(new Scene(root1));

                        notesStage.show();

                        filteredList = new FilteredList<>(unprocessedNotes, data -> true);

                        listView.setItems(filteredList);
                        
                        FileService.overrideFile(unprocessedNotes);

                    } catch (IOException e) {
                        AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Resultado", e);

                        alert.showAndWait();
                    }

                }
                chkSelectAll.setSelected(false);
                setCount();
            }

        }
    }

    public class NoteCell extends ListCell<Note> {

        @Override
        public void updateItem(Note note, boolean empty) {

            super.updateItem(note, empty);

            if (note != null) {

                CellListViewBase cell = new CellListViewBase(note.getRef());

                cell.ref.setText(note.getRef());

                cell.txtDestinationName.setText(note.getDestinationName());

                cell.txtWeight.setText(note.getWeight());

                Zone zone = NoteService.setNoteZone(note, cell.lbZone, listView);

                note.setZone(zone);
                if (note.getZone() != null) {
                    cell.lbZone.setText(note.getZone().getName());

                }

                cell.lbPostalCode.setText(note.getDestinationPostalCode());

                cell.txtCountry.setText(note.getCountry());

                cell.txtCountry.focusedProperty().addListener((observable, oldValue, newValue) -> {

                    if (!newValue) {

                        note.setCountry(cell.txtCountry.getText());

                        FileService.updateNote(note);

                        Zone newZone = NoteService.setNoteZone(note, cell.lbZone, listView);

                        if (newZone != null) {

                            note.setZone(newZone);

                            cell.lbZone.setText(note.getZone().getName());

                        }
                    }

                });

                cell.lbZone.textProperty().addListener(new ChangeListener<Object>() {
                    @Override
                    public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {

                        updateState(cell);
                    }
                });

                cell.txtCity.setText(note.getDestinationCity());

                if (note.getBundles().equals("0")) {
                    cell.txtBundles.setText("1");
                    note.setBundles("1");
                } else {
                    cell.txtBundles.setText(note.getBundles().trim());
                }

                cell.txtBundles.focusedProperty().addListener((args, oldValue, newValue) -> {

                    if (!newValue) {

                        if (ValidatorService.integerValidate(cell.txtBundles)) {

                            note.setBundles(cell.txtBundles.getText());
                            FileService.updateNote(note);
                        }
                        updateState(cell);

                    }

                });
                cell.txtWeight.focusedProperty().addListener((args, oldValue, newValue) -> {

                    if (!newValue) {
                        if (ValidatorService.doubleValidate(cell.txtWeight)) {
                            note.setWeight(cell.txtWeight.getText().trim());
                            FileService.updateNote(note);
                        }
                        updateState(cell);
                    }

                });

                cell.btnEdit.setOnAction((ActionEvent event) -> {

                    showNoteForm(note);
                });

                cell.chkAgency.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                    if (newValue) {

                        cell.comboAgency.setDisable(false);

                        cell.comboAgency.setItems(agencyNames);

                    } else {

                        cell.comboAgency.setItems(null);

                        cell.comboAgency.setDisable(true);

                        Zone newZone = NoteService.setNoteZone(note, cell.lbZone, listView);

                        note.setZone(newZone);

                        if (newZone != null) {
                            cell.lbZone.setText(note.getZone().getName());
                        }
                        cell.lbZone.setStyle(null);

                        note.setBEST_AGENCY(null);

                    }
                });

                cell.checkBigShipment.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                    if (newValue) {
                        note.setBigShipment(true);
                    } else {
                        note.setBigShipment(false);
                    }
                });

                cell.comboAgency.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (newValue != null) {
                            // FORZADO AGENCIA
                            cell.lbZone.setText("Forzada a " + newValue);

                            cell.lbZone.getStyleClass().clear();

                            cell.lbZone.setStyle("-fx-text-fill: blue");

                            note.setBEST_AGENCY(newValue);

                            updateState(cell);

                        } else {
                            // NO FORZADO AGENCIA
                            NoteService.setNoteZone(note, cell.lbZone, listView);

                            note.setBEST_AGENCY(null);

                        }
                    }

                });
                setGraphic(cell);

                updateState(cell);

            } else {
                setGraphic(null); // ESTO ES IMPORTANTE PARA ACTUALIZAR LA LISTA. SINO, NUNCA SE ELIMINAN LAS FILAS QUE TENGAN QUE BORRARSE
            }

        }

        private void updateState(CellListViewBase cell) {
            if (!cell.lbZone.getText().equals("No se ha encontrado una Zona")
                    && ValidatorService.doubleValidate(cell.txtWeight)
                    && ValidatorService.integerValidate(cell.txtBundles)) {
                cell.state.setText("OK");
                cell.state.setStyle("-fx-text-fill:green");

            } else {
                cell.state.setText(" X");
                cell.state.setStyle("-fx-text-fill:red");
            }
        }
    }

    private void showNoteForm(Note note) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("form/notesForm.fxml"));

            Parent noteRoot = (Parent) fxmlLoader.load();

            Stage stageForm = new Stage();

            ConfigStage.configStage(stageForm, "Albaran: " + note.getRef(), Modality.APPLICATION_MODAL);

            NotesFormController noteFormController = fxmlLoader.getController();

            noteFormController.transferNote(note);

            stageForm.setScene(new Scene(noteRoot));

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
        protected final Label txtDestinationName;
        protected final Label lbZone;
        protected final TextField txtCountry;
        protected final Label txtCity;
        protected final Label lbPostalCode;
        protected final CheckBox chkAgency;
        protected final ComboBox comboAgency;
        protected final TextField txtWeight;
        protected final TextField txtBundles;
        protected final CheckBox checkBigShipment;
        protected final Button btnEdit;

        protected final Label state;

        private boolean validRef;
        private boolean validCP;
        private boolean validCountry;
        private boolean validCity;
        private boolean validName;
        private boolean validWeight;
        private boolean validBundles;

        public boolean isValidRef() {
            return validRef;
        }

        public boolean isValidCP() {
            return validCP;
        }

        public boolean isValidCountry() {
            return validCountry;
        }

        public boolean isValidCity() {
            return validCity;
        }

        public CellListViewBase(String id) {

            setId(id);

            ref = new Label();
            txtDestinationName = new Label();
            lbZone = new Label();
            txtCountry = new TextField();
            txtCity = new Label();
            lbPostalCode = new Label();
            chkAgency = new CheckBox();
            comboAgency = new ComboBox();
            txtBundles = new TextField();
            txtWeight = new TextField();
            checkBigShipment = new CheckBox();
            btnEdit = new Button();
            state = new Label();

            validRef = true;
            validCP = true;
            validCity = true;
            validCountry = true;
            validWeight = true;
            validBundles = true;
            validName = true;

            txtDestinationName.textProperty().addListener(listener -> {
                validName = !txtDestinationName.getText().isEmpty();
            });

            txtCity.textProperty().addListener(listener -> {
                validCity = !txtCity.getText().isEmpty();
            });
            ref.textProperty().addListener(listener -> {
                validRef = !ref.getText().isEmpty();
            });
            lbPostalCode.textProperty().addListener(listener -> {
                validCP = !lbPostalCode.getText().isEmpty();

            });
            txtCountry.textProperty().addListener(listener -> {
                validCountry = !txtCountry.getText().isEmpty();

                if (!validCountry || !validCity || !validCP || !validRef || !validName) {
                    this.getStyleClass().add("invalidRow");
                } else {
                    this.getStyleClass().clear();

                }

            });
            txtCity.textProperty().addListener(listener -> {
                validCity = !txtCity.getText().isEmpty();

            });
            txtWeight.textProperty().addListener(listener -> {
                double value = 0;
                try {
                    value = Double.parseDouble(txtWeight.getText().trim());
                    validWeight = !Double.isNaN(value);

                } catch (NumberFormatException e) {
                    //   AlertExceptionService alert = new AlertExceptionService("NotesController", "No se ha podido parsear el peso", e);

                    // alert.showAndWait();
                }

            });
            txtCity.textProperty().addListener(listener -> {
                validCity = !txtCity.getText().isEmpty();

            });

            setAlignment(javafx.geometry.Pos.CENTER_LEFT);
            setPrefHeight(35.0);
            setPrefWidth(1100.0);

            ref.setPrefHeight(25.0);
            ref.setPrefWidth(91.0);
            ref.setText("numero ref");
            ref.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            ref.setTextOverrun(javafx.scene.control.OverrunStyle.CLIP);
            HBox.setMargin(ref, new Insets(0.0, 0.0, 0.0, 10.0));

            txtDestinationName.setPrefHeight(17.0);
            txtDestinationName.setPrefWidth(166.0);
            txtDestinationName.setText("Nombre Destino");
            txtDestinationName.setTextOverrun(javafx.scene.control.OverrunStyle.CLIP);
            HBox.setMargin(txtDestinationName, new Insets(0.0, 0.0, 0.0, 10.0));

            lbZone.setPrefHeight(17.0);
            lbZone.setPrefWidth(176.0);
            lbZone.setText("Zona");
            HBox.setMargin(lbZone, new Insets(0.0, 0.0, 0.0, 10.0));

            txtCountry.setPrefHeight(25.0);
            txtCountry.setPrefWidth(100.0);
            txtCountry.setPromptText("Pais");
            txtCountry.setText("txtPais");
            HBox.setMargin(txtCountry, new Insets(0.0, 0.0, 0.0, 10.0));

            txtCity.setLayoutX(143.0);
            txtCity.setLayoutY(19.0);
            txtCity.setPrefHeight(17.0);
            txtCity.setPrefWidth(136.0);
            txtCity.setText("Poblacion");
            txtCity.setTextOverrun(javafx.scene.control.OverrunStyle.CLIP);
            HBox.setMargin(txtCity, new Insets(0.0, 0.0, 0.0, 10.0));

            lbPostalCode.setPrefHeight(17.0);
            lbPostalCode.setPrefWidth(55.0);
            lbPostalCode.setText("29003");
            HBox.setMargin(lbPostalCode, new Insets(0.0, 0.0, 0.0, 10.0));

            chkAgency.setMnemonicParsing(false);
            HBox.setMargin(chkAgency, new Insets(0.0, 0.0, 0.0, 10.0));

            comboAgency.setDisable(true);
            comboAgency.setPrefWidth(150.0);
            comboAgency.setPromptText("Forzar Agencia");
            HBox.setMargin(comboAgency, new Insets(0.0, 0.0, 0.0, 2.0));

            txtWeight.setLayoutX(885.0);
            txtWeight.setLayoutY(15.0);
            txtWeight.setPrefHeight(25.0);
            txtWeight.setPrefWidth(59.0);
            txtWeight.setPromptText("Peso");

            txtBundles.setPrefHeight(25.0);
            txtBundles.setPrefWidth(51.0);
            txtBundles.setText("Bultos");
            HBox.setMargin(txtBundles, new Insets(0.0, 0.0, 0.0, 10.0));

            checkBigShipment.setPrefWidth(15.0);
            HBox.setMargin(checkBigShipment, new Insets(0.0, 0.0, 0.0, 15.0));

            btnEdit.setAlignment(javafx.geometry.Pos.CENTER);
            btnEdit.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
            btnEdit.setMnemonicParsing(false);
            btnEdit.setPrefHeight(26.0);
            btnEdit.setPrefWidth(63.0);
            btnEdit.setText("Editar");
            btnEdit.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
            btnEdit.setFont(new Font(13.0));
            HBox.setMargin(btnEdit, new Insets(0.0, 0.0, 0.0, 50.0));
            setOpaqueInsets(new Insets(0.0));
            setPadding(new Insets(0.0, 5.0, 0.0, 5.0));

            state.setPrefHeight(17.0);
            state.setPrefWidth(35.0);
            HBox.setMargin(state, new Insets(0.0, 0.0, 0.0, 15.0));

            getChildren().add(ref);
            getChildren().add(txtDestinationName);
            getChildren().add(lbZone);
            getChildren().add(txtCountry);
            getChildren().add(txtCity);
            getChildren().add(lbPostalCode);
            getChildren().add(chkAgency);
            getChildren().add(comboAgency);
            getChildren().add(txtWeight);
            getChildren().add(txtBundles);
            getChildren().add(checkBigShipment);
            getChildren().add(btnEdit);
            getChildren().add(state);
        }

    }

}
