
package tdt.db.dao;

import javafx.collections.ObservableList;
import tdt.model.Provincia;


public interface IProvinciaDao {
    
    public ObservableList<Provincia> obtenerProvincias();
    
    public Provincia obtenerProvincia(int provinciaId);
    
    public boolean actualizarProvincia(Provincia provincia);
    
    public int añadirProvincia(Provincia provincia);
    
    public boolean borrarProvincia(int provinciaId);
    
    public ObservableList<Provincia> obtenerProvinciasDeZona(int idZona);
    
    public ObservableList<Provincia> obtenerProvinciasSinZonaAsociada();
    
    public ObservableList<Provincia> obtenerProvinciasZona();
    
    public boolean actualizarProvinciaZona(int id_provincia, String nombre_zona);
    
    
}
