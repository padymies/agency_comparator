
package tdt.model;


public class Rate {

    private int kg;
    private int agencyId;
    private int zoneId;
    private int agencyZoneId;
    private double price;

    public Rate(int kg, double price) {
        this.kg = kg;
        this.price = price;
    }

    public Rate(int kg, int agencyId, int zoneId, double price) {
        this.kg = kg;
        this.agencyId = agencyId;
        this.zoneId = zoneId;
        this.price = price;
    }
    public Rate(int kg, int agencyId, int zoneId, int agencyZoneId, double price) {
        this.kg = kg;
        this.agencyId = agencyId;
        this.zoneId = zoneId;
        this.agencyZoneId = agencyZoneId;
        this.price = price;
    }

    public int getKg() {
        return kg;
    }

    public void setKg(int kg) {
        this.kg = kg;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public int getAgencyZoneId() {
        return agencyZoneId;
    }

    public void setAgencyZoneId(int agencyZoneId) {
        this.agencyZoneId = agencyZoneId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Tarifa{" + "kg=" + kg + ", idAgencia=" + agencyId + ", idZona=" + zoneId + ", idAgenciaZona=" + agencyZoneId + ", precio=" + price + '}';
    }

}
