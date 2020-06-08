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
public class Exclusion {

    private int id;
    private String cp;
    private int id_agencia; // null: no hay agencia
    private int inclusion_exclusion; // 1: inclusion; -1: exclusion; 0: no se puede enviar por ninguna

    private String nombreAgencia;

    public Exclusion(String cp, String nombreAgencia, int inclusion_exclusion) {
        this.cp = cp;
        this.nombreAgencia = nombreAgencia;
        this.inclusion_exclusion = inclusion_exclusion;
    }

    public Exclusion(int id, String cp, int id_agencia, int inclusion_exclusion) {
        this.id = id;
        this.cp = cp;
        this.id_agencia = id_agencia;
        this.inclusion_exclusion = inclusion_exclusion;
    }

    public Exclusion(int id, String cp, String nombreAgencia, int inclusion_exclusion) {
        this.id = id;
        this.cp = cp;
        this.inclusion_exclusion = inclusion_exclusion;
        this.nombreAgencia = nombreAgencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public int getId_agencia() {
        return id_agencia;
    }

    public void setId_agencia(int id_agencia) {
        this.id_agencia = id_agencia;
    }

    public int getInclusion_exclusion() {
        return inclusion_exclusion;
    }

    public void setInclusion_exclusion(int inclusion_exclusion) {
        this.inclusion_exclusion = inclusion_exclusion;
    }

    public String getNombreAgencia() {
        return nombreAgencia;
    }

    public void setNombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
    }

}
