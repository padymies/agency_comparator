package tdt.ui.postales;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import tdt.db.daoImpl.CityImpl;
import tdt.model.City;
import tdt.services.AlertService;
import tdt.db.dao.ICityDao;

public class PostalesController implements Initializable {

    @FXML
    private TableView<City> tablaProvincias;
    @FXML
    private TableColumn<City, String> provincia;
    @FXML
    private TableColumn<City, String> codigo;

    private ICityDao provinciaDao;
    @FXML
    private HBox hBox;
    @FXML
    private TextField inputProv;
    @FXML
    private TextField inputCp;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        provinciaDao = new CityImpl();

        ObservableList<City> listaProvincias = provinciaDao.getCities();

        provincia.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        tablaProvincias.setItems(listaProvincias);

        tablaProvincias.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private void addProvincia(ActionEvent event) {
        
        if (!inputCp.getText().isEmpty() && !inputProv.getText().isEmpty()) {
            City p = new City(inputProv.getText().trim(), inputCp.getText().trim());
            System.out.println(p.toString());
            int result = provinciaDao.addCity(p);
            if(result != -1) {
                tablaProvincias.getItems().add(p);
            }
        } else {
             AlertService alert = new AlertService((Alert.AlertType.INFORMATION), "Añadir codigo postal", "Se deben rellenar los dos campos",
                    "");
             alert.showAndWait();
        }
    }

    @FXML
    private void deleteProv(ActionEvent event) {

        City seleccionado = tablaProvincias.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            // Eliminar
            AlertService alert = new AlertService((Alert.AlertType.CONFIRMATION), "Borrado de codigo postal", "Seguro que quiere eliminar el codigo postal " + seleccionado.getCode() + "?",
                    "");

            ButtonType okButton = new ButtonType("Sí", ButtonBar.ButtonData.YES);

            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

            alert.getButtonTypes().setAll(okButton, noButton);

            alert.showAndWait().ifPresent(type -> {

                if (type == okButton) {

                    boolean result = provinciaDao.deleteCity(seleccionado.getId());
                    
                    if(result) {
                    tablaProvincias.getItems().remove(seleccionado);
                        
                    }
                    
                } else {
                    alert.close();
                }
            });
        } else {
            // Mostrar alerta
             AlertService alert = new AlertService((Alert.AlertType.CONFIRMATION), "Borrado de codigo postal", "No se ha seleccionado un código postal para eliminar",
                    "");
             
             alert.showAndWait();
        }
    }

}
