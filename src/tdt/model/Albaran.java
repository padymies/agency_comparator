package tdt.model;

public class Albaran {

    private String CLIENTE; // 0-4
    private String DEPARTAMENTO; // 5-9
    private String REF; // 10-39
    private String FECHA; // 40-47
    private String TIPO_SERV; // 48-51 
    private String VARIANTE; // 52
    private String NOMBRE_REM; // 53-82
    private String DIRECC_REM; // 83-112
    private String POBLACION_REM; // 113-142
    private String NOMBRE_DESTINO; // 143-182
    private String DIRECC_DESTINO; //183-282
    private String VIA_DESTINO; //283-285
    private String NUMERO_DESTINO; // 286-295
    private String PISO_DESTINO; // 296-297
    private String TFNO_DESTINO; // 298-309
    private String POBLACION_DESTINO; // 310-349
    private String POSTAL_DESTINO; // 350-354
    private String BULTOS; // 355-357
    private String DOCUMENTOS; // 358-360
    private String PAQUETES; // 361-363
    private String ANCHO; // 364-366
    private String ALTO; //367-369
    private String LARGO; // 370-372
    private String PESO; // 373-384
    private String REEMBOLSO; // 385-396
    private String VALOR; //397-408
    private String CUENTA_CLIENTE; // 409-420
    private String MONEDA; // 421-421
    private String OBSERVACIONES; // 422-491
    private String SABADO; // 492
    private String HORA_ENTRADA; // 493-497
    private String RETORNO; // 498
    private String GESTION_DESTINO; // 499
    private String PORTES_DEBIDOS; // 500
    private String FORMA_PAGO; // 501-503
    private String EMAIL; // 504-553
    private String PAIS; // 554-593
    private String GLS; // 594-635
    
    
    private Zone zona;
    
    private String MEJOR_AGENCIA;
    
    private String newRef;

    public String getNewRef() {
        return newRef;
    }

    public void setNewRef(String newRef) {
        this.newRef = newRef;
    }
    
    
    
    public Albaran(String cliente, String departamento, String ref, String fecha, String typeServ, String variante, String nombreRem, String direcRem, String poblacionRem, String nombreDestino, String direcDestino, String viaDestino, String numeroDestino, String pisoDestino, String tfnoDestino, String poblaDestino, String postalDestino, String bultos, String documentos, String paquetes, String ancho, String alto, String largo, String peso, String reembolso, String valor, String ctaCliente, String moneda, String observaciones, String sabado, String horaEntr, String retorno, String gestionDest, String portesDebidos, String formaPago, String email, String pais, String gls) {
        this.CLIENTE = cliente;
        this.DEPARTAMENTO = departamento;
        this.REF = ref;
        this.FECHA = fecha;
        this.TIPO_SERV = typeServ;
        this.VARIANTE = variante;
        this.NOMBRE_REM = nombreRem;
        this.DIRECC_REM = direcRem;
        this.POBLACION_REM = poblacionRem;
        this.NOMBRE_DESTINO = nombreDestino;
        this.DIRECC_DESTINO = direcDestino;
        this.VIA_DESTINO = viaDestino;
        this.NUMERO_DESTINO = numeroDestino;
        this.PISO_DESTINO = pisoDestino;
        this.TFNO_DESTINO = tfnoDestino;
        this.POBLACION_DESTINO = poblaDestino;
        this.POSTAL_DESTINO = postalDestino;
        this.BULTOS = bultos;
        this.DOCUMENTOS = documentos;
        this.PAQUETES = paquetes;
        this.ANCHO = ancho;
        this.ALTO = alto;
        this.LARGO = largo;
        this.PESO = peso;
        this.REEMBOLSO = reembolso;
        this.VALOR = valor;
        this.CUENTA_CLIENTE = ctaCliente;
        this.MONEDA = moneda;
        this.OBSERVACIONES = observaciones;
        this.SABADO = sabado;
        this.HORA_ENTRADA = horaEntr;
        this.RETORNO = retorno;
        this.GESTION_DESTINO = gestionDest;
        this.PORTES_DEBIDOS = portesDebidos;
        this.FORMA_PAGO = formaPago;
        this.EMAIL = email;
        this.PAIS = pais;
        this.GLS = gls;
    }

    public String getMEJOR_AGENCIA() {
        return MEJOR_AGENCIA;
    }

    public void setMEJOR_AGENCIA(String MEJOR_AGENCIA) {
        this.MEJOR_AGENCIA = MEJOR_AGENCIA;
    }

    
    public String getCliente() {
        return CLIENTE;
    }

    public void setCliente(String cliente) {
        this.CLIENTE = cliente;
    }

    public String getDepartamento() {
        return DEPARTAMENTO;
    }

    public void setDepartamento(String departamento) {
        this.DEPARTAMENTO = departamento;
    }

    public String getRef() {
        return REF;
    }

    public void setRef(String ref) {
        this.REF = ref;
    }

    public String getFecha() {
        return FECHA;
    }

    public void setFecha(String fecha) {
        this.FECHA = fecha;
    }

    public String getTypeServ() {
        return TIPO_SERV;
    }

    public void setTypeServ(String typeServ) {
        this.TIPO_SERV = typeServ;
    }

    public String getVariante() {
        return VARIANTE;
    }

    public void setVariante(String variante) {
        this.VARIANTE = variante;
    }

    public String getNombreRem() {
        return NOMBRE_REM;
    }

    public void setNombreRem(String nombreRem) {
        this.NOMBRE_REM = nombreRem;
    }

    public String getDirecRem() {
        return DIRECC_REM;
    }

    public void setDirecRem(String direcRem) {
        this.DIRECC_REM = direcRem;
    }

    public String getPoblacionRem() {
        return POBLACION_REM;
    }

    public void setPoblacionRem(String poblacionRem) {
        this.POBLACION_REM = poblacionRem;
    }

    public String getNombreDestino() {
        return NOMBRE_DESTINO;
    }

    public void setNombreDestino(String nombreDestino) {
        this.NOMBRE_DESTINO = nombreDestino;
    }

    public String getDirecDestino() {
        return DIRECC_DESTINO;
    }

    public void setDirecDestino(String direcDestino) {
        this.DIRECC_DESTINO = direcDestino;
    }

    public String getViaDestino() {
        return VIA_DESTINO;
    }

    public void setViaDestino(String viaDestino) {
        this.VIA_DESTINO = viaDestino;
    }

    public String getNumeroDestino() {
        return NUMERO_DESTINO;
    }

    public void setNumeroDestino(String numeroDestino) {
        this.NUMERO_DESTINO = numeroDestino;
    }

    public String getPisoDestino() {
        return PISO_DESTINO;
    }

    public void setPisoDestino(String pisoDestino) {
        this.PISO_DESTINO = pisoDestino;
    }

    public String getTfnoDestino() {
        return TFNO_DESTINO;
    }

    public void setTfnoDestino(String tfnoDestino) {
        this.TFNO_DESTINO = tfnoDestino;
    }

    public String getPoblaDestino() {
        return POBLACION_DESTINO;
    }

    public void setPoblaDestino(String poblaDestino) {
        this.POBLACION_DESTINO = poblaDestino;
    }

    public String getPostalDestino() {
        return POSTAL_DESTINO;
    }

    public void setPostalDestino(String postalDestino) {
        this.POSTAL_DESTINO = postalDestino;
    }

    public String getBultos() {
        return BULTOS;
    }

    public void setBultos(String bultos) {
        this.BULTOS = bultos;
    }

    public String getDocumentos() {
        return DOCUMENTOS;
    }

    public void setDocumentos(String documentos) {
        this.DOCUMENTOS = documentos;
    }

    public String getPaquetes() {
        return PAQUETES;
    }

    public void setPaquetes(String paquetes) {
        this.PAQUETES = paquetes;
    }

    public String getAncho() {
        return ANCHO;
    }

    public void setAncho(String ancho) {
        this.ANCHO = ancho;
    }

    public String getAlto() {
        return ALTO;
    }

    public void setAlto(String alto) {
        this.ALTO = alto;
    }

    public String getLargo() {
        return LARGO;
    }

    public void setLargo(String largo) {
        this.LARGO = largo;
    }

    public String getPeso() {
        return PESO;
    }

    public void setPeso(String peso) {
        this.PESO = peso;
    }

    public String getReembolso() {
        return REEMBOLSO;
    }

    public void setReembolso(String reembolso) {
        this.REEMBOLSO = reembolso;
    }

    public String getValor() {
        return VALOR;
    }

    public void setValor(String valor) {
        this.VALOR = valor;
    }

    public String getCtaCliente() {
        return CUENTA_CLIENTE;
    }

    public void setCtaCliente(String ctaCliente) {
        this.CUENTA_CLIENTE = ctaCliente;
    }

    public String getMoneda() {
        return MONEDA;
    }

    public void setMoneda(String moneda) {
        this.MONEDA = moneda;
    }

    public String getObservaciones() {
        return OBSERVACIONES;
    }

    public void setObservaciones(String observaciones) {
        this.OBSERVACIONES = observaciones;
    }

    public String getSabado() {
        return SABADO;
    }

    public void setSabado(String sabado) {
        this.SABADO = sabado;
    }

    public String getHoraEntr() {
        return HORA_ENTRADA;
    }

    public void setHoraEntr(String horaEntr) {
        this.HORA_ENTRADA = horaEntr;
    }

    public String getRetorno() {
        return RETORNO;
    }

    public void setRetorno(String retorno) {
        this.RETORNO = retorno;
    }

    public String getGestionDest() {
        return GESTION_DESTINO;
    }

    public void setGestionDest(String gestionDest) {
        this.GESTION_DESTINO = gestionDest;
    }

    public String getPortesDebidos() {
        return PORTES_DEBIDOS;
    }

    public void setPortesDebidos(String portesDebidos) {
        this.PORTES_DEBIDOS = portesDebidos;
    }

    public String getFormaPago() {
        return FORMA_PAGO;
    }

    public void setFormaPago(String formaPago) {
        this.FORMA_PAGO = formaPago;
    }

    public String getEmail() {
        return EMAIL;
    }

    public void setEmail(String email) {
        this.EMAIL = email;
    }

    public String getPais() {
        return PAIS;
    }

    public void setPais(String pais) {
        this.PAIS = pais;
    }

    public String getGls() {
        return GLS;
    }

    public void setGls(String gls) {
        this.GLS = gls;
    }

    public Zone getZona() {
        return zona;
    }

    public void setZona(Zone zona) {
        this.zona = zona;
    }

    
    
    @Override
    public String toString() {
        return "Register{" + "cliente=" + CLIENTE.trim() + ", departamento=" + DEPARTAMENTO.trim() +
                ", ref=" + REF.trim() + ", fecha=" + FECHA.trim() + ", typeServ=" + TIPO_SERV.trim() +
                ", variante=" + VARIANTE.trim() + ", nombreRem=" + NOMBRE_REM.trim() + ", direcRem=" +
                DIRECC_REM.trim() + ", poblacionRem=" + POBLACION_REM.trim() + ", nombreDestino=" +
                NOMBRE_DESTINO.trim() + ", pisoDestino=" + PISO_DESTINO.trim() + ", tfnoDestino=" +
                TFNO_DESTINO.trim() + ", poblaDestino=" + POBLACION_DESTINO.trim() + ", postalDestino=" +
                POSTAL_DESTINO.trim() + ", bultos=" + BULTOS.trim() + ", documentos=" + DOCUMENTOS.trim() +
                ", paquetes=" + PAQUETES.trim() + ", ancho=" + ANCHO.trim() + ", alto=" + ALTO.trim() +
                ", largo=" + LARGO.trim() + ", peso=" + PESO.trim() + ", reembolso=" + REEMBOLSO.trim() +
                ", valor=" + VALOR.trim() + ", ctaCliente=" + CUENTA_CLIENTE.trim() + ", moneda=" +
                MONEDA.trim() + ", observaciones=" + OBSERVACIONES.trim() + ", sabado=" + SABADO.trim() +
                ", horaEntr=" + HORA_ENTRADA.trim() + ", retorno=" + RETORNO.trim() + ", gestionDest=" +
                GESTION_DESTINO.trim() + ", portesDebidos=" + PORTES_DEBIDOS.trim() + ", formaPago=" +
                FORMA_PAGO.trim() + ", email=" + EMAIL.trim() + ", pais=" + PAIS.trim() +
                ", gls=" + GLS.trim() + '}';
    }

    
}
