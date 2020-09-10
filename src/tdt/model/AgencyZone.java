package tdt.model;

public class AgencyZone {

    private int agencyZoneId;
    private int agencyId;
    private int zoneId;
    private double increase;
    private int maxKilos;
    
    private int deliveryTime;
    
    private int packages;
    
    private boolean bigShipment;
    
    private double comision;
    
    private String agencyName;

    public AgencyZone(int agencyId, int zoneId, double increase, int deliveryTime, int maxKilos, String agencyName) {
        this.agencyId = agencyId;
        this.zoneId = zoneId;
        this.increase = increase;
        this.deliveryTime = deliveryTime;
        this.maxKilos = maxKilos;
        this.agencyName = agencyName;
    }
    public AgencyZone(int agencyId, int zoneId, String name) {
        this.agencyId = agencyId;
        this.zoneId = zoneId;
        this.agencyName = name;
    }

    public AgencyZone(int agencyZoneId, int agencyId, int zoneId, double increase, int maxKilos) {
        this.agencyZoneId = agencyZoneId;
        this.agencyId = agencyId;
        this.zoneId = zoneId;
        this.increase = increase;
        this.maxKilos = maxKilos;
    }
    public AgencyZone(int agencyZoneId, int agencyId, int zoneId, double increase, int maxKilos, String agencyName) {
        this.agencyZoneId = agencyZoneId;
        this.agencyId = agencyId;
        this.zoneId = zoneId;
        this.increase = increase;
        this.maxKilos = maxKilos;
        this.agencyName = agencyName;
    }

    public AgencyZone(int agencyId, int zoneId, double increase, int deliveryTimne, int maxKilos, String agencyName, int packages, boolean bigShipment, double comision ) {
        this.agencyId = agencyId;
        this.zoneId = zoneId;
        this.increase = increase;
        this.deliveryTime = deliveryTimne;
        this.maxKilos = maxKilos;
        this.agencyName = agencyName;
        this.packages = packages;
        this.bigShipment = bigShipment;
        this.comision = comision;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public int getPackages() {
        return packages;
    }

    public void setPackages(int bultos) {
        this.packages = bultos;
    }

    public boolean isBigShipment() {
        return bigShipment;
    }

    public void setBigShipment(boolean bigShipment) {
        this.bigShipment = bigShipment;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    
    public int getAgencyZoneId() {
        return agencyZoneId;
    }

    public void setAgencyZoneId(int agencyZoneId) {
        this.agencyZoneId = agencyZoneId;
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

    public double getIncrease() {
        return increase;
    }

    public void setIncrease(double increase) {
        this.increase = increase;
    }

    public int getMaxKilos() {
        return maxKilos;
    }

    public void setMaxKilos(int maxKilos) {
        this.maxKilos = maxKilos;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    
    

}
