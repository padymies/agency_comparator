package tdt.ui.albaranes.form;

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

public class AlbaranFormController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField pago;
    @FXML
    private TextField nomreDestino;
    @FXML
    private TextField direcDestino;
    @FXML
    private TextField poblacionDestino;
    @FXML
    private TextField tfnoDestino;
    @FXML
    private TextField ancho;
    @FXML
    private TextField alto;
    @FXML
    private TextField peso;
    @FXML
    private TextField reembolso;
    @FXML
    private TextField largo;
    @FXML
    private TextField bultos;
    private Button btClose;
    @FXML
    private TextField txtPais;
    @FXML
    private TextField postalDestino;
    @FXML
    private TextField cliente;
    @FXML
    private TextField fecha;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btClose1;
    @FXML
    private TextField lbRef;

    private Note albaran;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConfigStage.setIcon(btnGuardar, "check.png", 18);
        ConfigStage.setIcon(btClose1, "cancel.png", 18);
    }

    public void transferAlbaran(Note albaran) {

        this.albaran = albaran;

        lbRef.setText(albaran.getRef());

        cliente.setText(albaran.getClient().trim());

        String date = albaran.getDate().trim();

        DateFormat input = new SimpleDateFormat("ddMMyyyy");

        DateFormat output = new SimpleDateFormat("dd-MM-yyyy");

        try {
            String result = output.format(input.parse(date));
            fecha.setText(result);
        } catch (ParseException ex) {
            fecha.setText(date);
            AlertExceptionService alert = new AlertExceptionService("Parseo de fecha", "No se ha podido formatear la fecha", ex);

            alert.showAndWait();
        }

        email.setText(albaran.getEmail().trim());

        pago.setText(albaran.getPayWay().trim());

        txtPais.setText(albaran.getCountry().trim());

        postalDestino.setText(albaran.getDestinationPostalCode().trim());

        nomreDestino.setText(albaran.getDestinyName().trim());

        direcDestino.setText(albaran.getDestinationAddress().trim());

        poblacionDestino.setText(albaran.getDestinationCity().trim());

        tfnoDestino.setText(albaran.getDestinationPhone().trim());

        ancho.setText(albaran.getHeight().trim());

        alto.setText(albaran.getWidth().trim());

        peso.setText(albaran.getWeight().trim());

        reembolso.setText(albaran.getRefund().trim());

        largo.setText(albaran.getLong().trim());

        bultos.setText("1");
    }

    @FXML
    private void close(ActionEvent event) {
        anchorPane.getScene().getWindow().hide();
    }

    @FXML
    private void guardar(ActionEvent event) {

        if (!lbRef.getText().isEmpty() && !direcDestino.getText().isEmpty()
                && !postalDestino.getText().isEmpty() && !email.getText().isEmpty() && !tfnoDestino.getText().isEmpty()
                && !nomreDestino.getText().isEmpty() && !poblacionDestino.getText().isEmpty()) {

            if (!albaran.getRef().equals(lbRef.getText().trim())) {
                albaran.setNewRef(lbRef.getText().trim());
            }

            albaran.setClient(cliente.getText().trim());

            String newFe = fecha.getText().replace("-", "");

            albaran.setDate(newFe);

            albaran.setEmail(email.getText().trim());

            albaran.setDestinationPostalCode(postalDestino.getText().trim());

            albaran.setDestinyName(nomreDestino.getText().trim());

            albaran.setDestinationAddress(direcDestino.getText().trim());

            albaran.setDestinationPhone(tfnoDestino.getText().trim());

            albaran.setCountry(txtPais.getText().trim());

            albaran.setDestinationCity(poblacionDestino.getText().trim());

            albaran.setPayWay(pago.getText().trim());

            albaran.setWeight(peso.getText().trim());

            albaran.setWidth(ancho.getText().trim());

            albaran.setRefund(reembolso.getText().trim());

            albaran.setLong(largo.getText().trim());

            albaran.setHeight(alto.getText().trim());

            albaran.setBundles(bultos.getText().trim());

            FileService.actualizarAlbaran(albaran);

            anchorPane.getScene().getWindow().hide();

        } else {

            AlertService alert = new AlertService(Alert.AlertType.ERROR, "Error en formulario", "No se han podido guardar los cambios, revise los errores.", "Los campos marcados con (*) son obligatorios");

            alert.showAndWait();
        }
    }
}
