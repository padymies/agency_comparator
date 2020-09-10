
package tdt.db.dao;

import javafx.collections.ObservableList;
import tdt.model.Agency;


public interface IAgencyDao {

    public ObservableList<Agency> getAgencies();
    
    public ObservableList<String> getAgencyNames();

    public Agency getAgency(int id);

    public int addAgency(Agency agencia);

    public boolean updateAgency(Agency agencia);

    public boolean deleteAgency(int agenciaId);
    
  

}
