package tdt.model;

public class Agency {

    private int agencyId;

    private String name;
    private int bundles;
    private double surchargeFuel;
    private double minimumRefund;
    private boolean bigShipment;
    private double comision;
    private String folderName;

    public Agency(int agencyId, String name, int bundles, double surchargeFuel, double minimumRefund, boolean bigShipment, double comision, String folder) {
        this.agencyId = agencyId;
        this.name = name;
        this.bundles = bundles;
        this.surchargeFuel = surchargeFuel;
        this.minimumRefund = minimumRefund;
        this.bigShipment = bigShipment;
        this.comision = comision;
        this.folderName = folder;

    }

    public Agency(String nombre, int bultos, double recargo_combustible, double minimo_reembolso, boolean envio_grande, double comision, String folder) {
        this.name = nombre;
        this.bundles = bultos;
        this.surchargeFuel = recargo_combustible;
        this.minimumRefund = minimo_reembolso;
        this.bigShipment = envio_grande;
        this.comision = comision;
        this.folderName = folder;
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

    public int getBundles() {
        return bundles;
    }

    public void setBundles(int bundles) {
        this.bundles = bundles;
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

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    @Override
    public String toString() {
        return "Agency{" + "agencyId=" + agencyId + ", name=" + name + ", bundles=" + bundles + ", surchargeFuel=" + surchargeFuel + ", minimumRefund=" + minimumRefund + ", bigShipment=" + bigShipment + ", comision=" + comision + ", folderName=" + folderName + '}';
    }
    
    


}
