package tdt.model;

public class Note {

    private String CLIENT; // 0-4
    private String DEPARTAMENT; // 5-9
    private String REF; // 10-39
    private String DATE; // 40-47
    private String TYPE_SERV; // 48-51 
    private String VARIANT; // 52
    private String SENDER_NAME; // 53-82
    private String SENDER_ADDRESS; // 83-112
    private String SENDER_CITY; // 113-142
    private String DESTINATION_NAME; // 143-182
    private String DESTINATION_ADDRESS; //183-282
    private String DESTINATION_ROAD; //283-285
    private String DESTINATION_NUMBER; // 286-295
    private String DESTINATION_APARTMENT; // 296-297
    private String DESTINATION_PHONE; // 298-309
    private String DESTINATION_CITY; // 310-349
    private String DESTINATION_POSTAL_CODE; // 350-354
    private String BUNDLES; // 355-357
    private String DOCUMENTS; // 358-360
    private String PACKAGES; // 361-363
    private String WIDTH; // 364-366
    private String HEIGHT; //367-369
    private String LONG; // 370-372
    private String WEIGHT; // 373-384
    private String REFUND; // 385-396
    private String VALUE; //397-408
    private String CLIENT_BILL; // 409-420
    private String COIN; // 421-421
    private String OBSERVATIONS; // 422-491
    private String SATURDAY; // 492
    private String IN_TIME; // 493-497
    private String RETURN; // 498
    private String DESTINY_MANAGEMENT; // 499
    private String DUE_POSTAGE; // 500
    private String PAY_WAY; // 501-503
    private String EMAIL; // 504-553
    private String COUNTRY; // 554-593
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
            String observations, String saturday, String inTime, String _return, String destinyManagement, String duePostage, String payWay, String email, String country, String gls) {
        this.CLIENT = client;
        this.DEPARTAMENT = departament;
        this.REF = ref;
        this.DATE = date;
        this.TYPE_SERV = typeServ;
        this.VARIANT = variant;
        this.SENDER_NAME = senderName;
        this.SENDER_ADDRESS = senderAddress;
        this.SENDER_CITY = senderCity;
        this.DESTINATION_NAME = destinationName;
        this.DESTINATION_ADDRESS = destinationAddress;
        this.DESTINATION_ROAD = destinationRoad;
        this.DESTINATION_NUMBER = destinationNumber;
        this.DESTINATION_APARTMENT = destinationApartment;
        this.DESTINATION_PHONE = destinationPhone;
        this.DESTINATION_CITY = destinationCity;
        this.DESTINATION_POSTAL_CODE = destinationPostalCode;
        this.BUNDLES = bundles;
        this.DOCUMENTS = documents;
        this.PACKAGES = packages;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.LONG = _long;
        this.WEIGHT = weight;
        this.REFUND = refund;
        this.VALUE = value;
        this.CLIENT_BILL = clientBill;
        this.COIN = coin;
        this.OBSERVATIONS = observations;
        this.SATURDAY = saturday;
        this.IN_TIME = inTime;
        this.RETURN = _return;
        this.DESTINY_MANAGEMENT = destinyManagement;
        this.DUE_POSTAGE = duePostage;
        this.PAY_WAY = payWay;
        this.EMAIL = email;
        this.COUNTRY = country;
        this.GLS = gls;
    }

    public String getMEJOR_AGENCIA() {
        return BEST_AGENCY;
    }

    public void setMEJOR_AGENCIA(String MEJOR_AGENCIA) {
        this.BEST_AGENCY = MEJOR_AGENCIA;
    }

    
    public String getCliente() {
        return CLIENT;
    }

    public void setCliente(String cliente) {
        this.CLIENT = cliente;
    }

    public String getDepartamento() {
        return DEPARTAMENT;
    }

    public void setDepartamento(String departamento) {
        this.DEPARTAMENT = departamento;
    }

    public String getRef() {
        return REF;
    }

    public void setRef(String ref) {
        this.REF = ref;
    }

    public String getFecha() {
        return DATE;
    }

    public void setFecha(String fecha) {
        this.DATE = fecha;
    }

    public String getTypeServ() {
        return TYPE_SERV;
    }

    public void setTypeServ(String typeServ) {
        this.TYPE_SERV = typeServ;
    }

    public String getVariante() {
        return VARIANT;
    }

    public void setVariante(String variante) {
        this.VARIANT = variante;
    }

    public String getNombreRem() {
        return SENDER_NAME;
    }

    public void setNombreRem(String nombreRem) {
        this.SENDER_NAME = nombreRem;
    }

    public String getDirecRem() {
        return SENDER_ADDRESS;
    }

    public void setDirecRem(String direcRem) {
        this.SENDER_ADDRESS = direcRem;
    }

    public String getPoblacionRem() {
        return SENDER_CITY;
    }

    public void setPoblacionRem(String poblacionRem) {
        this.SENDER_CITY = poblacionRem;
    }

    public String getNombreDestino() {
        return DESTINATION_NAME;
    }

    public void setNombreDestino(String nombreDestino) {
        this.DESTINATION_NAME = nombreDestino;
    }

    public String getDirecDestino() {
        return DESTINATION_ADDRESS;
    }

    public void setDirecDestino(String direcDestino) {
        this.DESTINATION_ADDRESS = direcDestino;
    }

    public String getViaDestino() {
        return DESTINATION_ROAD;
    }

    public void setViaDestino(String viaDestino) {
        this.DESTINATION_ROAD = viaDestino;
    }

    public String getNumeroDestino() {
        return DESTINATION_NUMBER;
    }

    public void setNumeroDestino(String numeroDestino) {
        this.DESTINATION_NUMBER = numeroDestino;
    }

    public String getPisoDestino() {
        return DESTINATION_APARTMENT;
    }

    public void setPisoDestino(String pisoDestino) {
        this.DESTINATION_APARTMENT = pisoDestino;
    }

    public String getTfnoDestino() {
        return DESTINATION_PHONE;
    }

    public void setTfnoDestino(String tfnoDestino) {
        this.DESTINATION_PHONE = tfnoDestino;
    }

    public String getPoblaDestino() {
        return DESTINATION_CITY;
    }

    public void setPoblaDestino(String poblaDestino) {
        this.DESTINATION_CITY = poblaDestino;
    }

    public String getPostalDestino() {
        return DESTINATION_POSTAL_CODE;
    }

    public void setPostalDestino(String postalDestino) {
        this.DESTINATION_POSTAL_CODE = postalDestino;
    }

    public String getBultos() {
        return BUNDLES;
    }

    public void setBultos(String bultos) {
        this.BUNDLES = bultos;
    }

    public String getDocumentos() {
        return DOCUMENTS;
    }

    public void setDocumentos(String documentos) {
        this.DOCUMENTS = documentos;
    }

    public String getPaquetes() {
        return PACKAGES;
    }

    public void setPaquetes(String paquetes) {
        this.PACKAGES = paquetes;
    }

    public String getAncho() {
        return WIDTH;
    }

    public void setAncho(String ancho) {
        this.WIDTH = ancho;
    }

    public String getAlto() {
        return HEIGHT;
    }

    public void setAlto(String alto) {
        this.HEIGHT = alto;
    }

    public String getLargo() {
        return LONG;
    }

    public void setLargo(String largo) {
        this.LONG = largo;
    }

    public String getPeso() {
        return WEIGHT;
    }

    public void setPeso(String peso) {
        this.WEIGHT = peso;
    }

    public String getReembolso() {
        return REFUND;
    }

    public void setReembolso(String reembolso) {
        this.REFUND = reembolso;
    }

    public String getValor() {
        return VALUE;
    }

    public void setValor(String valor) {
        this.VALUE = valor;
    }

    public String getCtaCliente() {
        return CLIENT_BILL;
    }

    public void setCtaCliente(String ctaCliente) {
        this.CLIENT_BILL = ctaCliente;
    }

    public String getMoneda() {
        return COIN;
    }

    public void setMoneda(String moneda) {
        this.COIN = moneda;
    }

    public String getObservaciones() {
        return OBSERVATIONS;
    }

    public void setObservaciones(String observaciones) {
        this.OBSERVATIONS = observaciones;
    }

    public String getSabado() {
        return SATURDAY;
    }

    public void setSabado(String sabado) {
        this.SATURDAY = sabado;
    }

    public String getHoraEntr() {
        return IN_TIME;
    }

    public void setHoraEntr(String horaEntr) {
        this.IN_TIME = horaEntr;
    }

    public String getRetorno() {
        return RETURN;
    }

    public void setRetorno(String retorno) {
        this.RETURN = retorno;
    }

    public String getGestionDest() {
        return DESTINY_MANAGEMENT;
    }

    public void setGestionDest(String gestionDest) {
        this.DESTINY_MANAGEMENT = gestionDest;
    }

    public String getPortesDebidos() {
        return DUE_POSTAGE;
    }

    public void setPortesDebidos(String portesDebidos) {
        this.DUE_POSTAGE = portesDebidos;
    }

    public String getFormaPago() {
        return PAY_WAY;
    }

    public void setFormaPago(String formaPago) {
        this.PAY_WAY = formaPago;
    }

    public String getEmail() {
        return EMAIL;
    }

    public void setEmail(String email) {
        this.EMAIL = email;
    }

    public String getPais() {
        return COUNTRY;
    }

    public void setPais(String pais) {
        this.COUNTRY = pais;
    }

    public String getGls() {
        return GLS;
    }

    public void setGls(String gls) {
        this.GLS = gls;
    }

    public Zone getZona() {
        return zone;
    }

    public void setZona(Zone zona) {
        this.zone = zona;
    }

    
    
    @Override
    public String toString() {
        return "Register{" + "cliente=" + CLIENT.trim() + ", departamento=" + DEPARTAMENT.trim() +
                ", ref=" + REF.trim() + ", fecha=" + DATE.trim() + ", typeServ=" + TYPE_SERV.trim() +
                ", variante=" + VARIANT.trim() + ", nombreRem=" + SENDER_NAME.trim() + ", direcRem=" +
                SENDER_ADDRESS.trim() + ", poblacionRem=" + SENDER_CITY.trim() + ", nombreDestino=" +
                DESTINATION_NAME.trim() + ", pisoDestino=" + DESTINATION_APARTMENT.trim() + ", tfnoDestino=" +
                DESTINATION_PHONE.trim() + ", poblaDestino=" + DESTINATION_CITY.trim() + ", postalDestino=" +
                DESTINATION_POSTAL_CODE.trim() + ", bultos=" + BUNDLES.trim() + ", documentos=" + DOCUMENTS.trim() +
                ", paquetes=" + PACKAGES.trim() + ", ancho=" + WIDTH.trim() + ", alto=" + HEIGHT.trim() +
                ", largo=" + LONG.trim() + ", peso=" + WEIGHT.trim() + ", reembolso=" + REFUND.trim() +
                ", valor=" + VALUE.trim() + ", ctaCliente=" + CLIENT_BILL.trim() + ", moneda=" +
                COIN.trim() + ", observaciones=" + OBSERVATIONS.trim() + ", sabado=" + SATURDAY.trim() +
                ", horaEntr=" + IN_TIME.trim() + ", retorno=" + RETURN.trim() + ", gestionDest=" +
                DESTINY_MANAGEMENT.trim() + ", portesDebidos=" + DUE_POSTAGE.trim() + ", formaPago=" +
                PAY_WAY.trim() + ", email=" + EMAIL.trim() + ", pais=" + COUNTRY.trim() +
                ", gls=" + GLS.trim() + '}';
    }

    
}
