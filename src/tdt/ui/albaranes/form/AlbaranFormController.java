/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.ui.albaranes.form;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tdt.model.Albaran;
import tdt.services.FileService;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
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
    private TextField viaDestino;
    @FXML
    private TextField numeroDestino;
    @FXML
    private TextField pisoDestino;
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
    private Label lbRef;

    private Albaran albaran;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void transferAlbaran(Albaran albaran) {

        this.albaran = albaran;

        lbRef.setText(albaran.getRef());

        cliente.setText(albaran.getCliente().trim());

        String date = albaran.getFecha().trim();

        // TODO: Poner / entre fecha
        DateFormat input = new SimpleDateFormat("ddMMyyyy");
        DateFormat output = new SimpleDateFormat("dd-MM-yyyy");
        try {
            String result = output.format(input.parse(date));
            System.out.println(result);
            fecha.setText(result);
        } catch (ParseException ex) {
            fecha.setText(date);
            Logger.getLogger(AlbaranFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        email.setText(albaran.getEmail().trim());

        pago.setText(albaran.getFormaPago().trim());

        txtPais.setText(albaran.getPais().trim());

        postalDestino.setText(albaran.getPostalDestino().trim());

        nomreDestino.setText(albaran.getNombreDestino().trim());

        direcDestino.setText(albaran.getDirecDestino().trim());

        poblacionDestino.setText(albaran.getPoblaDestino().trim());

        viaDestino.setText(albaran.getViaDestino().trim());

        numeroDestino.setText(albaran.getNumeroDestino().trim());

        pisoDestino.setText(albaran.getPisoDestino().trim());

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

        albaran.setCliente(cliente.getText().trim());

        String newFe= fecha.getText().replace("-", "");
        System.out.println("=================================================================================="+newFe);
//        albaran.setFecha(fecha.getText().trim());

        albaran.setEmail(email.getText().trim());

        albaran.setPostalDestino(postalDestino.getText().trim());

        albaran.setNombreDestino(nomreDestino.getText().trim());

        albaran.setDirecDestino(direcDestino.getText().trim());

        albaran.setTfnoDestino(tfnoDestino.getText().trim());

        albaran.setViaDestino(viaDestino.getText().trim());

        albaran.setPais(txtPais.getText().trim());

        albaran.setNumeroDestino(numeroDestino.getText().trim());

        albaran.setPoblaDestino(poblacionDestino.getText().trim());

        albaran.setPisoDestino(pisoDestino.getText().trim());

        albaran.setFormaPago(pago.getText().trim());

        albaran.setPeso(peso.getText().trim());

        albaran.setAncho(ancho.getText().trim());

        albaran.setReembolso(reembolso.getText().trim());

        albaran.setLargo(largo.getText().trim());

        albaran.setAlto(alto.getText().trim());

        albaran.setBultos(bultos.getText().trim());

        FileService.actualizarAlbaran(albaran);

        anchorPane.getScene().getWindow().hide();
    }
}
