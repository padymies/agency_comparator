/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.main.albaranes.form;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tdt.model.Albaran;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class AlbaranFormController implements Initializable {

    @FXML
    private TextField cliente;
    @FXML
    private TextField cuenta;
    @FXML
    private TextField fecha;
    @FXML
    private TextField servicio;
    @FXML
    private TextField email;
    @FXML
    private TextField departamento;
    @FXML
    private TextField pago;
    @FXML
    private TextField portes;
    @FXML
    private TextField moneda;
    @FXML
    private TextField variante;
    @FXML
    private TextField nombreRem;
    @FXML
    private TextField direcRem;
    @FXML
    private TextField nomreDestino;
    @FXML
    private TextField direcDestino;
    @FXML
    private TextField poblacionRem;
    @FXML
    private TextField viaDestino;
    @FXML
    private TextField numeroDestino;
    @FXML
    private TextField pisoDestino;
    @FXML
    private TextField tfnoDestino;
    @FXML
    private TextField documentos;
    @FXML
    private TextField retorno;
    @FXML
    private TextField paquetes;
    @FXML
    private TextField horaEntrada;
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
    @FXML
    private Button btSave;
    @FXML
    private Button btCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void transferAlbaran(Albaran albaran) {
        cliente.setText(albaran.getCliente());
        cuenta.setText(albaran.getCtaCliente());
        fecha.setText(albaran.getFecha());
        servicio.setText(albaran.getTypeServ());
        email.setText(albaran.getEmail());
        departamento.setText(albaran.getDepartamento());
        pago.setText(albaran.getFormaPago());
        portes.setText(albaran.getPortesDebidos());
        moneda.setText(albaran.getMoneda());
        variante.setText(albaran.getVariante());
        nombreRem.setText(albaran.getNombreRem());
        direcRem.setText(albaran.getDirecRem());
        nomreDestino.setText(albaran.getNombreDestino());
        direcDestino.setText(albaran.getDirecDestino());
        poblacionRem.setText(albaran.getPoblaDestino());
        viaDestino.setText(albaran.getViaDestino());
        numeroDestino.setText(albaran.getNumeroDestino());
        pisoDestino.setText(albaran.getPisoDestino());
        tfnoDestino.setText(albaran.getTfnoDestino());
        documentos.setText(albaran.getDocumentos());
        retorno.setText(albaran.getRetorno());
        paquetes.setText(albaran.getPaquetes());
        horaEntrada.setText(albaran.getHoraEntr());
        ancho.setText(albaran.getAlto());
        alto.setText(albaran.getAncho());
        peso.setText(albaran.getPeso());
        reembolso.setText(albaran.getReembolso());
        largo.setText(albaran.getLargo());
        bultos.setText(albaran.getBultos());
    }

    @FXML
    private void saveForm(ActionEvent event) {
    }

    @FXML
    private void cancel(ActionEvent event) {
    }
}
