
package tdt.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tdt.model.MappedFileModel;

public class MapFileService {
    
    private final PropertyService propsService;
    private ObservableList<MappedFileModel> mappedFileObject;
    
    public MapFileService() {
        propsService = new PropertyService();
        
    }
    
    public ObservableList<MappedFileModel> getMapedFileList() {
        // TODO: Agregar resto de propiedades
        mappedFileObject = FXCollections.observableArrayList();
       
        return mappedFileObject;
    } 
}
