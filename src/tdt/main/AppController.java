package tdt.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tdt.main.albaranes.AlbaranesController;
import tdt.model.Albaran;
import tdt.services.ConfigStage;
import tdt.services.FileHandler;
import tdt.services.MyLogger;
import tdt.services.RegisterFactory;

public class AppController implements Initializable {

    @FXML
    private MenuBar menu;
    @FXML
    private Button providerList;

    private FileChooser fileChooser;

    private Stage stage;

    @FXML
    private MenuItem importFile;

    @FXML
    private Button fileChosoer;

    @FXML
    private TextFlow flow;

    MyLogger log;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        log = new MyLogger(flow);
    }

    @FXML
    private void showProviders(ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("providers/providers.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();

            // Create new Stage for new Window
            Stage stage = new Stage();

            ConfigStage.configStage(stage, "Agencias", Modality.APPLICATION_MODAL);

            this.stage = stage;

            stage.setScene(new Scene(root1));

            stage.show();

        } catch (IOException e) {
        }
    }

    @FXML
    private void openFileChooser(ActionEvent event) {

        ArrayList<String> registerList;

        Albaran reg;

        fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");

        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {

            FileHandler fh = new FileHandler();

            registerList = fh.extractRegisters(file);

            ObservableList<Albaran> listaAlbaranes = FXCollections.observableArrayList();

            for (String register : registerList) {

                reg = RegisterFactory.generarAlbaran(register);

                if (reg != null) {

                    listaAlbaranes.add(reg);

                    System.out.println(reg.toString());

                } else {

                    System.out.println("ERROR EN FORMATO DE FICHERO: " + file.getName());

                    break;
                }
            }

            if (listaAlbaranes.size() > 0) {

                mostrarAlbaranes(listaAlbaranes);

            }

        }
    }

    private void mostrarAlbaranes(ObservableList<Albaran> albaranes) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("albaranes/albaranes.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();

            AlbaranesController albaranesController = fxmlLoader.getController();

            albaranesController.trannsferLista(albaranes);

            Stage stage = new Stage();

            ConfigStage.configStage(stage, "Albaranes", Modality.APPLICATION_MODAL);

            stage.setScene(new Scene(root1));

            stage.show();

        } catch (IOException e) {
        }
    }

    @FXML
    private void configMapFile(ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mapFile/MapFile.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            // Create new Stage for new Window
            Stage stage = new Stage();

            ConfigStage.configStage(stage, "Posiciones de archivo", Modality.APPLICATION_MODAL);

            stage.setScene(new Scene(root1));

            stage.show();

        } catch (IOException e) {
            e.getMessage();
        }

    }

}
