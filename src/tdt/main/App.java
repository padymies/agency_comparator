
package tdt.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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


    public static void main(String[] args) {
      launch(args);
    }
    
}
