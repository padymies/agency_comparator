package tdt.model;

public class Register {

    private String cliente; // 0-4
    private String departamento; // 5-9
    private String ref; // 10-39
    private String fecha; // 40-47
    private String typeServ; // 48-51 
    private String variante; // 52
    private String nombreRem; // 53-82
    private String direcRem; // 83-112
    private String poblacionRem; // 113-142
    private String nombreDestino; // 143-182
    private String direcDestino; //183-282
    private String viaDestino; //283-285
    private String numeroDestino; // 286-295
    private String pisoDestino; // 296-297
    private String tfnoDestino; // 298-309
    private String poblaDestino; // 310-349
    private String postalDestino; // 350-354
    private String bultos; // 355-357
    private String documentos; // 358-360
    private String paquetes; // 361-363
    private String ancho; // 364-366
    private String alto; //367-369
    private String largo; // 370-372
    private String peso; // 373-384
    private String reembolso; // 385-396
    private String valor; //397-408
    private String ctaCliente; // 409-420
    private String moneda; // 421-421
    private String observaciones; // 422-491
    private String sabado; // 492
    private String horaEntr; // 493-497
    private String retorno; // 498
    private String gestionDest; // 499
    private String portesDebidos; // 500
    private String formaPago; // 501-503
    private String email; // 504-553
    private String pais; // 554-593
    private String gls; // 594-635

    public Register(String cliente, String departamento, String ref, String fecha, String typeServ, String variante, String nombreRem, String direcRem, String poblacionRem, String nombreDestino, String direcDestino, String viaDestino, String numeroDestino, String pisoDestino, String tfnoDestino, String poblaDestino, String postalDestino, String bultos, String documentos, String paquetes, String ancho, String alto, String largo, String peso, String reembolso, String valor, String ctaCliente, String moneda, String observaciones, String sabado, String horaEntr, String retorno, String gestionDest, String portesDebidos, String formaPago, String email, String pais, String gls) {
        this.cliente = cliente;
        this.departamento = departamento;
        this.ref = ref;
        this.fecha = fecha;
        this.typeServ = typeServ;
        this.variante = variante;
        this.nombreRem = nombreRem;
        this.direcRem = direcRem;
        this.poblacionRem = poblacionRem;
        this.nombreDestino = nombreDestino;
        this.direcDestino = direcDestino;
        this.viaDestino = viaDestino;
        this.numeroDestino = numeroDestino;
        this.pisoDestino = pisoDestino;
        this.tfnoDestino = tfnoDestino;
        this.poblaDestino = poblaDestino;
        this.postalDestino = postalDestino;
        this.bultos = bultos;
        this.documentos = documentos;
        this.paquetes = paquetes;
        this.ancho = ancho;
        this.alto = alto;
        this.largo = largo;
        this.peso = peso;
        this.reembolso = reembolso;
        this.valor = valor;
        this.ctaCliente = ctaCliente;
        this.moneda = moneda;
        this.observaciones = observaciones;
        this.sabado = sabado;
        this.horaEntr = horaEntr;
        this.retorno = retorno;
        this.gestionDest = gestionDest;
        this.portesDebidos = portesDebidos;
        this.formaPago = formaPago;
        this.email = email;
        this.pais = pais;
        this.gls = gls;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTypeServ() {
        return typeServ;
    }

    public void setTypeServ(String typeServ) {
        this.typeServ = typeServ;
    }

    public String getVariante() {
        return variante;
    }

    public void setVariante(String variante) {
        this.variante = variante;
    }

    public String getNombreRem() {
        return nombreRem;
    }

    public void setNombreRem(String nombreRem) {
        this.nombreRem = nombreRem;
    }

    public String getDirecRem() {
        return direcRem;
    }

    public void setDirecRem(String direcRem) {
        this.direcRem = direcRem;
    }

    public String getPoblacionRem() {
        return poblacionRem;
    }

    public void setPoblacionRem(String poblacionRem) {
        this.poblacionRem = poblacionRem;
    }

    public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    public String getDirecDestino() {
        return direcDestino;
    }

    public void setDirecDestino(String direcDestino) {
        this.direcDestino = direcDestino;
    }

    public String getViaDestino() {
        return viaDestino;
    }

    public void setViaDestino(String viaDestino) {
        this.viaDestino = viaDestino;
    }

    public String getNumeroDestino() {
        return numeroDestino;
    }

    public void setNumeroDestino(String numeroDestino) {
        this.numeroDestino = numeroDestino;
    }

    public String getPisoDestino() {
        return pisoDestino;
    }

    public void setPisoDestino(String pisoDestino) {
        this.pisoDestino = pisoDestino;
    }

    public String getTfnoDestino() {
        return tfnoDestino;
    }

    public void setTfnoDestino(String tfnoDestino) {
        this.tfnoDestino = tfnoDestino;
    }

    public String getPoblaDestino() {
        return poblaDestino;
    }

    public void setPoblaDestino(String poblaDestino) {
        this.poblaDestino = poblaDestino;
    }

    public String getPostalDestino() {
        return postalDestino;
    }

    public void setPostalDestino(String postalDestino) {
        this.postalDestino = postalDestino;
    }

    public String getBultos() {
        return bultos;
    }

    public void setBultos(String bultos) {
        this.bultos = bultos;
    }

    public String getDocumentos() {
        return documentos;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    public String getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(String paquetes) {
        this.paquetes = paquetes;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getAlto() {
        return alto;
    }

    public void setAlto(String alto) {
        this.alto = alto;
    }

    public String getLargo() {
        return largo;
    }

    public void setLargo(String largo) {
        this.largo = largo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getReembolso() {
        return reembolso;
    }

    public void setReembolso(String reembolso) {
        this.reembolso = reembolso;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCtaCliente() {
        return ctaCliente;
    }

    public void setCtaCliente(String ctaCliente) {
        this.ctaCliente = ctaCliente;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getSabado() {
        return sabado;
    }

    public void setSabado(String sabado) {
        this.sabado = sabado;
    }

    public String getHoraEntr() {
        return horaEntr;
    }

    public void setHoraEntr(String horaEntr) {
        this.horaEntr = horaEntr;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public String getGestionDest() {
        return gestionDest;
    }

    public void setGestionDest(String gestionDest) {
        this.gestionDest = gestionDest;
    }

    public String getPortesDebidos() {
        return portesDebidos;
    }

    public void setPortesDebidos(String portesDebidos) {
        this.portesDebidos = portesDebidos;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getGls() {
        return gls;
    }

    public void setGls(String gls) {
        this.gls = gls;
    }

    @Override
    public String toString() {
        return "Register{" + "cliente=" + cliente.trim() + ", departamento=" + departamento.trim() +
                ", ref=" + ref.trim() + ", fecha=" + fecha.trim() + ", typeServ=" + typeServ.trim() +
                ", variante=" + variante.trim() + ", nombreRem=" + nombreRem.trim() + ", direcRem=" +
                direcRem.trim() + ", poblacionRem=" + poblacionRem.trim() + ", nombreDestino=" +
                nombreDestino.trim() + ", pisoDestino=" + pisoDestino.trim() + ", tfnoDestino=" +
                tfnoDestino.trim() + ", poblaDestino=" + poblaDestino.trim() + ", postalDestino=" +
                postalDestino.trim() + ", bultos=" + bultos.trim() + ", documentos=" + documentos.trim() +
                ", paquetes=" + paquetes.trim() + ", ancho=" + ancho.trim() + ", alto=" + alto.trim() +
                ", largo=" + largo.trim() + ", peso=" + peso.trim() + ", reembolso=" + reembolso.trim() +
                ", valor=" + valor.trim() + ", ctaCliente=" + ctaCliente.trim() + ", moneda=" +
                moneda.trim() + ", observaciones=" + observaciones.trim() + ", sabado=" + sabado.trim() +
                ", horaEntr=" + horaEntr.trim() + ", retorno=" + retorno.trim() + ", gestionDest=" +
                gestionDest.trim() + ", portesDebidos=" + portesDebidos.trim() + ", formaPago=" +
                formaPago.trim() + ", email=" + email.trim() + ", pais=" + pais.trim() +
                ", gls=" + gls.trim() + '}';
    }

    
}
