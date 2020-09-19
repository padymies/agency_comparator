package tdt.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tdt.services.AlertExceptionService;
import tdt.services.ConfigStage;
import tdt.services.PropertyService;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    
        PropertyService props = new PropertyService();

        Object ip = props.getProps("tdt.ip");

        if (ip == null || ip.toString().isEmpty()) {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("startConfig/StartConfig.fxml"));

                ConfigStage.configStage(stage, "TDT: Configuración inicial", null);

                Scene scene = new Scene(root);

                stage.setScene(scene);

                stage.show();

            } catch (IOException e) {
                AlertExceptionService alert = new AlertExceptionService("Carga de ventana principal", "No se ha podido abrir la ventana principal", e);

                alert.showAndWait();
            }
        } else {

            Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));

            ConfigStage.configStage(stage, "TDT Profesional", null);

            Scene scene = new Scene(root);

            stage.setScene(scene);

            stage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
