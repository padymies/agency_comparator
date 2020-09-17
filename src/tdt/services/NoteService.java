
package tdt.services;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import tdt.db.dao.IZoneDao;
import tdt.db.daoImpl.ZoneImpl;
import tdt.model.Note;
import tdt.model.Zone;

public class NoteService {

    private static IZoneDao zoneDao;

    public static Zone setNoteZone(Note note, Label lbZone, ListView list) {

        // TODO: Añadir ciudades aquí 
        // 1- Comprobamos pais
        // 2- Comprobamos si hay ciudad (xxxx)
        // 3- Comprobamos el codigo postal (xx)
        
        String country = note.getCountry();

        String postalCode = null;
        
        if (note.getDestinationPostalCode() != null && note.getDestinationPostalCode().length() >= 2) {
           // cp = albaran.getDestinationPostalCode().substring(0, 2);
            postalCode = note.getDestinationPostalCode();

        }

        Zone zone = null;

        zoneDao = new ZoneImpl();

        if (country.toLowerCase().equals("españa")) {

            zone = zoneDao.getZoneByCity(postalCode);
            
            if(zone == null) {
                postalCode = note.getDestinationPostalCode().substring(0, 2);
                zone = zoneDao.getZoneByCity(postalCode);
            }
            
        } else {
            zone = zoneDao.getZoneByCountry(note.getCountry());
        }

        if (zone != null) {
            if (lbZone.getStyleClass().contains("noZona")) {
                lbZone.getStyleClass().remove("noZona");
            }
        } else {
            noZoneMark(lbZone, list);
        }

        return zone;
    }

    public static void noZoneMark(Label label, ListView list) {
        if (!label.getStyleClass().contains("noZona")) {
            label.setText("No se ha encontrado una Zona");
            label.getStyleClass().add("noZona");

        }

    }

}
