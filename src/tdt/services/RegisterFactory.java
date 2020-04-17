package tdt.services;

import tdt.model.Register;

public class RegisterFactory {

    private static Register register;

    public static Register generateRegister(String data) {

        try {

            String cliente = data.substring(0, 4);
            String departamento = data.substring(5, 9);
            String ref = data.substring(10, 39);
            String fecha = data.substring(40, 47);
            String typeSer = data.substring(48, 51);
            String variante = data.substring(52, 52);
            String nombreRem = data.substring(53, 82);
            String direcRem = data.substring(83, 112);
            String poblacionRem = data.substring(113, 142);
            String nombreDestino = data.substring(143, 182);
            String direcDestino = data.substring(183, 282);
            String viaDestino = data.substring(283, 285);
            String numeroDestino = data.substring(286, 295);
            String pisoDestino = data.substring(296, 297);
            String tfnoDestino = data.substring(298, 309);
            String poblaDestino = data.substring(310, 349);
            String postalDestino = data.substring(350, 354);
            String bultos = data.substring(355, 357);
            String documentos = data.substring(358, 360);
            String paquetes = data.substring(361, 363);
            String ancho = data.substring(364, 366);
            String alto = data.substring(367, 369);
            String largo = data.substring(370, 372);
            String peso = data.substring(373, 384);
            String reembolso = data.substring(385, 396);
            String valor = data.substring(397, 408);
            String ctaCliente = data.substring(409, 420);
            String moneda = data.substring(421, 421);
            String observaciones = data.substring(422, 491);
            String sabado = data.substring(492, 492);
            String horaEntr = data.substring(493, 497);
            String retorno = data.substring(498, 498);
            String gestionDest = data.substring(499, 499);
            String portesDebidos = data.substring(500, 500);
            String formaPago = data.substring(501, 503);
            String email = data.substring(504, 553);
            String pais = data.substring(554, 593);
            String gls = data.substring(594, 599);

            register = new Register(
                    cliente, departamento, ref, fecha, typeSer, variante,
                    nombreRem, direcRem, poblacionRem, nombreDestino, direcDestino,
                    viaDestino, numeroDestino, pisoDestino,
                    tfnoDestino, poblaDestino, postalDestino, bultos, documentos,
                    paquetes, ancho, alto, largo, peso, reembolso, valor, ctaCliente,
                    moneda, observaciones, sabado, horaEntr, retorno, gestionDest,
                    portesDebidos, formaPago, email, pais, gls);

        } catch (Exception e) {

            System.out.println("ERROR AL GENERAR ALBARÁN: " + e.getMessage());
            e.getMessage();
        }
        return register;
    }
}
