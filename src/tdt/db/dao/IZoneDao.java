package tdt.db.dao;

import javafx.collections.ObservableList;
import tdt.model.Zone;

public interface IZoneDao {

    public ObservableList<Zone> getZonesUI();

    public Zone getZone(int zoneId);
    
    public Zone getZoneByCity(String postalCode);
    
    public Zone getZoneByCountry(String countryName);

    public int addZone(Zone zone);

    public boolean updateZone(Zone zone);

    public boolean deleteZone(int zoneId);

    public ObservableList<String> getZoneNames();
    
    
}
