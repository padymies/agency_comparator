package tdt.ui.notes.form;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tdt.model.Note;
import tdt.services.AlertExceptionService;
import tdt.services.AlertService;
import tdt.services.ConfigStage;
import tdt.services.FileService;

public class NotesFormController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField pay;
    @FXML
    private TextField destinationName;
    @FXML
    private TextField destinationAddress;
    @FXML
    private TextField destinationPostalCode;
    @FXML
    private TextField destinationPhone;
    @FXML
    private TextField width;
    @FXML
    private TextField height;
    @FXML
    private TextField weight;
    @FXML
    private TextField refund;
    @FXML
    private TextField _long;
    @FXML
    private TextField bundles;
    @FXML
    private TextField txtCountry;
    @FXML
    private TextField txtDestinationCode;
    @FXML
    private TextField client;
    @FXML
    private TextField date;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClose;
    @FXML
    private TextField lbRef;

    private Note note;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConfigStage.setIcon(btnSave, "check.png", 18);
        ConfigStage.setIcon(btnClose, "cancel.png", 18);
    }

    public void transferAlbaran(Note note) {

        this.note = note;

        lbRef.setText(note.getRef());

        client.setText(note.getClient().trim());

        String dateNote = note.getDate().trim();

        DateFormat input = new SimpleDateFormat("ddMMyyyy");

        DateFormat output = new SimpleDateFormat("dd-MM-yyyy");

        try {
            String result = output.format(input.parse(dateNote));
            date.setText(result);
        } catch (ParseException ex) {
            date.setText(dateNote);
            AlertExceptionService alert = new AlertExceptionService("Parseo de fecha", "No se ha podido formatear la fecha", ex);

            alert.showAndWait();
        }

        email.setText(note.getEmail().trim());

        pay.setText(note.getPayWay().trim());

        txtCountry.setText(note.getCountry().trim());

        txtDestinationCode.setText(note.getDestinationPostalCode().trim());

        destinationName.setText(note.getDestinationName().trim());

        destinationAddress.setText(note.getDestinationAddress().trim());

        destinationPostalCode.setText(note.getDestinationCity().trim());

        destinationPhone.setText(note.getDestinationPhone().trim());

        width.setText(note.getHeight().trim());

        height.setText(note.getWidth().trim());

        weight.setText(note.getWeight().trim());

        refund.setText(note.getRefund().trim());

        _long.setText(note.getLong().trim());

        bundles.setText("1");
    }

    @FXML
    private void close(ActionEvent event) {
        anchorPane.getScene().getWindow().hide();
    }

    @FXML
    private void save(ActionEvent event) {

        if (!lbRef.getText().isEmpty() && !destinationAddress.getText().isEmpty()
                && !txtDestinationCode.getText().isEmpty() && !email.getText().isEmpty() && !destinationPhone.getText().isEmpty()
                && !destinationName.getText().isEmpty() && !destinationPostalCode.getText().isEmpty()) {

            if (!note.getRef().equals(lbRef.getText().trim())) {
                note.setNewRef(lbRef.getText().trim());
            }

            note.setClient(client.getText().trim());

            String newDate = date.getText().replace("-", "");

            note.setDate(newDate);

            note.setEmail(email.getText().trim());

            note.setDestinationPostalCode(txtDestinationCode.getText().trim());

            note.setDestinationName(destinationName.getText().trim());

            note.setDestinationAddress(destinationAddress.getText().trim());

            note.setDestinationPhone(destinationPhone.getText().trim());

            note.setCountry(txtCountry.getText().trim());

            note.setDestinationCity(destinationPostalCode.getText().trim());

            note.setPayWay(pay.getText().trim());

            note.setWeight(weight.getText().trim());

            note.setWidth(width.getText().trim());

            note.setRefund(refund.getText().trim());

            note.setLong(_long.getText().trim());

            note.setHeight(height.getText().trim());

            note.setBundles(bundles.getText().trim());

            FileService.updateNote(note);

            anchorPane.getScene().getWindow().hide();

        } else {

            AlertService alert = new AlertService(Alert.AlertType.ERROR, "Error en formulario", "No se han podido guardar los cambios, revise los errores.", "Los campos marcados con (*) son obligatorios");

            alert.showAndWait();
        }
    }
}
