package tdt.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import tdt.db.dao.IAgencyDao;
import tdt.db.dao.IAppConfig;
import tdt.db.dao.IExclusionDao;
import tdt.db.dao.IRateDao;
import tdt.db.dao.IZoneDao;
import tdt.db.daoImpl.AgencyImpl;
import tdt.db.daoImpl.AppConfigImpl;
import tdt.db.daoImpl.ExclusionsImpl;
import tdt.db.daoImpl.RateImpl;
import tdt.db.daoImpl.ZoneImpl;
import tdt.model.Agency;
import tdt.model.AgencyZone;
import tdt.model.Exclusion;
import tdt.model.Note;
import tdt.model.RateComparator;

public class ComparatorService {

    private LoggerService logger;

    private final int AGENCY_FORCED = 1;

    private final int AGENCY_EXCLUDED = -1;

    private IExclusionDao exclusionsDao;

    private IAgencyDao agenciesDao;

    private IRateDao rateDao;

    private IZoneDao zoneDao;

    private IAppConfig configDao;

    public ComparatorService() {

        logger = new LoggerService("output");

        rateDao = new RateImpl();

        zoneDao = new ZoneImpl();

        agenciesDao = new AgencyImpl();

        configDao = new AppConfigImpl();
    }

    public void noteCompare(ArrayList<Note> notes) {

        for (Note note : notes) {

            ObservableList<AgencyZone> agenciesList = rateDao.getAgenciesByZone(note.getZone().getZoneId());

            if (!hasExclusions(note, agenciesList)) {
                // ================= 1- SE COMPRUEBA QUE NO ESTÉ FORZADA LA AGENCIA ====================//
                if (note.getBEST_AGENCY() == null) {

                    ArrayList<RateComparator> resultList = new ArrayList<>();

                    if (agenciesList.size() > 0) { // Prueba 
                        for (AgencyZone agencyZone : agenciesList) {

                            // Kilo mayor de la tarifa de la agencia_zona
                            int maxKilos = rateDao.getMaxKilo(agencyZone.getAgencyId(), agencyZone.getZoneId());

                            double weight = -1;

                            try {

                                weight = Math.ceil(Double.parseDouble(note.getWeight()));

                            } catch (NumberFormatException e) {

                                AlertExceptionService weightAlert = new AlertExceptionService("Error", "Error en parseo de peso", e);

                                weightAlert.showAndWait();
                            }

                            RateComparator result = null;

                            if (maxKilos > 0) {
                                // ================ 2- SE COMPRUEBA QUE NO SOBREPASE EL MAXIMO DE KILOS ==============//
                                if (weight != -1 && (agencyZone.getMaxKilos() == 0 || agencyZone.getMaxKilos() >= weight)) {
                                    if (maxKilos >= weight) {

                                        result = rateDao.ratesNotesCompare(weight, note.getZone().getZoneId(), agencyZone.getAgencyId());

                                    } else {
                                        if (agencyZone.getIncrease() > 0) { // SE APLICA INCREMENTO SI TIENE

                                            result = rateDao.ratesNotesCompare(maxKilos, note.getZone().getZoneId(), agencyZone.getAgencyId());

                                            result.setPrice(result.getPrice() + (weight - maxKilos * agencyZone.getIncrease()));

                                        } else {
                                            logger.writeLog("info", "REF: " + note.getRef() + " ============ NO HAY TARIFA PARA EL PESO  " + weight + " EN LA ZONA "
                                                    + note.getZone().getName() + " PARA LA AGENCIA " + agencyZone.getAgencyName() + " ============\n Acción: Revise las tarifas de la zona\n", null);
                                        }
                                    }
                                }
                            } else {
                                logger.writeLog("info", "REF: " + note.getRef() + "============ NO HAY TARIFAS PARA LA AGENCIA  " + agencyZone.getAgencyName() + " EN LA ZONA "
                                        + note.getZone().getName() + " ============\n Acción: Revise las tarifas de la zona\n", null);
                            }

                            if (result != null) {

                                resultList.add(result);

                            }

                        }
                    } else {
                        logger.writeLog("info", "REF: " + note.getRef() + " ============ NO HAY AGENCIAS PARA LA ZONA:  " + note.getZone().getName() + " ============\n Acción: Revise la Zona\n", null);
                    }
                    // ================ 3-COMPROBAMOS EL RESTO DE VARIABLES PARA DETERMINAR EL PRECIO FINAL ================//

                    if (resultList.size() > 0) {

                        resultList.forEach(item -> {

                            if (item.getSurchargeFuel() > 0) {

                                double percent = item.getSurchargeFuel() * item.getPrice() / 100;

                                item.setPrice(item.getPrice() + percent);
                            }

                            if (note.getRefund() != null) {

                                double refundPrice = 0;

                                double comisionPrice = 0;

                                if (item.getMinimumRefund() > 0) {
                                    refundPrice = item.getPrice() + item.getMinimumRefund();

                                }

                                if (item.getComision() > 0) {

                                    double percent = item.getPrice() * item.getComision() / 100;

                                    comisionPrice = item.getPrice() + percent;
                                }

                                if (comisionPrice > 0 && comisionPrice >= refundPrice) {
                                    item.setPrice(comisionPrice);
                                }

                                if (refundPrice > 0 && refundPrice > comisionPrice) {
                                    item.setPrice(refundPrice);

                                }

                            }

                        });

                        Collections.sort(resultList, Comparator.comparing(item -> item.getPrice()));

                        RateComparator first = resultList.get(0);

                        double urgencyPercent = configDao.getUrgencyPercent();

                        double urgencyPrice = 0;

                        urgencyPrice = first.getPrice() + (first.getPrice() * urgencyPercent / 100);

                        if (urgencyPrice > 0) {

                            if (first.getDeliveryTime() > 1) {
                                for (int i = 1; i < resultList.size(); i++) {
                                    if (resultList.get(i).getDeliveryTime() < first.getDeliveryTime()
                                            && resultList.get(i).getPrice() - first.getPrice() < urgencyPrice) {

                                        note.setBEST_AGENCY(resultList.get(i).getAgencyName());
                                        break;
                                    }
                                }
                            }
                        }

                        if (note.getBEST_AGENCY() == null) {
                            note.setBEST_AGENCY(first.getAgencyName());
                        }

                    }

                }
            }
        }

    }

    private boolean hasExclusions(Note note, ObservableList<AgencyZone> list) {

        boolean result = true;

        exclusionsDao = new ExclusionsImpl();

        Exclusion ex = exclusionsDao.getExclusionByPostalCode(note.getDestinationPostalCode());

        if (ex != null) {
            switch (ex.getInclusion_exclusion()) {
                case AGENCY_FORCED:

                    agenciesDao = new AgencyImpl();

                    Agency agency = agenciesDao.getAgency(ex.getAgencyId());

                    note.setBEST_AGENCY(agency.getName());

                    break;
                case AGENCY_EXCLUDED:

                    if (list != null) {

                        list.forEach(item -> {

                            if (item.getAgencyId() == ex.getAgencyId()) {

                                list.remove(item);

                            }
                        });
                    }
                    break;
                default:
                    logger.writeLog("info", "REF: " + note.getRef() + " ============ EXCLUSIONES: El código postal " + ex.getPostalCode() + ""
                            + " No se puede enviar por ninguna agencia ============\n Acción: Revise las exclusiones\n", null);
                    AlertService alert = new AlertService(Alert.AlertType.INFORMATION, "Información de Salida", "Aviso de exclusión:\n"
                            + "Referencia:  " + note.getRef() + "\nCP  " + note.getDestinationPostalCode() + "", "\n"
                            + "El código postal está excluido");
                    alert.show();
                    break;
            }
        } else {
            return false;
        }

        return result;
    }

}
