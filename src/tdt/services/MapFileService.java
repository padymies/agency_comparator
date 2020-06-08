
package tdt.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tdt.model.VariableArchivo;

public class MapFileService {
    
    private ObservableList<VariableArchivo> mappedFileObject;
    
    public MapFileService() {}
    
    public ObservableList<VariableArchivo> getMapedFileList() {

        mappedFileObject = FXCollections.observableArrayList();
       
        return mappedFileObject;
    } 
}
