package tdt.model;

public class AgenciaZona {

    private int idAgenciaZona;
    private int idAgencia;
    private int idZona;
    private double incremento;
    private int maxKilos;
    
    private int plazoEntrega;
    
    private int bultos;
    
    private boolean envioGrande;
    
    private double comision;
    
    private String nombreAgencia;

    public AgenciaZona(int idAgencia, int idZona, double incremento, int plazoEntrega, int maxKilos, String nombreAgencia) {
        this.idAgencia = idAgencia;
        this.idZona = idZona;
        this.incremento = incremento;
        this.plazoEntrega = plazoEntrega;
        this.maxKilos = maxKilos;
        this.nombreAgencia = nombreAgencia;
    }
    public AgenciaZona(int idAgencia, int idZona, String nombre) {
        this.idAgencia = idAgencia;
        this.idZona = idZona;
        this.nombreAgencia = nombre;
    }

    public AgenciaZona(int idAgenciaZona, int idAgencia, int idZona, double incremento, int maxKilos) {
        this.idAgenciaZona = idAgenciaZona;
        this.idAgencia = idAgencia;
        this.idZona = idZona;
        this.incremento = incremento;
        this.maxKilos = maxKilos;
    }
    public AgenciaZona(int idAgenciaZona, int idAgencia, int idZona, double incremento, int maxKilos, String nombreAgencia) {
        this.idAgenciaZona = idAgenciaZona;
        this.idAgencia = idAgencia;
        this.idZona = idZona;
        this.incremento = incremento;
        this.maxKilos = maxKilos;
        this.nombreAgencia = nombreAgencia;
    }

    public AgenciaZona(int idAgencia, int idZona, double incremento, int plazoEntrega, int maxKilos, String nombreAgencia, int bultos, boolean envioGrande, double comision ) {
        this.idAgencia = idAgencia;
        this.idZona = idZona;
        this.incremento = incremento;
        this.plazoEntrega = plazoEntrega;
        this.maxKilos = maxKilos;
        this.nombreAgencia = nombreAgencia;
        this.bultos = bultos;
        this.envioGrande = envioGrande;
        this.comision = comision;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public int getBultos() {
        return bultos;
    }

    public void setBultos(int bultos) {
        this.bultos = bultos;
    }

    public boolean isEnvioGrande() {
        return envioGrande;
    }

    public void setEnvioGrande(boolean envioGrande) {
        this.envioGrande = envioGrande;
    }

    public int getPlazoEntrega() {
        return plazoEntrega;
    }

    public void setPlazoEntrega(int plazoEntrega) {
        this.plazoEntrega = plazoEntrega;
    }

    
    public int getIdAgenciaZona() {
        return idAgenciaZona;
    }

    public void setIdAgenciaZona(int idAgenciaZona) {
        this.idAgenciaZona = idAgenciaZona;
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

    public double getIncremento() {
        return incremento;
    }

    public void setIncremento(double incremento) {
        this.incremento = incremento;
    }

    public int getMaxKilos() {
        return maxKilos;
    }

    public void setMaxKilos(int maxKilos) {
        this.maxKilos = maxKilos;
    }

    public String getNombreAgencia() {
        return nombreAgencia;
    }

    public void setNombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
    }
    
    

}
