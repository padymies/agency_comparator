package tdt.ui;

import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tdt.db.dao.IAppConfig;
import tdt.db.daoImpl.AppConfigImpl;
import tdt.model.Note;
import tdt.services.AlertExceptionService;
import tdt.services.AlertService;
import tdt.services.ConfigStage;
import tdt.services.FileService;
import tdt.services.logger.LoggerService;
import tdt.services.RegisterFactory;
import tdt.ui.notes.NotesController;

public class AppController implements Initializable {

    @FXML
    private MenuBar menu;

    @FXML
    private MenuItem importFile;

    @FXML
    private Button btnFileChooser;

    @FXML
    private Menu menuConf;

    @FXML
    private MenuItem agencyMenu;

    @FXML
    private MenuItem rateMenu;

    @FXML
    private MenuItem postalMenu;

    @FXML
    private MenuItem logMenu;

    @FXML
    private MenuItem cityMenu;

    @FXML
    private MenuItem exclusionMenu;

    @FXML
    private MenuItem urgencyMenu;

    @FXML
    private MenuItem closeMenu;

    private FileChooser fileChooser;

    private Stage stage;

    private Image enableIcon;

    private Image disableIcon;

    private ImageView enableImg;

    private ImageView disableImg;

    private ImageView backGroundImg;

    @FXML
    private AnchorPane pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConfigStage.setIcon(btnFileChooser, "file.png", 22);

        enableIcon = new Image("file:resources/img/enable.png");

        enableImg = new ImageView(enableIcon);

        enableImg.setFitHeight(10);

        enableImg.setFitWidth(10);

        disableIcon = new Image("file:resources/img/disable.png");

        disableImg = new ImageView(disableIcon);

        disableImg.setFitHeight(10);

        disableImg.setFitWidth(10);

        menuConf.setGraphic(disableImg);

    }

    @FXML
    private void showAgencies(ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("agencies/agencies.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();

            Stage agenciesStage = new Stage();

            ConfigStage.configStage(agenciesStage, "Agencias", Modality.APPLICATION_MODAL);

            agenciesStage.setScene(new Scene(root1));

            agenciesStage.show();

        } catch (Exception e) {

            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Agencias", e);

            alert.showAndWait();
        }
    }

    @FXML
    private void openFileChooser(ActionEvent event) {

        ArrayList<String> registerList;

        Note reg;

        fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");

        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {

            registerList = FileService.extractRegisters(file);

            ObservableList<Note> notesList = FXCollections.observableArrayList();

            for (String register : registerList) {

                reg = RegisterFactory.generateNote(register);

                if (reg != null) {

                    notesList.add(reg);

                } else {
                    break;
                }
            }

            showNotes(notesList);
        }
    }

    private void showNotes(ObservableList<Note> notes) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("notes/notes.fxml"));

            Parent root = (Parent) fxmlLoader.load();

            NotesController notesController = fxmlLoader.getController();

            notesController.trannsferList(notes);

            Stage notesStage = new Stage();

            ConfigStage.configStage(notesStage, "Albaranes", Modality.APPLICATION_MODAL);

            notesStage.setScene(new Scene(root));

            notesStage.show();

        } catch (Exception e) {
            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Albaranes", e);

            alert.showAndWait();
        }
    }

    @FXML
    private void configMapFile(ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mapFile/MapFile.fxml"));

            Parent root = (Parent) fxmlLoader.load();

            Stage stageMapFIle = new Stage();

            ConfigStage.configStage(stageMapFIle, "Posiciones de archivo", Modality.APPLICATION_MODAL);

            stageMapFIle.setScene(new Scene(root));

            stageMapFIle.show();

        } catch (Exception e) {
            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Variables de archivo", e);

            alert.showAndWait();
        }

    }

    @FXML
    private void showRates(ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rates/rates.fxml"));

            Parent root = (Parent) fxmlLoader.load();

            Stage stageMapFIle = new Stage();

            ConfigStage.configStage(stageMapFIle, "Tarifas", Modality.APPLICATION_MODAL);

            stageMapFIle.setScene(new Scene(root));

            stageMapFIle.show();

        } catch (Exception e) {

            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Tarifas", e);

            alert.showAndWait();
        }
    }

    @FXML
    private void showPostalCodes(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("postal/postal.fxml"));

            Parent root = (Parent) fxmlLoader.load();

            Stage postalStage = new Stage();

            ConfigStage.configStage(postalStage, "Códigos postales", Modality.APPLICATION_MODAL);

            postalStage.setScene(new Scene(root));

            postalStage.show();

        } catch (Exception e) {

            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Códigos postales", e);

            alert.showAndWait();
        }
    }

    @FXML
    private void showCities(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cities/cities.fxml"));

            Parent root = (Parent) fxmlLoader.load();

            Stage citiesStage = new Stage();

            ConfigStage.configStage(citiesStage, "Provincias", Modality.APPLICATION_MODAL);

            citiesStage.setScene(new Scene(root));

            citiesStage.show();

        } catch (Exception e) {

            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Provincias", e);

            alert.showAndWait();
        }
    }

    @FXML
    private void showExclusions(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exclusions/exclusions.fxml"));

            Parent root = (Parent) fxmlLoader.load();

            Stage exclusionStage = new Stage();

            ConfigStage.configStage(exclusionStage, "Exclusiones", Modality.APPLICATION_MODAL);

            exclusionStage.setScene(new Scene(root));

            exclusionStage.show();

        } catch (Exception e) {

            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Excepciones", e);

            alert.showAndWait();
        }

    }

    @FXML
    private void showUrgency(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("urgency/urgency.fxml"));

            Parent root = (Parent) fxmlLoader.load();

            Stage urgencyStage = new Stage();

            ConfigStage.configStage(urgencyStage, "Porcentaje de urgencia", Modality.APPLICATION_MODAL);

            urgencyStage.setScene(new Scene(root));

            urgencyStage.show();

        } catch (Exception e) {

            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Porcentaje de urgencia", e);

            alert.showAndWait();
        }
    }

    @FXML
    private void logIn(ActionEvent event) {

        IAppConfig appDao = new AppConfigImpl();

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logForm.fxml"));

            LogFormController loginController = new LogFormController() {

                @Override
                public void login() {

                    String password = appDao.getPassAdmin();

                    if (password != null) {

                        if (!pass.getText().isEmpty() && pass.getText().equals(password)) {
                            logedIn();
                        } else {
                            AlertService alert = new AlertService(Alert.AlertType.ERROR, "Error en inicio de sesión", "La contraseña introducida no es correcta", "");
                            alert.show();
                        }
                    }
                }
            };

            fxmlLoader.setController(loginController);

            Parent root = (Parent) fxmlLoader.load();

            Stage loginStage = new Stage();

            ConfigStage.configStage(loginStage, "Administrador", Modality.APPLICATION_MODAL);

            loginStage.setScene(new Scene(root));

            loginStage.show();

        } catch (Exception e) {

            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de inicio de sesión", e);

            alert.showAndWait();
        }

    }

    @FXML
    private void logout(ActionEvent event) {

        menuConf.setGraphic(disableImg);
        agencyMenu.setVisible(false);
        exclusionMenu.setVisible(false);
        postalMenu.setVisible(false);
        postalMenu.setVisible(false);
        cityMenu.setVisible(false);
        rateMenu.setVisible(false);
        urgencyMenu.setVisible(false);
        closeMenu.setVisible(false);
        logMenu.setVisible(true);

    }

    private void logedIn() {

        menuConf.setGraphic(enableImg);
        agencyMenu.setVisible(true);
        exclusionMenu.setVisible(true);
        postalMenu.setVisible(true);
        postalMenu.setVisible(true);
        cityMenu.setVisible(true);
        rateMenu.setVisible(true);
        urgencyMenu.setVisible(true);
        closeMenu.setVisible(true);
        logMenu.setVisible(false);
    }

}
