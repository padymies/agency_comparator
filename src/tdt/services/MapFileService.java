
package tdt.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tdt.model.VariableArchivo;

public class MapFileService {
    
    private final PropertyService propsService;
   
    private ObservableList<VariableArchivo> mappedFileObject;
    
    public MapFileService() {
    
        propsService = new PropertyService();
        
    }
    
    public ObservableList<VariableArchivo> getMapedFileList() {
        // TODO: Agregar resto de propiedades
        mappedFileObject = FXCollections.observableArrayList();
       
        return mappedFileObject;
    } 
}
