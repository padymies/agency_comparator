/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.ui.startConfig;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tdt.services.AlertExceptionService;
import tdt.services.AlertService;
import tdt.services.ConfigStage;
import tdt.services.PropertyService;
import tdt.ui.App;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class StartConfigController implements Initializable {

    @FXML
    private TextField txt1;
    @FXML
    private TextField txt2;
    @FXML
    private TextField txt3;
    @FXML
    private TextField txt4;
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ConfigStage.setIcon(btn, "save.png", 16);

    }

    @FXML
    private void saveIp(ActionEvent event) {
        if (!txt1.getText().isEmpty() && !txt2.getText().isEmpty() && !txt3.getText().isEmpty() && !txt4.getText().isEmpty()) {

            String ip = txt1.getText() + "." + txt2.getText() + "." + txt3.getText() + "." + txt4.getText();

            PropertyService props = new PropertyService();

            props.setProp("tdt.ip", ip);

            App p = new App();
            try {
                p.start(new Stage());
            } catch (Exception ex) {
                AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana Principal", ex);

                alert.showAndWait();
            }
            ((Node) event.getSource()).getScene().getWindow().hide();
        } else {

            AlertService alert = new AlertService(Alert.AlertType.ERROR, "Ip base de datos", "Debe rellenar todos los campos de texto", "");
            alert.showAndWait();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

}
