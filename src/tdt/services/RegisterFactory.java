package tdt.services;

import java.lang.reflect.Field;
import java.util.HashMap;
import tdt.db.dao.IFileVariableDao;
import tdt.db.daoImpl.FileVariableImpl;
import tdt.model.FileVariable;
import tdt.model.Note;

public class RegisterFactory {

    private static Note note;

    private static IFileVariableDao variableDao;

    private static HashMap<String, FileVariable> list;

    public static Note generateNote(String data) {

        variableDao = new FileVariableImpl();

        list = variableDao.hashMapFileVariable();

        try {
            String cliente = data.substring(list.get("CLIENTE").getStart() - 1, list.get("CLIENTE").getEnd() - 1).trim();
            String departamento = data.substring(list.get("DEPARTAMENTO").getStart() - 1, list.get("DEPARTAMENTO").getEnd() - 1).trim();
            String ref = data.substring(list.get("REF").getStart() - 1, list.get("REF").getEnd() - 1).trim();
            String fecha = data.substring(list.get("FECHA").getStart() - 1, list.get("FECHA").getEnd() - 1).trim();
            String typeSer = data.substring(list.get("TIPO_SERV").getStart() - 1, list.get("TIPO_SERV").getEnd() - 1).trim();
            String variante = data.substring(list.get("VARIANTE").getStart() - 1, list.get("VARIANTE").getEnd() - 1).trim();
            String nombreRem = data.substring(list.get("NOMBRE_REM").getStart() - 1, list.get("NOMBRE_REM").getEnd() - 1).trim();
            String direcRem = data.substring(list.get("DIRECC_REM").getStart() - 1, list.get("DIRECC_REM").getEnd() - 1).trim();
            String poblacionRem = data.substring(list.get("POBLACION_REM").getStart() - 1, list.get("POBLACION_REM").getEnd() - 1).trim();
            String nombreDestino = data.substring(list.get("NOMBRE_DESTINO").getStart() - 1, list.get("NOMBRE_DESTINO").getEnd() - 1).trim();
            String direcDestino = data.substring(list.get("DIRECC_DESTINO").getStart() - 1, list.get("DIRECC_DESTINO").getEnd() - 1).trim();
            String viaDestino = data.substring(list.get("VIA_DESTINO").getStart() - 1, list.get("VIA_DESTINO").getEnd() - 1).trim();
            String numeroDestino = data.substring(list.get("NUMERO_DESTINO").getStart() - 1, list.get("NUMERO_DESTINO").getEnd() - 1).trim();
            String pisoDestino = data.substring(list.get("PISO_DESTINO").getStart() - 1, list.get("PISO_DESTINO").getEnd() - 1).trim();
            String tfnoDestino = data.substring(list.get("TFNO_DESTINO").getStart() - 1, list.get("TFNO_DESTINO").getEnd() - 1).trim();
            String poblaDestino = data.substring(list.get("POBLACION_DESTINO").getStart() - 1, list.get("POBLACION_DESTINO").getEnd() - 1).trim();
            String postalDestino = data.substring(list.get("POSTAL_DESTINO").getStart() - 1, list.get("POSTAL_DESTINO").getEnd() - 1).trim();
            String bultos = data.substring(list.get("BULTOS").getStart() - 1, list.get("BULTOS").getEnd() - 1).trim();
            String documentos = data.substring(list.get("DOCUMENTOS").getStart() - 1, list.get("DOCUMENTOS").getEnd() - 1).trim();
            String paquetes = data.substring(list.get("PAQUETES").getStart() - 1, list.get("PAQUETES").getEnd() - 1).trim();
            String ancho = data.substring(list.get("ANCHO").getStart() - 1, list.get("ANCHO").getEnd() - 1).trim();
            String alto = data.substring(list.get("ALTO").getStart() - 1, list.get("ALTO").getEnd() - 1).trim();
            String largo = data.substring(list.get("LARGO").getStart() - 1, list.get("LARGO").getEnd() - 1).trim();
            String peso = data.substring(list.get("PESO").getStart() - 1, list.get("PESO").getEnd() - 1).trim();
            String reembolso = data.substring(list.get("REEMBOLSO").getStart() - 1, list.get("REEMBOLSO").getEnd() - 1).trim();
            String valor = data.substring(list.get("VALOR").getStart() - 1, list.get("VALOR").getEnd() - 1).trim();
            String ctaCliente = data.substring(list.get("CUENTA_CLIENTE").getStart() - 1, list.get("CUENTA_CLIENTE").getEnd() - 1).trim();
            String moneda = data.substring(list.get("MONEDA").getStart() - 1, list.get("MONEDA").getEnd() - 1).trim();
            String observaciones = data.substring(list.get("OBSERVACIONES").getStart() - 1, list.get("OBSERVACIONES").getEnd() - 1).trim();
            String sabado = data.substring(list.get("SABADO").getStart() - 1, list.get("SABADO").getEnd() - 1).trim();
            String horaEntr = data.substring(list.get("HORA_ENTRADA").getStart() - 1, list.get("HORA_ENTRADA").getEnd() - 1).trim();
            String retorno = data.substring(list.get("RETORNO").getStart() - 1, list.get("RETORNO").getEnd() - 1).trim();
            String gestionDest = data.substring(list.get("GESTION_DESTINO").getStart() - 1, list.get("GESTION_DESTINO").getEnd() - 1).trim();
            String portesDebidos = data.substring(list.get("PORTES_DEBIDOS").getStart() - 1, list.get("PORTES_DEBIDOS").getEnd() - 1).trim();
            String formaPago = data.substring(list.get("FORMA_PAGO").getStart() - 1, list.get("FORMA_PAGO").getEnd() - 1).trim();
            String email = data.substring(list.get("EMAIL").getStart() - 1, list.get("EMAIL").getEnd() - 1).trim();
            String pais = data.substring(list.get("PAIS").getStart() - 1, list.get("PAIS").getEnd() - 1).trim();
            String gls = data.substring(list.get("GLS").getStart() - 1, list.get("GLS").getEnd() - 1).trim();

            note = new Note(
                    cliente, departamento, ref, fecha, typeSer, variante,
                    nombreRem, direcRem, poblacionRem, nombreDestino, direcDestino,
                    viaDestino, numeroDestino, pisoDestino,
                    tfnoDestino, poblaDestino, postalDestino, bultos, documentos,
                    paquetes, ancho, alto, largo, peso, reembolso, valor, ctaCliente,
                    moneda, observaciones, sabado, horaEntr, retorno, gestionDest,
                    portesDebidos, formaPago, email, pais, gls);

        } catch (Exception e) {

            AlertExceptionService alert = new AlertExceptionService("Generar Albarán", "No se ha podido generar el albarán", e);

            alert.showAndWait();
        }
        return note;
    }

    public static String generateNoteRegister(Note note) {
        
        String client = note.getClient();
        String departament = note.getDepartament();
        if (note.getNewRef() != null) {
            note.setRef(note.getNewRef());
            note.setNewRef(null);
        }
        String ref = note.getRef();
        String date = note.getDate();
        String typeSer = note.getTypeServ();
        String variant = note.getVariant();
        String senderName = note.getSenderName();
        String senderAddress = note.getSenderAddress();
        String senderCity = note.getSenderCity();
        String destinationName = note.getDestinationName();
        String destinationAddress = note.getDestinationAddress();
        String destinationRoad = note.getDestinationRoad();
        String destinationNumber = note.getDestinationNumber();
        String destinationApartment = note.getDestinationApartment();
        String destinationPhone = note.getDestinationPhone();
        String destinationCity = note.getDestinationCity();
        String destinationPostalCode = note.getDestinationPostalCode();
        String bundles = note.getBundles();
        String documents = note.getDocuments();
        String packages = note.getPackages();
        String width = note.getWidth();
        String height = note.getHeight();
        String _long = note.getLong();
        String weight = note.getWeight();
        String refund = note.getRefund();
        String value = note.getValue();
        String clientBill = note.getClientBill();
        String coin = note.getCoin();
        String observations = note.getObservations();
        String saturday = note.getSaturday();
        String inTime = note.getInTime();
        String _return = note.getReturn();
        String destinationManagement = note.getDestinationManagement();
        String duePostage = note.getDuePostage();
        String payWay = note.getPayWay();
        String email = note.getEmail();
        String country = note.getCountry();
        String gls = note.getGls();

        String[] dataList = {client, departament, ref, date, typeSer, variant, senderName, senderAddress, senderCity, destinationName, destinationAddress, destinationRoad, destinationNumber, destinationApartment,
            destinationPhone, destinationCity, destinationPostalCode, bundles, documents, packages, width, height, _long, weight, refund, value, clientBill, coin, observations, saturday, inTime, _return, destinationManagement,
            duePostage, payWay, email, country, gls};

        char fill = ' ';

        String line = "";

        Field[] fields = Note.class.getDeclaredFields();

        for (int i = 0; i < fields.length - 1; i++) {
            if (!fields[i].getName().equals("zone") && !fields[i].getName().equals("newRef") && !fields[i].getName().equals("BEST_AGENCY") && !fields[i].getName().equals("bigShipment")) {

                int widthField = (list.get(fields[i].getName()).getEnd()) - (list.get(fields[i].getName()).getStart()) - dataList[i].length();

                line += dataList[i] + new String(new char[widthField]).replace('\0', fill);
            }
        }

        return line;
    }
}
