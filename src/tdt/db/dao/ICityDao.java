
package tdt.db.dao;

import javafx.collections.ObservableList;
import tdt.model.City;


public interface ICityDao {
    
    public ObservableList<City> getCities();
    
    public City getCity(int cityId);
    
    public boolean updateCity(City city);
    
    public int addCity(City city);
    
    public boolean deleteCity(int cityId);
    
    public ObservableList<City> getCitiesByZone(int idZona);
    
    public ObservableList<City> getCitiesNoZone();
    
    public ObservableList<City> getZoneCities();
    
    public boolean updateZoneCity(int cityId, String zoneName);
    
    
}
