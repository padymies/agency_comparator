package tdt.model;

public class ComparadorTarifa {

    private int kg;
    
    private String nombreAgencia;
    
    private int idAgencia;

    private int idZona;

    private double precio;

    private int plazoEntrega;

    private int bultos;

    private double recargoCombustible;

    private double minimoReembolso;

    private boolean envioGrande;

    private double incremento;

    private int maxKilos;
    

    public ComparadorTarifa(int idAgencia, int idZona, double precio, int plazoEntrega, int bultos, double recargoCombustible, double minimoReembolso, boolean envioGrande, double incremento, int maxKilos) {
        this.idAgencia = idAgencia;
        this.idZona = idZona;
        this.precio = precio;
        this.plazoEntrega = plazoEntrega;
        this.bultos = bultos;
        this.recargoCombustible = recargoCombustible;
        this.minimoReembolso = minimoReembolso;
        this.envioGrande = envioGrande;
        this.incremento = incremento;
        this.maxKilos = maxKilos;
    }

    public ComparadorTarifa(int kg, String nombreAgencia, int idAgencia, int idZona, double precio, int plazoEntrega, int bultos, double recargoCombustible, double minimoReembolso, boolean envioGrande, double incremento, int maxKilos) {
        this.kg = kg;
        this.nombreAgencia = nombreAgencia;
        this.idAgencia = idAgencia;
        this.idZona = idZona;
        this.precio = precio;
        this.plazoEntrega = plazoEntrega;
        this.bultos = bultos;
        this.recargoCombustible = recargoCombustible;
        this.minimoReembolso = minimoReembolso;
        this.envioGrande = envioGrande;
        this.incremento = incremento;
        this.maxKilos = maxKilos;
    }

    public int getKg() {
        return kg;
    }

    public void setKg(int kg) {
        this.kg = kg;
    }

        
    public String getNombreAgencia() {
        return nombreAgencia;
    }

    public void setNombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getPlazoEntrega() {
        return plazoEntrega;
    }

    public void setPlazoEntrega(int plazoEntrega) {
        this.plazoEntrega = plazoEntrega;
    }

    public int getBultos() {
        return bultos;
    }

    public void setBultos(int bultos) {
        this.bultos = bultos;
    }

    public double getRecargoCombustible() {
        return recargoCombustible;
    }

    public void setRecargoCombustible(double recargoCombustible) {
        this.recargoCombustible = recargoCombustible;
    }

    public double getMinimoReembolso() {
        return minimoReembolso;
    }

    public void setMinimoReembolso(double minimoReembolso) {
        this.minimoReembolso = minimoReembolso;
    }

    public boolean isEnvioGrande() {
        return envioGrande;
    }

    public void setEnvioGrande(boolean envioGrande) {
        this.envioGrande = envioGrande;
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

    @Override
    public String toString() {
        return "ComparadorTarifa{" + "nombreAgencia=" + nombreAgencia + ", idAgencia=" + idAgencia + ", idZona=" + idZona + ", precio=" + precio + ", plazoEntrega=" + plazoEntrega + ", bultos=" + bultos + ", recargoCombustible=" + recargoCombustible + ", minimoReembolso=" + minimoReembolso + ", envioGrande=" + envioGrande + ", incremento=" + incremento + ", maxKilos=" + maxKilos + '}';
    }

}
