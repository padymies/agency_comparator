
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
import tdt.db.dao.IProvinciaDao;
import tdt.db.dao.IZonaDao;
import tdt.db.daoImpl.ProvinciaImpl;
import tdt.db.daoImpl.ZonaImpl;
import tdt.model.Provincia;


public class ProvinciasController implements Initializable {

    @FXML
    private TableView<Provincia> table;
    @FXML
    private TableColumn<Provincia, String> nombre;
    @FXML
    private TableColumn<Provincia, String> codigo;
    @FXML
    private TableColumn<?, String> zona;

    private IProvinciaDao provinciaDao;

    private IZonaDao zonaDao;

    private ObservableList<Provincia> listaProvincias;

    private ObservableList<String> nombresZonas;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        zonaDao = new ZonaImpl();

        provinciaDao = new ProvinciaImpl();

        nombresZonas = zonaDao.obtenerNombresZonas();

        listaProvincias = provinciaDao.obtenerProvinciasZona();

        nombre.setCellValueFactory(new PropertyValueFactory("nombre"));

        codigo.setCellValueFactory(new PropertyValueFactory("codigo"));

        zona.setCellValueFactory(new PropertyValueFactory("nombreZona"));

        zona.setCellFactory(ComboBoxTableCell.forTableColumn(nombresZonas));

        zona.setOnEditCommit((value) -> {

            Provincia prov = (Provincia) value.getRowValue();
            
            provinciaDao.actualizarProvinciaZona(prov.getId(), value.getNewValue());
            
        });

        table.setItems(listaProvincias);

        table.setEditable(true);

    }

}