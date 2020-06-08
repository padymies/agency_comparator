/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.model;

/**
 *
 * @author Usuario
 */
public class Provincia {

    private int id;
    private String nombre;
    private String codigo;
    private int idZona;
    private String nombreZona;

    public Provincia(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public Provincia(int id, String nombre, String codigo, int idZona, String nombreZona) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.idZona = idZona;
        this.nombreZona = nombreZona;
    }

    public Provincia(int id, String nombre, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    @Override
    public String toString() {
        return "Provincia{" + "id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", idZona=" + idZona + ", nombreZona=" + nombreZona + '}';
    }

}
