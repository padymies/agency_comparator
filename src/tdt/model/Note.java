package tdt.model;

public class Note {

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
    
    
    private Zone zone;
    
    private String BEST_AGENCY;
    
    private String newRef;

    public String getNewRef() {
        return newRef;
    }

    public void setNewRef(String newRef) {
        this.newRef = newRef;
    }
    
    
    
    public Note(String client, String departament, String ref, String date, String typeServ, String variant, String senderName, String senderAddress, 
            String senderCity, String destinationName, String destinationAddress, String destinationRoad, String destinationNumber, 
            String destinationApartment, String destinationPhone, String destinationCity, String destinationPostalCode, String bundles, String documents,
            String packages, String width, String height, String _long, String weight, String refund, String value, String clientBill, String coin,
            String observations, String saturday, String inTime, String _return, String destinationManagement, String duePostage, String payWay, String email, String country, String gls) {
        this.CLIENTE = client;
        this.DEPARTAMENTO = departament;
        this.REF = ref;
        this.FECHA = date;
        this.TIPO_SERV = typeServ;
        this.VARIANTE = variant;
        this.NOMBRE_REM = senderName;
        this.DIRECC_REM = senderAddress;
        this.POBLACION_REM = senderCity;
        this.NOMBRE_DESTINO = destinationName;
        this.DIRECC_DESTINO = destinationAddress;
        this.VIA_DESTINO = destinationRoad;
        this.NUMERO_DESTINO = destinationNumber;
        this.PISO_DESTINO = destinationApartment;
        this.TFNO_DESTINO = destinationPhone;
        this.POBLACION_DESTINO = destinationCity;
        this.POSTAL_DESTINO = destinationPostalCode;
        this.BULTOS = bundles;
        this.DOCUMENTOS = documents;
        this.PAQUETES = packages;
        this.ANCHO = width;
        this.ALTO = height;
        this.LARGO = _long;
        this.PESO = weight;
        this.REEMBOLSO = refund;
        this.VALOR = value;
        this.CUENTA_CLIENTE = clientBill;
        this.MONEDA = coin;
        this.OBSERVACIONES = observations;
        this.SABADO = saturday;
        this.HORA_ENTRADA = inTime;
        this.RETORNO = _return;
        this.GESTION_DESTINO = destinationManagement;
        this.PORTES_DEBIDOS = duePostage;
        this.FORMA_PAGO = payWay;
        this.EMAIL = email;
        this.PAIS = country;
        this.GLS = gls;
    }

    public String getBEST_AGENCY() {
        return BEST_AGENCY;
    }

    public void setBEST_AGENCY(String BEST_AGENCY) {
        this.BEST_AGENCY = BEST_AGENCY;
    }

    
    public String getClient() {
        return CLIENTE;
    }

    public void setClient(String client) {
        this.CLIENTE = client;
    }

    public String getDepartament() {
        return DEPARTAMENTO;
    }

    public void setDepartament(String departament) {
        this.DEPARTAMENTO = departament;
    }

    public String getRef() {
        return REF;
    }

    public void setRef(String ref) {
        this.REF = ref;
    }

    public String getDate() {
        return FECHA;
    }

    public void setDate(String date) {
        this.FECHA = date;
    }

    public String getTypeServ() {
        return TIPO_SERV;
    }

    public void setTypeServ(String typeServ) {
        this.TIPO_SERV = typeServ;
    }

    public String getVariant() {
        return VARIANTE;
    }

    public void setVariant(String variant) {
        this.VARIANTE = variant;
    }

    public String getSenderName() {
        return NOMBRE_REM;
    }

    public void setSenderName(String senderName) {
        this.NOMBRE_REM = senderName;
    }

    public String getSenderAddress() {
        return DIRECC_REM;
    }

    public void setSenderAddress(String senderAddress) {
        this.DIRECC_REM = senderAddress;
    }

    public String getSenderCity() {
        return POBLACION_REM;
    }

    public void setSenderCity(String senderCity) {
        this.POBLACION_REM = senderCity;
    }

    public String getDestinationName() {
        return NOMBRE_DESTINO;
    }

    public void setDestinationName(String destinationName) {
        this.NOMBRE_DESTINO = destinationName;
    }

    public String getDestinationAddress() {
        return DIRECC_DESTINO;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.DIRECC_DESTINO = destinationAddress;
    }

    public String getDestinationRoad() {
        return VIA_DESTINO;
    }

    public void setDestinationRoad(String destinationRoad) {
        this.VIA_DESTINO = destinationRoad;
    }

    public String getDestinationNumber() {
        return NUMERO_DESTINO;
    }

    public void setDestinationNumber(String destinationNumber) {
        this.NUMERO_DESTINO = destinationNumber;
    }

    public String getDestinationApartment() {
        return PISO_DESTINO;
    }

    public void setDestinationApartment(String destinationApartment) {
        this.PISO_DESTINO = destinationApartment;
    }

    public String getDestinationPhone() {
        return TFNO_DESTINO;
    }

    public void setDestinationPhone(String destinationPhone) {
        this.TFNO_DESTINO = destinationPhone;
    }

    public String getDestinationCity() {
        return POBLACION_DESTINO;
    }

    public void setDestinationCity(String destinationCity) {
        this.POBLACION_DESTINO = destinationCity;
    }

    public String getDestinationPostalCode() {
        return POSTAL_DESTINO;
    }

    public void setDestinationPostalCode(String destinationPostalCode) {
        this.POSTAL_DESTINO = destinationPostalCode;
    }

    public String getBundles() {
        return BULTOS;
    }

    public void setBundles(String bundles) {
        this.BULTOS = bundles;
    }

    public String getDocuments() {
        return DOCUMENTOS;
    }

    public void setDocuments(String documents) {
        this.DOCUMENTOS = documents;
    }

    public String getPackages() {
        return PAQUETES;
    }

    public void setPackages(String packages) {
        this.PAQUETES = packages;
    }

    public String getWidth() {
        return ANCHO;
    }

    public void setWidth(String width) {
        this.ANCHO = width;
    }

    public String getHeight() {
        return ALTO;
    }

    public void setHeight(String height) {
        this.ALTO = height;
    }

    public String getLong() {
        return LARGO;
    }

    public void setLong(String _long) {
        this.LARGO = _long;
    }

    public String getWeight() {
        return PESO;
    }

    public void setWeight(String weight) {
        this.PESO = weight;
    }

    public String getRefund() {
        return REEMBOLSO;
    }

    public void setRefund(String refund) {
        this.REEMBOLSO = refund;
    }

    public String getValue() {
        return VALOR;
    }

    public void setValue(String value) {
        this.VALOR = value;
    }

    public String getClientBill() {
        return CUENTA_CLIENTE;
    }

    public void setClientBill(String clientBill) {
        this.CUENTA_CLIENTE = clientBill;
    }

    public String getCoin() {
        return MONEDA;
    }

    public void setCoin(String coin) {
        this.MONEDA = coin;
    }

    public String getObservations() {
        return OBSERVACIONES;
    }

    public void setObservations(String observations) {
        this.OBSERVACIONES = observations;
    }

    public String getSaturday() {
        return SABADO;
    }

    public void setSaturday(String saturday) {
        this.SABADO = saturday;
    }

    public String getInTime() {
        return HORA_ENTRADA;
    }

    public void setInTime(String inTime) {
        this.HORA_ENTRADA = inTime;
    }

    public String getReturn() {
        return RETORNO;
    }

    public void setReturn(String _return) {
        this.RETORNO = _return;
    }

    public String getDestinationManagement() {
        return GESTION_DESTINO;
    }

    public void setDestinationManagement(String destinationManagement) {
        this.GESTION_DESTINO = destinationManagement;
    }

    public String getDuePostage() {
        return PORTES_DEBIDOS;
    }

    public void setDuePostage(String duePostage) {
        this.PORTES_DEBIDOS = duePostage;
    }

    public String getPayWay() {
        return FORMA_PAGO;
    }

    public void setPayWay(String payWay) {
        this.FORMA_PAGO = payWay;
    }

    public String getEmail() {
        return EMAIL;
    }

    public void setEmail(String email) {
        this.EMAIL = email;
    }

    public String getCountry() {
        return PAIS;
    }

    public void setCountry(String country) {
        this.PAIS = country;
    }

    public String getGls() {
        return GLS;
    }

    public void setGls(String gls) {
        this.GLS = gls;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
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
