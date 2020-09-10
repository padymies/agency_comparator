
package tdt.db.dao;

import javafx.collections.ObservableList;
import tdt.model.City;


public interface IProvinciaDao {
    
    public ObservableList<City> obtenerProvincias();
    
    public City obtenerProvincia(int provinciaId);
    
    public boolean actualizarProvincia(City provincia);
    
    public int añadirProvincia(City provincia);
    
    public boolean borrarProvincia(int provinciaId);
    
    public ObservableList<City> obtenerProvinciasDeZona(int idZona);
    
    public ObservableList<City> obtenerProvinciasSinZonaAsociada();
    
    public ObservableList<City> obtenerProvinciasZona();
    
    public boolean actualizarProvinciaZona(int id_provincia, String nombre_zona);
    
    
}
