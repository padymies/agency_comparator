package tdt.services;

import java.util.Arrays;
import tdt.model.Register;

public class RegisterFactory {

    private static Register register;

    private static PropertyService props;

    public static Register generateRegister(String data) {
        props = new PropertyService();
        try {

            String cliente = data.substring(props.getPropsInt("CLIENTE.START"), props.getPropsInt("CLIENTE.END"));
            String departamento = data.substring(props.getPropsInt("DEPARTAMENTO.START"), props.getPropsInt("DEPARTAMENTO.END"));
            String ref = data.substring(props.getPropsInt("REF.START"), props.getPropsInt("REF.END"));
            String fecha = data.substring(props.getPropsInt("FECHA.START"), props.getPropsInt("FECHA.END"));
            String typeSer = data.substring(props.getPropsInt("TYPESERV.START"), props.getPropsInt("TYPESERV.END"));
            String variante = data.substring(props.getPropsInt("VARIANTE.START"), props.getPropsInt("VARIANTE.END"));
            String nombreRem = data.substring(props.getPropsInt("NOMBREREM.START"), props.getPropsInt("NOMBREREM.END"));
            String direcRem = data.substring(props.getPropsInt("DIRECREM.START"), props.getPropsInt("DIRECREM.END"));
            String poblacionRem = data.substring(props.getPropsInt("POBLACIONREM.START"), props.getPropsInt("POBLACIONREM.END"));
            String nombreDestino = data.substring(props.getPropsInt("NOMBREDESTINO.START"), props.getPropsInt("NOMBREDESTINO.END"));
            String direcDestino = data.substring(props.getPropsInt("DIRECDESTINO.START"), props.getPropsInt("DIRECDESTINO.END"));
            String viaDestino = data.substring(props.getPropsInt("VIADESTINO.START"), props.getPropsInt("VIADESTINO.END"));
            String numeroDestino = data.substring(props.getPropsInt("NUMERODESTINO.START"), props.getPropsInt("NUMERODESTINO.END"));
            String pisoDestino = data.substring(props.getPropsInt("PISODESTINO.START"), props.getPropsInt("PISODESTINO.END"));
            String tfnoDestino = data.substring(props.getPropsInt("TFNODESTINO.START"), props.getPropsInt("TFNODESTINO.END"));
            String poblaDestino = data.substring(props.getPropsInt("POBLADESTINO.START"), props.getPropsInt("POBLADESTINO.END"));
            String postalDestino = data.substring(props.getPropsInt("POSTALDESTINO.START"), props.getPropsInt("POSTALDESTINO.END"));
            String bultos = data.substring(props.getPropsInt("BULTOS.START"), props.getPropsInt("BULTOS.END"));
            String documentos = data.substring(props.getPropsInt("DOCUMENTOS.START"), props.getPropsInt("DOCUMENTOS.END"));
            String paquetes = data.substring(props.getPropsInt("PAQUETES.START"), props.getPropsInt("PAQUETES.END"));
            String ancho = data.substring(props.getPropsInt("ANCHO.START"), props.getPropsInt("ANCHO.END"));
            String alto = data.substring(props.getPropsInt("ALTO.START"), props.getPropsInt("ALTO.END"));
            String largo = data.substring(props.getPropsInt("LARGO.START"), props.getPropsInt("LARGO.END"));
            String peso = data.substring(props.getPropsInt("PESO.START"), props.getPropsInt("PESO.END"));
            String reembolso = data.substring(props.getPropsInt("REEMBOLSO.START"), props.getPropsInt("REEMBOLSO.END"));
            String valor = data.substring(props.getPropsInt("VALOR.START"), props.getPropsInt("VALOR.END"));
            String ctaCliente = data.substring(props.getPropsInt("CTACLIENTE.START"), props.getPropsInt("CTACLIENTE.END"));
            String moneda = data.substring(props.getPropsInt("MONEDA.START"), props.getPropsInt("MONEDA.END"));
            String observaciones = data.substring(props.getPropsInt("OBSERVACIONES.START"), props.getPropsInt("OBSERVACIONES.END"));
            String sabado = data.substring(props.getPropsInt("SABADO.START"), props.getPropsInt("SABADO.END"));
            String horaEntr = data.substring(props.getPropsInt("HORAENTR.START"), props.getPropsInt("HORAENTR.END"));
            String retorno = data.substring(props.getPropsInt("RETORNO.START"), props.getPropsInt("RETORNO.END"));
            String gestionDest = data.substring(props.getPropsInt("GESTIONDEST.START"), props.getPropsInt("GESTIONDEST.END"));
            String portesDebidos = data.substring(props.getPropsInt("PORTESDEBIDOS.START"), props.getPropsInt("PORTESDEBIDOS.END"));
            String formaPago = data.substring(props.getPropsInt("FORMAPAGO.START"), props.getPropsInt("FORMAPAGO.END"));
            String email = data.substring(props.getPropsInt("EMAIL.START"), props.getPropsInt("EMAIL.END"));
            String pais = data.substring(props.getPropsInt("PAIS.START"), props.getPropsInt("PAIS.END"));
            String gls = data.substring(props.getPropsInt("GLS.START"), props.getPropsInt("GLS.END"));

            register = new Register(
                    cliente, departamento, ref, fecha, typeSer, variante,
                    nombreRem, direcRem, poblacionRem, nombreDestino, direcDestino,
                    viaDestino, numeroDestino, pisoDestino,
                    tfnoDestino, poblaDestino, postalDestino, bultos, documentos,
                    paquetes, ancho, alto, largo, peso, reembolso, valor, ctaCliente,
                    moneda, observaciones, sabado, horaEntr, retorno, gestionDest,
                    portesDebidos, formaPago, email, pais, gls);

        } catch (Exception e) {

            System.out.println("ERROR AL GENERAR ALBARÁN: " + Arrays.toString(e.getStackTrace()));
        }
        return register;
    }
}
