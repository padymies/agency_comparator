package tdt.db.dao;

import javafx.collections.ObservableList;
import tdt.model.Zone;

public interface IZonaDao {

    public ObservableList<Zone> obtenerZonasUI();

    public Zone obtenerZona(int idZona);
    
    public Zone obtenerZonaPorProvincia(String cp);
    
    public Zone obtenerZonaPorPais(String nombrePais);

    public int añadirZona(Zone zona);

    public boolean actualizarZona(Zone zona);

    public boolean borrarZona(int idZona);

    public ObservableList<String> obtenerNombresZonas();
    
    
}
