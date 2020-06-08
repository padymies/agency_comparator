
package tdt.model;


public class Zona {
   
    private int idZona;
    private String nombre;
    private String pais;
    private String descripcion;

    public Zona(String nombre, String pais, String descripcion) {
        this.nombre = nombre;
        this.pais = pais;
        this.descripcion = descripcion;
    }

        
    public Zona(int idZona, String nombre, String pais, String descripcion) {
        this.idZona = idZona;
        this.nombre = nombre;
        this.pais = pais;
        this.descripcion = descripcion;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
