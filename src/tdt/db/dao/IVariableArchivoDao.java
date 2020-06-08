
package tdt.db.dao;

import java.util.HashMap;
import javafx.collections.ObservableList;
import tdt.model.VariableArchivo;


public interface IVariableArchivoDao {

    public HashMap<String, VariableArchivo> HashMapVariableArchivo();
   
    public ObservableList<VariableArchivo> ObservableVariableArchivo();

    public boolean actualizarVariableArchivo(VariableArchivo albaran);
    
    public VariableArchivo getVariable(String key);

}
