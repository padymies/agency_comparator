
package tdt.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tdt.services.ConfigStage;

public class App extends Application {
    
    
    @Override
    public void start(Stage stage) throws Exception {
      
      
        Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));
        
        ConfigStage.configStage(stage, "TDT Profesional", null);
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        stage.show();
    }


    public static void main(String[] args) {
      launch(args);
    }
    
}