
package tdt.db.dao;

import javafx.collections.ObservableList;
import tdt.model.Exclusion;


public interface IExclusionDao {
    
    
    public ObservableList<Exclusion> getExclusions();
    
    public Exclusion getExclusion(int id);
    
    public Exclusion getExclusionByPostalCode(String cp);

    public int addExclusion(Exclusion exclusion);

    public boolean updateExclusions(Exclusion exclusion);

    public boolean deleteExclusion(int exclusionId); 
}
