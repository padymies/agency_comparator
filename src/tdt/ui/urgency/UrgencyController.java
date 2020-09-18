/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.ui.urgency;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tdt.db.dao.IAppConfig;
import tdt.db.daoImpl.AppConfigImpl;
import tdt.services.AlertService;
import tdt.services.ConfigStage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class UrgencyController implements Initializable {

    @FXML
    private TextField txtUrgency;
    @FXML
    private Button btnUrgency;

    private IAppConfig configDao;

    private double urgency;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConfigStage.setIcon(btnUrgency, "check.png", 12);
        configDao = new AppConfigImpl();

        urgency = configDao.getUrgencyPercent();

        txtUrgency.setText(String.valueOf(urgency));
    }

    @FXML
    private void saveUrgency(ActionEvent event) {
        try {
            double newUrgency = Double.parseDouble(txtUrgency.getText().trim());

            configDao.updateUrgencyPercent(newUrgency);

            ((Node) event.getSource()).getScene().getWindow().hide();

        } catch (NumberFormatException e) {
            AlertService alert = new AlertService(Alert.AlertType.ERROR, "Actualizar Porcentaje de urgencia", "El porcentaje de urgencia debe ser un número entero o decimal", "");
            alert.showAndWait();
        }
    }

}
