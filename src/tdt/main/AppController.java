/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.main;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tdt.model.Register;
import tdt.services.FileHandler;
import tdt.services.RegisterFactory;

/**
 *
 * @author Usuario
 */
public class AppController implements Initializable {

    @FXML
    private MenuBar menu;
    @FXML
    private Button providerList;
    @FXML
    private Button fileChoser;

    private FileChooser fileChooser;

    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void showProviders(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("providers/providers.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            this.stage = stage;
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openFileChooser(ActionEvent event) {
        ArrayList<String> registerList;
        Register reg;
        fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            FileHandler fh = new FileHandler();
            registerList = fh.extractRegisters(file);
            for(String register : registerList) {
                System.out.println("Size => " + register.length());
                reg = RegisterFactory.generateRegister(register);
                System.out.println(reg.toString());
            }

        }
    }

}
