
package tdt.ui.postales;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tdt.db.dao.IProvinciaDao;
import tdt.db.daoImpl.ProvinciaImpl;
import tdt.model.Provincia;


public class PostalesController implements Initializable {

    @FXML
    private TableView<Provincia> tablaProvincias;
    @FXML
    private TableColumn<Provincia, String>provincia;
    @FXML
    private TableColumn<Provincia, String> codigo;

    private IProvinciaDao provinciaDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        provinciaDao = new ProvinciaImpl();
        
        ObservableList<Provincia> listaProvincias = provinciaDao.obtenerProvincias();
        
        provincia.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        
        tablaProvincias.setItems(listaProvincias);
    }    

    
}
