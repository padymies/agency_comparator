package tdt.ui.postal;

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
import tdt.db.dao.ICityDao;
import tdt.db.daoImpl.CityImpl;
import tdt.model.City;
import tdt.services.AlertService;

public class PostalController implements Initializable {

    @FXML
    private TableView<City> citiesTable;
    @FXML
    private TableColumn<City, String> city;
    @FXML
    private TableColumn<City, String> code;

    private ICityDao cityDao;
    @FXML
    private HBox hBox;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtPostalCode;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;

    private ObservableList<City> cityList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cityDao = new CityImpl();

        cityList = cityDao.getCities();

        city.setCellValueFactory(new PropertyValueFactory<>("name"));

        code.setCellValueFactory(new PropertyValueFactory<>("code"));

        citiesTable.setItems(cityList);

        citiesTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private void addCity(ActionEvent event) {

        if (!txtPostalCode.getText().isEmpty() && !txtCity.getText().isEmpty()) {
            City p = new City(txtCity.getText().trim(), txtPostalCode.getText().trim());

            int result = cityDao.addCity(p);
            if (result != -1) {
                citiesTable.getItems().add(p);
                txtCity.clear();
                txtPostalCode.clear();
            }
            
        } else {
            AlertService alert = new AlertService((Alert.AlertType.INFORMATION), "Añadir codigo postal", "Se deben rellenar los dos campos",
                    "");
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteCity(ActionEvent event) {

        City selected = citiesTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // Eliminar
            AlertService alert = new AlertService((Alert.AlertType.CONFIRMATION), "Borrado de codigo postal",
                    "Seguro que quiere eliminar el codigo postal " + selected.getName() + ": " + selected.getCode() + "?",
                    "");

            ButtonType okButton = new ButtonType("Sí", ButtonBar.ButtonData.YES);

            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

            alert.getButtonTypes().setAll(okButton, noButton);

            alert.showAndWait().ifPresent(type -> {

                if (type == okButton) {

                    boolean result = cityDao.deleteCity(selected.getId());

                    if (result) {
                        citiesTable.getItems().remove(selected);

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
