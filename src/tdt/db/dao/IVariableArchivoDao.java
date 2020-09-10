
package tdt.db.dao;

import java.util.HashMap;
import javafx.collections.ObservableList;
import tdt.model.FileVariable;


public interface IVariableArchivoDao {

    public HashMap<String, FileVariable> HashMapVariableArchivo();
   
    public ObservableList<FileVariable> ObservableVariableArchivo();

    public boolean actualizarVariableArchivo(FileVariable albaran);
    
    public FileVariable getVariable(String key);

}
