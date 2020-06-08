
package tdt.db.dao;

import javafx.collections.ObservableList;
import tdt.model.Agencia;


public interface IAgenciaDao {

    public ObservableList<Agencia> obtenerAgencias();
    
    public ObservableList<String> obtenerNombresAgencias();

    public Agencia obtenerAgencia(int id);

    public int añadirAgencia(Agencia agencia);

    public boolean actualizarAgencia(Agencia agencia);

    public boolean borrarAgencia(int agenciaId);
    
  

}
