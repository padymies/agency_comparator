
package tdt.db.dao;

import java.util.HashMap;
import javafx.collections.ObservableList;
import tdt.model.FileVariable;


public interface IFileVariableDao {

    public HashMap<String, FileVariable> hashMapFileVariable();
   
    public ObservableList<FileVariable> observableFileVariable();

    public boolean updateFileVariable(FileVariable var);
    
    public FileVariable getVariable(String key);

}
