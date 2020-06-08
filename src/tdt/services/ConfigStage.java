
package tdt.services;

import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfigStage {

    public static void configStage(Stage stage, String title, Modality modality) {
        if (modality != null) {
            stage.initModality(modality);
        }
        stage.getIcons().add(0, new Image("file:resources/tdt.jpg"));

        stage.setTitle(title);
        
        stage.setResizable(false);
    }
}
