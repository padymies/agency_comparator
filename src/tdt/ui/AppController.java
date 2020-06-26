package tdt.ui;

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
import tdt.model.Albaran;
import tdt.services.ConfigStage;
import tdt.services.FileService;
import tdt.services.MyLogger;
import tdt.services.RegisterFactory;
import tdt.ui.albaranes.AlbaranesController;

public class AppController implements Initializable {

    @FXML
    private MenuBar menu;

    @FXML
    private MenuItem importFile;

    @FXML
    private Button fileChosoer;

    @FXML
    private TextFlow flow;

    @FXML
    private MenuItem menuAgencias;

    @FXML
    private MenuItem menuTarifas;

    @FXML
    private MenuItem menuPostales;

    private FileChooser fileChooser;

    private Stage stage;

    MyLogger log;
    @FXML
    private MenuItem menuProvincias;
    @FXML
    private MenuItem menuExclusiones;
    @FXML
    private MenuItem menuUrgencia;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        log = new MyLogger(flow);
    }

    @FXML
    private void showProviders(ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("providers/providers.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();

            ConfigStage.configStage(stage, "Agencias", Modality.APPLICATION_MODAL);

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

            registerList = FileService.extractRegisters(file);

            ObservableList<Albaran> listaAlbaranes = FXCollections.observableArrayList();

            for (String register : registerList) {

                reg = RegisterFactory.generarAlbaran(register);

                if (reg != null) {

                    listaAlbaranes.add(reg);

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

            Stage stageAlbaranes = new Stage();

            ConfigStage.configStage(stageAlbaranes, "Albaranes", Modality.APPLICATION_MODAL);

            stageAlbaranes.setScene(new Scene(root1));

            stageAlbaranes.show();

        } catch (IOException e) {
        }
    }

    @FXML
    private void configMapFile(ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mapFile/MapFile.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();

            Stage stageMapFIle = new Stage();

            ConfigStage.configStage(stageMapFIle, "Posiciones de archivo", Modality.APPLICATION_MODAL);

            stageMapFIle.setScene(new Scene(root1));

            stageMapFIle.show();

        } catch (IOException e) {
            e.getMessage();
        }

    }

    @FXML
    private void showTarifas(ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tarifas/Tarifas.fxml"));

            Parent root = (Parent) fxmlLoader.load();

            Stage stageMapFIle = new Stage();

            ConfigStage.configStage(stageMapFIle, "Tarifas", Modality.APPLICATION_MODAL);

            stageMapFIle.setScene(new Scene(root));

            stageMapFIle.show();

        } catch (IOException e) {
        }
    }

    @FXML
    private void showPostales(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("postales/postales.fxml"));

            Parent root = (Parent) fxmlLoader.load();

            Stage stageMapFIle = new Stage();

            ConfigStage.configStage(stageMapFIle, "Códigos postales", Modality.APPLICATION_MODAL);

            stageMapFIle.setScene(new Scene(root));

            stageMapFIle.show();

        } catch (IOException e) {
        }
    }

    @FXML
    private void showProvincias(ActionEvent event) {
          try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("provincias/provincias.fxml"));

            Parent root = (Parent) fxmlLoader.load();

            Stage stageMapFIle = new Stage();

            ConfigStage.configStage(stageMapFIle, "Provincias", Modality.APPLICATION_MODAL);

            stageMapFIle.setScene(new Scene(root));

            stageMapFIle.show();

        } catch (IOException e) {
        }
    }

    @FXML
    private void showExclusiones(ActionEvent event) {
           try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exclusiones/exclusiones.fxml"));

            Parent root = (Parent) fxmlLoader.load();

            Stage stageMapFIle = new Stage();

            ConfigStage.configStage(stageMapFIle, "Exclusiones", Modality.APPLICATION_MODAL);

            stageMapFIle.setScene(new Scene(root));

            stageMapFIle.show();

        } catch (IOException e) {
        }
        
    }

    @FXML
    private void showUrgencia(ActionEvent event) {
         try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("urgencia/urgencia.fxml"));

            Parent root = (Parent) fxmlLoader.load();

            Stage stageMapFIle = new Stage();

            ConfigStage.configStage(stageMapFIle, "Porcentaje de urgencia", Modality.APPLICATION_MODAL);

            stageMapFIle.setScene(new Scene(root));

            stageMapFIle.show();

        } catch (IOException e) {
        }
    }

}
