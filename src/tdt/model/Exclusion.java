
package tdt.model;


public class Exclusion {

    private int id;
    private String postalCode;
    private int agencyId; // null: no hay agencia
    private int inclusion_exclusion; // 1: inclusion; -1: exclusion; 0: no se puede enviar por ninguna

    private String agencyName;

    public Exclusion(String postalCode, String agencyName, int inclusion_exclusion) {
        this.postalCode = postalCode;
        this.agencyName = agencyName;
        this.inclusion_exclusion = inclusion_exclusion;
    }

    public Exclusion(int id, String postalCode, int agencyId, int inclusion_exclusion) {
        this.id = id;
        this.postalCode = postalCode;
        this.agencyId = agencyId;
        this.inclusion_exclusion = inclusion_exclusion;
    }

    public Exclusion(int id, String postalCode, String agencyName, int inclusion_exclusion) {
        this.id = id;
        this.postalCode = postalCode;
        this.inclusion_exclusion = inclusion_exclusion;
        this.agencyName = agencyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public int getInclusion_exclusion() {
        return inclusion_exclusion;
    }

    public void setInclusion_exclusion(int inclusion_exclusion) {
        this.inclusion_exclusion = inclusion_exclusion;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    @Override
    public String toString() {
        return "Exclusion{" + "id=" + id + ", postalCode=" + postalCode + ", agencyId=" + agencyId + ", inclusion_exclusion=" + inclusion_exclusion + ", agencyName=" + agencyName + '}';
    }

}
