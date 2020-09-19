
package tdt.model;


public class City {

    private int id;
    private String name;
    private String code;
    private int zoneId;
    private String zoneName;

    public City(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public City(int id, String name, String code, int zoneId, String zoneName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.zoneId = zoneId;
        this.zoneName = zoneName;
    }

    public City(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    @Override
    public String toString() {
        return "Provincia{" + "id=" + id + ", nombre=" + name + ", codigo=" + code + ", idZona=" + zoneId + ", nombreZona=" + zoneName + '}';
    }

}
