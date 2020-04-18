
package tdt.model;

public class Provider {
    
    private int id;
   
    private String name;
    
    private String cif;
    
    public Provider(String name, String cif) {
        this.name = name;
        this.cif = cif;
    }
    
    public Provider(int id, String name, String cif) {
        this.id = id;
        this.name = name;
        this.cif = cif;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id= id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    @Override
    public String toString() {
        return "Providers{" + "id=" + id + ", name=" + name + ", cif=" + cif + '}';
    }
    
    
}
