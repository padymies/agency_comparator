
package tdt.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import tdt.services.ConfigStage;


public abstract class LogFormController implements Initializable {

    @FXML
    protected PasswordField pass;
    @FXML
    protected Button btnLog;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConfigStage.setIcon(btnLog, "check.png", 16);
        
        pass.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.ENTER) {

                login();
                ((Node) event.getSource()).getScene().getWindow().hide();
            }
        });

    }

    @FXML
    private void logIn(ActionEvent event) {
        login();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public abstract void login();

}
