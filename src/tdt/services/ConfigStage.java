package tdt.services;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfigStage {

    public static void configStage(Stage stage, String title, Modality modality) {
        if (modality != null) {
            stage.initModality(modality);
        }

        stage.getIcons().add(0, new Image("file:resources/img/tdt.jpg"));

        stage.setTitle(title);

        stage.setResizable(false);
    }

    public static void setIcon(Button b, String imgName, int size) {

        Image icon = new Image("file:resources/img/" + imgName);

        ImageView imgV = new ImageView(icon);

        imgV.setFitHeight(size);

        imgV.setFitWidth(size);

        b.setGraphic(imgV);
    }
}
