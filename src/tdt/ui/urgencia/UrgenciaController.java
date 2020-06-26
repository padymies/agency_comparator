/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.ui.urgencia;

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

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class UrgenciaController implements Initializable {

    @FXML
    private TextField txtUrgencia;
    @FXML
    private Button btnUrgencia;

    private IAppConfig configDao;

    private double urgencia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        configDao = new AppConfigImpl();

        urgencia = configDao.getPorcentajeUrgencia();

        txtUrgencia.setText(String.valueOf(urgencia));
    }

    @FXML
    private void saveUrgencia(ActionEvent event) {
        try {
            double newUrgencia = Double.parseDouble(txtUrgencia.getText().trim());

            configDao.actualizarPorcentajeUrgencia(newUrgencia);

            ((Node) event.getSource()).getScene().getWindow().hide();

        } catch (NumberFormatException e) {
            AlertService alert = new AlertService(Alert.AlertType.ERROR, "Actualizar Porcentaje de urgencia", "El porcentaje de urgencia debe ser un número entero o decimal", "");
            alert.showAndWait();
        }
    }

}
