package tdt.model;

public class Agency {

    private int agencyId;

    private String name;
    private int packages;
    private double surchargeFuel;
    private double minimumRefund;
    private boolean bigShipment;
    private double comision;

    public Agency(int agencyId, String name, int packages, double surchargeFuel, double minimumRefund, boolean bigShipment, double comision) {
        this.agencyId = agencyId;
        this.name = name;
        this.packages = packages;
        this.surchargeFuel = surchargeFuel;
        this.minimumRefund = minimumRefund;
        this.bigShipment = bigShipment;
        this.comision = comision;
    }

    public Agency(String nombre, int bultos, double recargo_combustible, double minimo_reembolso, boolean envio_grande, double comision) {
        this.name = nombre;
        this.packages = bultos;
        this.surchargeFuel = recargo_combustible;
        this.minimumRefund = minimo_reembolso;
        this.bigShipment = envio_grande;
        this.comision = comision;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Agency{" + "agencyId=" + agencyId + ", name=" + name + ", packages="
                + packages + ", surChargeFuel=" + surchargeFuel + ", minimumRefund=" + minimumRefund
                + ", bigShipment=" + bigShipment + ", comision=" + comision + '}';
    }

}
