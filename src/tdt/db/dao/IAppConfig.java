
package tdt.db.dao;


public interface IAppConfig {
    
    
    public double getUrgencyPercent();
    
    public boolean updateUrgencyPercent(double percent);
    
    public boolean deleteUrgencyPercent();
    
    public String getPassAdmin();
    
    public boolean updatePassAdmin(String pass);
}
