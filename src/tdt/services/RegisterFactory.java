package tdt.services;

import java.lang.reflect.Field;
import java.util.HashMap;
import tdt.db.daoImpl.VariableArchivoImpl;
import tdt.model.Note;
import tdt.model.FileVariable;
import tdt.db.dao.IFileVariableDao;

public class RegisterFactory {

    private static Note albaran;

    private static IFileVariableDao variableDao;

    private static HashMap<String, FileVariable> list;

    public static Note generarAlbaran(String data) {

        variableDao = new VariableArchivoImpl();

        list = variableDao.hashMapFileVariable();

        try {
            // ==== SE RESTA 1 A CADA VALOR PORQUE EL CLIENTE EMPIEZA A CONTAR EN 1 Y NO EN 0 COMO UN ARRAY ==== //
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

            albaran = new Note(
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
        return albaran;
    }

    public static String generarRegistroAlbaran(Note al) {
        
        String cliente = al.getCliente();
        String departamento = al.getDepartamento();
        if (al.getNewRef() != null) {
            al.setRef(al.getNewRef());
            al.setNewRef(null);
        }
        String ref = al.getRef();
        String fecha = al.getFecha();
        String typeSer = al.getTypeServ();
        String variante = al.getVariante();
        String nombreRem = al.getNombreRem();
        String direcRem = al.getDirecRem();
        String poblacionRem = al.getPoblacionRem();
        String nombreDestino = al.getNombreDestino();
        String direcDestino = al.getDirecDestino();
        String viaDestino = al.getViaDestino();
        String numeroDestino = al.getNumeroDestino();
        String pisoDestino = al.getPisoDestino();
        String tfnoDestino = al.getTfnoDestino();
        String poblaDestino = al.getPoblaDestino();
        String postalDestino = al.getPostalDestino();
        String bultos = al.getBultos();
        String documentos = al.getDocumentos();
        String paquetes = al.getPaquetes();
        String ancho = al.getAncho();
        String alto = al.getAlto();
        String largo = al.getLargo();
        String peso = al.getPeso();
        String reembolso = al.getReembolso();
        String valor = al.getValor();
        String ctaCliente = al.getCtaCliente();
        String moneda = al.getMoneda();
        String observaciones = al.getObservaciones();
        String sabado = al.getSabado();
        String horaEntr = al.getHoraEntr();
        String retorno = al.getRetorno();
        String gestionDest = al.getGestionDest();
        String portesDebidos = al.getPortesDebidos();
        String formaPago = al.getFormaPago();
        String email = al.getEmail();
        String pais = al.getPais();
        String gls = al.getGls();

        String[] dataList = {cliente, departamento, ref, fecha, typeSer, variante, nombreRem, direcRem, poblacionRem, nombreDestino, direcDestino, viaDestino, numeroDestino, pisoDestino,
            tfnoDestino, poblaDestino, postalDestino, bultos, documentos, paquetes, ancho, alto, largo, peso, reembolso, valor, ctaCliente, moneda, observaciones, sabado, horaEntr, retorno, gestionDest,
            portesDebidos, formaPago, email, pais, gls};

        char fill = ' ';

        String line = "";

        Field[] fields = Note.class.getDeclaredFields();

        for (int i = 0; i < fields.length - 1; i++) {
            if (!fields[i].getName().equals("zona") && !fields[i].getName().equals("newRef") && !fields[i].getName().equals("MEJOR_AGENCIA")) {

                int width = (list.get(fields[i].getName()).getEnd()) - (list.get(fields[i].getName()).getStart()) - dataList[i].length();

                line += dataList[i] + new String(new char[width]).replace('\0', fill);
            }
        }

        return line;
    }
}
