
package tdt.services;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import tdt.db.daoImpl.ZonaImpl;
import tdt.model.Note;
import tdt.model.Zone;
import tdt.db.dao.IZoneDao;

public class AlbaranService {

    private static IZoneDao zonaDao;

    public static Zone setAlbaranZona(Note albaran, Label lbZona, ListView list) {

        // TODO: Añadir ciudades aquí 
        // 1- Comprobamos pais
        // 2- Comprobamos si hay ciudad (xxxx)
        // 3- Comprobamos el codigo postal (xx)
        
        String pais = albaran.getPais();

        String cp = null;
        
        if (albaran.getPostalDestino() != null && albaran.getPostalDestino().length() >= 2) {
           // cp = albaran.getPostalDestino().substring(0, 2);
            cp = albaran.getPostalDestino();

        }

        Zone zona = null;

        zonaDao = new ZonaImpl();

        if (pais.toLowerCase().equals("españa")) {

            zona = zonaDao.getZoneByCity(cp);
            
            if(zona == null) {
                cp = albaran.getPostalDestino().substring(0, 2);
                zona = zonaDao.getZoneByCity(cp);
            }
            
        } else {
            zona = zonaDao.getZoneByCountry(albaran.getPais());
        }

        if (zona != null) {
            if (lbZona.getStyleClass().contains("noZona")) {
                lbZona.getStyleClass().remove("noZona");
            }
        } else {
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

}
