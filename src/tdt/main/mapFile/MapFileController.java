/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.main.mapFile;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tdt.db.dao.IVariableArchivoDao;
import tdt.db.daoImpl.VariableArchivoImpl;
import tdt.model.VariableArchivo;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class MapFileController implements Initializable {

    @FXML
    private TableView<VariableArchivo> mapFileTable;
    @FXML
    private TableColumn<VariableArchivo, String> key;
    @FXML
    private TableColumn<VariableArchivo, Integer> start;
    @FXML
    private TableColumn<VariableArchivo, Integer> end;

    private IVariableArchivoDao variableDao;

    private ObservableList<VariableArchivo> list;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        key.setCellValueFactory(new PropertyValueFactory<>("key"));
        
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        
        end.setCellValueFactory(new PropertyValueFactory<>("end"));

        variableDao = new VariableArchivoImpl();

        list = variableDao.ObservableVariableArchivo();
        
        mapFileTable.setItems(list);
    }

}
