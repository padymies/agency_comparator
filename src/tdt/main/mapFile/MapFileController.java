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
import tdt.model.MappedFileModel;
import tdt.services.MapFileService;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class MapFileController implements Initializable {

    @FXML
    private TableView<MappedFileModel> mapFileTable;
    @FXML
    private TableColumn<MappedFileModel, String>key;
    @FXML
    private TableColumn<MappedFileModel, Integer> start;
    @FXML
    private TableColumn<MappedFileModel, Integer> end;
    
    private MapFileService service;
    
    private ObservableList<MappedFileModel> list;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       key.setCellValueFactory(new PropertyValueFactory<>("key")); 
       start.setCellValueFactory(new PropertyValueFactory<>("start")); 
       end.setCellValueFactory(new PropertyValueFactory<>("end")); 
        
       service = new MapFileService();
       list = service.getMapedFileList();
       mapFileTable.setItems(list);
    }    
    
}
