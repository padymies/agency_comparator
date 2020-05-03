/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.services;

import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */
public class ConfigStage {

    public static void configStage(Stage stage, String title, Modality modality) {
        if (modality != null) {
            stage.initModality(modality);
        }
        stage.getIcons().add(0, new Image("file:resources/tdt.jpg"));

        stage.setTitle(title);
    }
}
