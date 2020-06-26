package tdt.model;

public class Agencia {

    private int id_agencia;

    private String nombre;
    private int bultos;
    private double recargo_combustible;
    private double minimo_reembolso;
    private boolean envio_grande;
    private double comision;

    public Agencia(int id_agencia, String nombre, int bultos, double recargo_combustible, double minimo_reembolso, boolean envio_grande, double comision) {
        this.id_agencia = id_agencia;
        this.nombre = nombre;
        this.bultos = bultos;
        this.recargo_combustible = recargo_combustible;
        this.minimo_reembolso = minimo_reembolso;
        this.envio_grande = envio_grande;
        this.comision = comision;
    }

    
    
    
    public Agencia(String nombre, int bultos, double recargo_combustible, double minimo_reembolso, boolean envio_grande, double comision) {
        this.nombre = nombre;
        this.bultos = bultos;
        this.recargo_combustible = recargo_combustible;
        this.minimo_reembolso = minimo_reembolso;
        this.envio_grande = envio_grande;
         this.comision = comision;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public int getId_agencia() {
        return id_agencia;
    }

    public void setId_agencia(int id_agencia) {
        this.id_agencia = id_agencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getBultos() {
        return bultos;
    }

    public void setBultos(int bultos) {
        this.bultos = bultos;
    }

    public double getRecargo_combustible() {
        return recargo_combustible;
    }

    public void setRecargo_combustible(double recargo_combustible) {
        this.recargo_combustible = recargo_combustible;
    }

    public double getMinimo_reembolso() {
        return minimo_reembolso;
    }

    public void setMinimo_reembolso(double minimo_reembolso) {
        this.minimo_reembolso = minimo_reembolso;
    }

    public boolean isEnvio_grande() {
        return envio_grande;
    }

    public void setEnvio_grande(boolean envio_grande) {
        this.envio_grande = envio_grande;
    }

    @Override
    public String toString() {
        return "Agencia{" + "id_agencia=" + id_agencia + ", nombre=" + nombre + ", bultos=" + 
                bultos + ", recargo_combustible=" + recargo_combustible + ", minimo_reembolso=" + minimo_reembolso +
                ", envio_grande=" + envio_grande + ", comision=" + comision +  '}';
    }
    
    

}
