
package tdt.db.dao;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import tdt.model.AgencyZone;
import tdt.model.Rate;
import tdt.model.RateComparator;


public interface IRateDao {

    public ObservableList<Rate> getRatesUI(int agencyId, int zoneId);
    
    public ArrayList<Rate> getRatesByKilos(int zoneId, int kg);

    public Rate getRate(int  agencyId, int zoneId, int kg);
    
    public ObservableList<Rate> getRateByAgencyZone(int zoneId, int agencyId);

    public boolean addRate(Rate rate);

    public boolean updateRate(Rate rate);
    
    public RateComparator ratesNotesCompare(double weight, int zoneId, int agencyId);
    
    public ObservableList<AgencyZone> getAgenciesByZone(int zoneId);
    
    public ObservableList<String> getAgenciesNameByZone(String zoneName);
    
    public boolean addZoneAgency(int agencyId, int zoneId, double increase, int deliveryTime, int maxKilos);
    
    public boolean updateZoneAgency(int agencyId, int zoneId, double increase, int deliveryTime, int maxKilos);
    
    public boolean deleteZoneAgency(int agencyId, int zoneId);
    
    public int getMaxKilo(int agencyId, int zoneId);
    
    public ObservableList<Rate> copyRate(String agencyName, String zoneName);
    
    public boolean pasteRate(int zoneId, int agencyId, ObservableList<Rate> values);
    
    public boolean deleteRatesFromAgency(int zoneId, int agencyId);
    
    public boolean deleteRate(Rate rate);
}
