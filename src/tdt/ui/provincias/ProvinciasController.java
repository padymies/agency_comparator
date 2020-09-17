package tdt.ui.provincias;

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
import tdt.db.daoImpl.ProvinciaImpl;
import tdt.db.daoImpl.ZoneImpl;
import tdt.model.City;
import tdt.db.dao.ICityDao;
import tdt.db.dao.IZoneDao;

public class ProvinciasController implements Initializable {

    @FXML
    private TableView<City> table;
    @FXML
    private TableColumn<City, String> nombre;
    @FXML
    private TableColumn<City, String> codigo;
    @FXML
    private TableColumn<?, String> zona;

    private ICityDao provinciaDao;

    private IZoneDao zonaDao;

    private ObservableList<City> listaProvincias;

    private ObservableList<String> nombresZonas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        zonaDao = new ZoneImpl();

        provinciaDao = new ProvinciaImpl();

        nombresZonas = zonaDao.getZoneNames();
        
        nombresZonas.add(0, "Sin zona");

        listaProvincias = provinciaDao.getZoneCities();

        nombre.setCellValueFactory(new PropertyValueFactory("nombre"));

        codigo.setCellValueFactory(new PropertyValueFactory("codigo"));

        zona.setCellValueFactory(new PropertyValueFactory("nombreZona"));
        
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

        zona.setCellFactory(ComboBoxTableCell.forTableColumn(converter, nombresZonas));
        
        
        zona.setOnEditCommit((value) -> {

            City prov = (City) value.getRowValue();

            provinciaDao.updateZoneCity(prov.getId(), value.getNewValue());

        });

        table.setItems(listaProvincias);

        table.setEditable(true);

    }

}
