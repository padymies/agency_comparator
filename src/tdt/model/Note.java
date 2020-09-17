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

    public String getBEST_AGENCY() {
        return BEST_AGENCY;
    }

    public void setBEST_AGENCY(String BEST_AGENCY) {
        this.BEST_AGENCY = BEST_AGENCY;
    }

    
    public String getClient() {
        return CLIENT;
    }

    public void setClient(String client) {
        this.CLIENT = client;
    }

    public String getDepartament() {
        return DEPARTAMENT;
    }

    public void setDepartament(String departament) {
        this.DEPARTAMENT = departament;
    }

    public String getRef() {
        return REF;
    }

    public void setRef(String ref) {
        this.REF = ref;
    }

    public String getDate() {
        return DATE;
    }

    public void setDate(String date) {
        this.DATE = date;
    }

    public String getTypeServ() {
        return TYPE_SERV;
    }

    public void setTypeServ(String typeServ) {
        this.TYPE_SERV = typeServ;
    }

    public String getVariant() {
        return VARIANT;
    }

    public void setVariant(String variant) {
        this.VARIANT = variant;
    }

    public String getSenderName() {
        return SENDER_NAME;
    }

    public void setSenderName(String senderName) {
        this.SENDER_NAME = senderName;
    }

    public String getSenderAddress() {
        return SENDER_ADDRESS;
    }

    public void setSenderAddress(String senderAddress) {
        this.SENDER_ADDRESS = senderAddress;
    }

    public String getSenderCity() {
        return SENDER_CITY;
    }

    public void setSenderCity(String senderCity) {
        this.SENDER_CITY = senderCity;
    }

    public String getDestinyName() {
        return DESTINATION_NAME;
    }

    public void setDestinyName(String destinyName) {
        this.DESTINATION_NAME = destinyName;
    }

    public String getDestinationAddress() {
        return DESTINATION_ADDRESS;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.DESTINATION_ADDRESS = destinationAddress;
    }

    public String getDestinationRoad() {
        return DESTINATION_ROAD;
    }

    public void setDestinationRoad(String destinationRoad) {
        this.DESTINATION_ROAD = destinationRoad;
    }

    public String getDestinationNumber() {
        return DESTINATION_NUMBER;
    }

    public void setDestinationNumber(String destinationNumber) {
        this.DESTINATION_NUMBER = destinationNumber;
    }

    public String getDestinationApartment() {
        return DESTINATION_APARTMENT;
    }

    public void setDestinationApartment(String destinationApartment) {
        this.DESTINATION_APARTMENT = destinationApartment;
    }

    public String getDestinationPhone() {
        return DESTINATION_PHONE;
    }

    public void setDestinationPhone(String destinationPhone) {
        this.DESTINATION_PHONE = destinationPhone;
    }

    public String getDestinationCity() {
        return DESTINATION_CITY;
    }

    public void setDestinationCity(String destinationCity) {
        this.DESTINATION_CITY = destinationCity;
    }

    public String getDestinationPostalCode() {
        return DESTINATION_POSTAL_CODE;
    }

    public void setDestinationPostalCode(String destinationPostalCode) {
        this.DESTINATION_POSTAL_CODE = destinationPostalCode;
    }

    public String getBundles() {
        return BUNDLES;
    }

    public void setBundles(String bundles) {
        this.BUNDLES = bundles;
    }

    public String getDocuments() {
        return DOCUMENTS;
    }

    public void setDocuments(String documents) {
        this.DOCUMENTS = documents;
    }

    public String getPackages() {
        return PACKAGES;
    }

    public void setPackages(String packages) {
        this.PACKAGES = packages;
    }

    public String getWidth() {
        return WIDTH;
    }

    public void setWidth(String width) {
        this.WIDTH = width;
    }

    public String getHeight() {
        return HEIGHT;
    }

    public void setHeight(String height) {
        this.HEIGHT = height;
    }

    public String getLong() {
        return LONG;
    }

    public void setLong(String _long) {
        this.LONG = _long;
    }

    public String getWeight() {
        return WEIGHT;
    }

    public void setWeight(String weight) {
        this.WEIGHT = weight;
    }

    public String getRefund() {
        return REFUND;
    }

    public void setRefund(String refund) {
        this.REFUND = refund;
    }

    public String getValue() {
        return VALUE;
    }

    public void setValue(String value) {
        this.VALUE = value;
    }

    public String getClientBill() {
        return CLIENT_BILL;
    }

    public void setClientBill(String clientBill) {
        this.CLIENT_BILL = clientBill;
    }

    public String getCoin() {
        return COIN;
    }

    public void setCoin(String coin) {
        this.COIN = coin;
    }

    public String getObservations() {
        return OBSERVATIONS;
    }

    public void setObservations(String observations) {
        this.OBSERVATIONS = observations;
    }

    public String getSaturday() {
        return SATURDAY;
    }

    public void setSaturday(String saturday) {
        this.SATURDAY = saturday;
    }

    public String getInTime() {
        return IN_TIME;
    }

    public void setInTime(String inTime) {
        this.IN_TIME = inTime;
    }

    public String getReturn() {
        return RETURN;
    }

    public void setReturn(String _return) {
        this.RETURN = _return;
    }

    public String getDestinyManagement() {
        return DESTINY_MANAGEMENT;
    }

    public void setDestinyManagement(String destinyManagement) {
        this.DESTINY_MANAGEMENT = destinyManagement;
    }

    public String getDuePostage() {
        return DUE_POSTAGE;
    }

    public void setDuePostage(String duePostage) {
        this.DUE_POSTAGE = duePostage;
    }

    public String getPayWay() {
        return PAY_WAY;
    }

    public void setPayWay(String payWay) {
        this.PAY_WAY = payWay;
    }

    public String getEmail() {
        return EMAIL;
    }

    public void setEmail(String email) {
        this.EMAIL = email;
    }

    public String getCountry() {
        return COUNTRY;
    }

    public void setCountry(String country) {
        this.COUNTRY = country;
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
