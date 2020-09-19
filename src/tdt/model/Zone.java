
package tdt.model;


public class Zone {
   
    private int zoneId;
    private String name;
    private String country;
    private String description;

    public Zone(String name, String country, String description) {
        this.name = name;
        this.country = country;
        this.description = description;
    }

        
    public Zone(int zoneId, String name, String country, String description) {
        this.zoneId = zoneId;
        this.name = name;
        this.country = country;
        this.description = description;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
