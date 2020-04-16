/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tdt.services.FileHandler;

/**
 *
 * @author Usuario
 */
public class App extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));
        stage.getIcons().add(0, new Image("file:resources/tdt.jpg"));
        stage.setTitle("TDT Profesional");
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileHandler fh = new FileHandler();
        fh.getTextFile();
        
        launch(args);
    }
    
}
