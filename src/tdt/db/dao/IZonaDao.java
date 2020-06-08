package tdt.db.dao;

import javafx.collections.ObservableList;
import tdt.model.Zona;

public interface IZonaDao {

    public ObservableList<Zona> obtenerZonasUI();

    public Zona obtenerZona(int idZona);
    
    public Zona obtenerZonaPorProvincia(String cp);
    
    public Zona obtenerZonaPorPais(String nombrePais);

    public int añadirZona(Zona zona);

    public boolean actualizarZona(Zona zona);

    public boolean borrarZona(int idZona);

    public ObservableList<String> obtenerNombresZonas();
    
    
}
