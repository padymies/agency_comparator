
package tdt.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tdt.model.Register;
import tdt.services.FileHandler;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void showProviders(ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("providers/providers.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            
            // Create new Stage for new Window
            Stage stage = new Stage();
            
            this.stage = stage;

            stage.setScene(new Scene(root1));

            stage.show();

        } catch (IOException e) {
            e.getMessage();
        }
    }

    @FXML
    private void openFileChooser(ActionEvent event) {

        ArrayList<String> registerList;

        Register reg;

        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {

            FileHandler fh = new FileHandler();
            registerList = fh.extractRegisters(file);

            for (String register : registerList) {

                reg = RegisterFactory.generateRegister(register);

                if (reg != null) {
                    // TODO: Show Object in UI    
                    System.out.println(reg.toString());
                } else {
                    System.out.println("ERROR EN FORMATO DE FICHERO: " + file.getName());
                    break;
                }
            }

        }
    }

    @FXML
    private void configMapFile(ActionEvent event) {
        
         try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mapFile/MapFile.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();
            
            // Create new Stage for new Window
            Stage stage = new Stage();
            
            this.stage = stage;

            stage.setScene(new Scene(root1));

            stage.show();

        } catch (IOException e) {
            e.getMessage();
        }
        
    }

}
