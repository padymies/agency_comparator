package tdt.ui.cities;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import tdt.db.dao.ICityDao;
import tdt.db.dao.IZoneDao;
import tdt.db.daoImpl.CityImpl;
import tdt.db.daoImpl.ZoneImpl;
import tdt.model.City;

public class CitiesController implements Initializable {

    @FXML
    private TableView<City> table;
    @FXML
    private TableColumn<City, String> name;
    @FXML
    private TableColumn<City, String> postalCode;
    @FXML
    private TableColumn<?, String> zone;

    private ICityDao cityDao;

    private IZoneDao zoneDao;

    private ObservableList<City> cityList;

    private ObservableList<String> zoneNames;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        zoneDao = new ZoneImpl();

        cityDao = new CityImpl();

        zoneNames = zoneDao.getZoneNames();
        
        zoneNames.add(0, "Sin zona");

        cityList = cityDao.getZoneCities();

        name.setCellValueFactory(new PropertyValueFactory("name"));

        postalCode.setCellValueFactory(new PropertyValueFactory("code"));

    zone.setCellValueFactory(new PropertyValueFactory("zoneName"));
        
        StringConverter<String> converter = new StringConverter<String>() {
            @Override
            public String toString(String object) {
               
               return object == null ? "Sin zona" : object;
            }

            @Override
            public String fromString(String string) {
                return string;
            }
        };

        zone.setCellFactory(ComboBoxTableCell.forTableColumn(converter, zoneNames));
        
        
        zone.setOnEditCommit((value) -> {

            City prov = (City) value.getRowValue();

            cityDao.updateZoneCity(prov.getId(), value.getNewValue());

        });

        table.setItems(cityList);

        table.setEditable(true);

    }

}
