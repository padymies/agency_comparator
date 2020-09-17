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

        cliente.setText(albaran.getCliente().trim());

        String date = albaran.getFecha().trim();

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

        pago.setText(albaran.getFormaPago().trim());

        txtPais.setText(albaran.getPais().trim());

        postalDestino.setText(albaran.getPostalDestino().trim());

        nomreDestino.setText(albaran.getNombreDestino().trim());

        direcDestino.setText(albaran.getDirecDestino().trim());

        poblacionDestino.setText(albaran.getPoblaDestino().trim());

        tfnoDestino.setText(albaran.getTfnoDestino().trim());

        ancho.setText(albaran.getAlto().trim());

        alto.setText(albaran.getAncho().trim());

        peso.setText(albaran.getPeso().trim());

        reembolso.setText(albaran.getReembolso().trim());

        largo.setText(albaran.getLargo().trim());

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

            albaran.setCliente(cliente.getText().trim());

            String newFe = fecha.getText().replace("-", "");

            albaran.setFecha(newFe);

            albaran.setEmail(email.getText().trim());

            albaran.setPostalDestino(postalDestino.getText().trim());

            albaran.setNombreDestino(nomreDestino.getText().trim());

            albaran.setDirecDestino(direcDestino.getText().trim());

            albaran.setTfnoDestino(tfnoDestino.getText().trim());

            albaran.setPais(txtPais.getText().trim());

            albaran.setPoblaDestino(poblacionDestino.getText().trim());

            albaran.setFormaPago(pago.getText().trim());

            albaran.setPeso(peso.getText().trim());

            albaran.setAncho(ancho.getText().trim());

            albaran.setReembolso(reembolso.getText().trim());

            albaran.setLargo(largo.getText().trim());

            albaran.setAlto(alto.getText().trim());

            albaran.setBultos(bultos.getText().trim());

            FileService.actualizarAlbaran(albaran);

            anchorPane.getScene().getWindow().hide();

        } else {

            AlertService alert = new AlertService(Alert.AlertType.ERROR, "Error en formulario", "No se han podido guardar los cambios, revise los errores.", "Los campos marcados con (*) son obligatorios");

            alert.showAndWait();
        }
    }
}
