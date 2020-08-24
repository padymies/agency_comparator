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
public class Tarifa {

    private int kg;
    private int idAgencia;
    private int idZona;
    private int idAgenciaZona;
    private double precio;

    public Tarifa(int kg, double precio) {
        this.kg = kg;
        this.precio = precio;
    }

    public Tarifa(int kg, int idAgencia, int idZona, double precio) {
        this.kg = kg;
        this.idAgencia = idAgencia;
        this.idZona = idZona;
        this.precio = precio;
    }
    public Tarifa(int kg, int idAgencia, int idZona, int idAgenciaZona, double precio) {
        this.kg = kg;
        this.idAgencia = idAgencia;
        this.idZona = idZona;
        this.idAgenciaZona = idAgenciaZona;
        this.precio = precio;
    }

    public int getKg() {
        return kg;
    }

    public void setKg(int kg) {
        this.kg = kg;
    }

    public int getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(int idAgencia) {
        this.idAgencia = idAgencia;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public int getIdAgenciaZona() {
        return idAgenciaZona;
    }

    public void setIdAgenciaZona(int idAgenciaZona) {
        this.idAgenciaZona = idAgenciaZona;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Tarifa{" + "kg=" + kg + ", idAgencia=" + idAgencia + ", idZona=" + idZona + ", idAgenciaZona=" + idAgenciaZona + ", precio=" + precio + '}';
    }

}
