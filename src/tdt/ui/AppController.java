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
import tdt.model.Albaran;
import tdt.services.AlertExceptionService;
import tdt.services.AlertService;
import tdt.services.ConfigStage;
import tdt.services.FileService;
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
    private MenuItem menuAgencias;

    @FXML
    private MenuItem menuTarifas;

    @FXML
    private MenuItem menuPostales;

    @FXML
    MenuItem log;

    @FXML
    private MenuItem menuProvincias;

    @FXML
    private MenuItem menuExclusiones;

    @FXML
    private MenuItem menuUrgencia;

    private FileChooser fileChooser;

    private Stage stage;
    @FXML
    private Menu menuConf;
    @FXML
    private MenuItem close;

    private Image enableIcon;

    private Image disableIcon;

    private ImageView enableImg;

    private ImageView disableImg;

    private ImageView fondo;

    @FXML
    private AnchorPane pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConfigStage.setIcon(fileChosoer, "file.png", 22);

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
    private void showProviders(ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("providers/providers.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();

            ConfigStage.configStage(stage, "Agencias", Modality.APPLICATION_MODAL);

            stage.setScene(new Scene(root1));

            stage.show();

        } catch (IOException e) {
            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Agencias", e);

            alert.showAndWait();
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
            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Albaranes", e);

            alert.showAndWait();
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
            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Variables de archivo", e);

            alert.showAndWait();
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
            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Tarifas", e);

            alert.showAndWait();
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
            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Códigos postales", e);

            alert.showAndWait();
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
            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Provincias", e);

            alert.showAndWait();
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
            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Excepciones", e);

            alert.showAndWait();
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
            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de Porcentaje de urgencia", e);

            alert.showAndWait();
        }
    }

    @FXML
    private void logIn(ActionEvent event) {

        IAppConfig appDao = new AppConfigImpl();

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logForm.fxml"));

            LogFormController contr = new LogFormController() {

                @Override
                public void login() {

                    String password = appDao.getPassAdmin();

                    if (password != null) {

                        if (!pass.getText().isEmpty() && pass.getText().equals(password)) {
                            logedIn();
                        } else {
                            AlertService alert = new AlertService(Alert.AlertType.ERROR, "Error en inicio de sesión", "La contraseña introducida no es correcta", "");
                            alert.showAndWait();
                        }
                    }
                }
            };

            fxmlLoader.setController(contr);

            Parent root = (Parent) fxmlLoader.load();

            Stage stageMapFIle = new Stage();

            ConfigStage.configStage(stageMapFIle, "Administrador", Modality.APPLICATION_MODAL);

            stageMapFIle.setScene(new Scene(root));

            stageMapFIle.show();

        } catch (IOException e) {
            AlertExceptionService alert = new AlertExceptionService("Carga de ventanas", "No se ha podido abrir la ventana de inicio de sesión", e);

            alert.showAndWait();
        }

    }

    @FXML
    private void logout(ActionEvent event) {

        menuConf.setGraphic(disableImg);
        menuAgencias.setVisible(false);
        menuExclusiones.setVisible(false);
        menuPostales.setVisible(false);
        menuPostales.setVisible(false);
        menuProvincias.setVisible(false);
        menuTarifas.setVisible(false);
        menuUrgencia.setVisible(false);
        close.setVisible(false);
        log.setVisible(true);

    }

    private void logedIn() {

        menuConf.setGraphic(enableImg);
        menuAgencias.setVisible(true);
        menuExclusiones.setVisible(true);
        menuPostales.setVisible(true);
        menuPostales.setVisible(true);
        menuProvincias.setVisible(true);
        menuTarifas.setVisible(true);
        menuUrgencia.setVisible(true);
        close.setVisible(true);
        log.setVisible(false);
    }

}
