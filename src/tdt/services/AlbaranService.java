/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.services;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import tdt.db.dao.IZonaDao;
import tdt.db.daoImpl.ZonaImpl;
import tdt.model.Albaran;
import tdt.model.Zona;

/**
 *
 * @author Usuario
 */
public class AlbaranService {

    private static IZonaDao zonaDao;

    public static Zona setAlbaranZona(Albaran albaran, Label lbZona, ListView list) {

        String pais = albaran.getPais();

        String cp = null;
        if (albaran.getPostalDestino() != null && albaran.getPostalDestino().length() >= 2) {
            cp = albaran.getPostalDestino().substring(0, 2);

        }

        Zona zona = null;

        zonaDao = new ZonaImpl();

        if (pais.toLowerCase().equals("españa")) {

            zona = zonaDao.obtenerZonaPorProvincia(cp);

            // System.out.println("Zona por provincia => " + zona);
        } else {
            zona = zonaDao.obtenerZonaPorPais(albaran.getPais());
            // System.out.println("Zona por Pais => " + zona);
        }

        if (zona != null) {
            if (lbZona.getStyleClass().contains("noZona")) {
                // System.out.println("Tiene zona" + lbZona.getStyleClass());
                lbZona.getStyleClass().remove("noZona");
            }
        } else {
            // System.out.println("Tiene zona" + lbZona.getStyleClass());
            marcarSinZona(lbZona, list);
        }

        return zona;
    }

    public static void marcarSinZona(Label label, ListView list) {
        if (!label.getStyleClass().contains("noZona")) {
            label.setText("No se ha encontrado una Zona");
            label.getStyleClass().add("noZona");

        }

    }

    private boolean isExclusion() {
        return true;
    }
}
