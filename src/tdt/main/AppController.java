/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.main;

import java.io.File;
import java.net.URL;
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
        
        
    } catch(Exception e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void openFileChooser(ActionEvent event) {
        fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
        String fileName = file.getName();
        System.out.println(fileName);
            
        }
    }

 
    
}
