package tdt.model;

public class RateComparator {

    private int kg;
    
    private String agencyName;
    
    private int agencyId;

    private int zoneId;

    private double price;

    private int deliveryTime;

    private int packages;

    private double surchargeFuel;

    private double minimumRefund;

    private boolean bigShipment;
    
    private double comision;

    private double increase;

    private int maxKilos;
    

    public RateComparator(int agencyId, int zoneId, double price, int deliveryTime, int packages, double surchargeFuel, 
            double minimumRefund, boolean bigShipment, double comision, double increase, int maxKilos) {
        this.agencyId = agencyId;
        this.zoneId = zoneId;
        this.price = price;
        this.deliveryTime = deliveryTime;
        this.packages = packages;
        this.surchargeFuel = surchargeFuel;
        this.minimumRefund = minimumRefund;
        this.bigShipment = bigShipment;
        this.increase = increase;
        this.maxKilos = maxKilos;
        this.comision = comision;
    }

    public RateComparator(int kg, String agencyName, int agencyId, int zoneId, double price, int deliveryTime,
            int packages, double surchargeFuel, double minimumRefund, boolean bigShipment, double comision, double increase, int maxKilos) {
        this.kg = kg;
        this.agencyName = agencyName;
        this.agencyId = agencyId;
        this.zoneId = zoneId;
        this.price = price;
        this.deliveryTime = deliveryTime;
        this.packages = packages;
        this.surchargeFuel = surchargeFuel;
        this.minimumRefund = minimumRefund;
        this.bigShipment = bigShipment;
        this.increase = increase;
        this.maxKilos = maxKilos;
        this.comision = comision;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public int getKg() {
        return kg;
    }

    public void setKg(int kg) {
        this.kg = kg;
    }

        
    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getPackages() {
        return packages;
    }

    public void setPackages(int packages) {
        this.packages = packages;
    }

    public double getSurchargeFuel() {
        return surchargeFuel;
    }

    public void setSurchargeFuel(double surchargeFuel) {
        this.surchargeFuel = surchargeFuel;
    }

    public double getMinimumRefund() {
        return minimumRefund;
    }

    public void setMinimumRefund(double minimumRefund) {
        this.minimumRefund = minimumRefund;
    }

    public boolean isBigShipment() {
        return bigShipment;
    }

    public void setBigShipment(boolean bigShipment) {
        this.bigShipment = bigShipment;
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

    @Override
    public String toString() {
        return "ComparadorTarifa{" + "nombreAgencia=" + agencyName + ", idAgencia=" + agencyId + ", idZona=" + zoneId + ", precio=" + price + ", plazoEntrega=" + deliveryTime + ", bultos=" + packages + ", recargoCombustible=" + surchargeFuel + ", minimoReembolso=" + minimumRefund + ", envioGrande=" + bigShipment + ", incremento=" + increase + ", maxKilos=" + maxKilos + '}';
    }

}
