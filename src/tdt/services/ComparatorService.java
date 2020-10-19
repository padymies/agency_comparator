package tdt.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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

            if (note.getBEST_AGENCY() == null) {

                logger.writeLog("info", "Comparando albaran " + note.getRef() + "...", null);

                ObservableList<AgencyZone> agenciesList = rateDao.getAgenciesByZone(note.getZone().getZoneId());

                if (!hasExclusions(note, agenciesList)) { // Check exclusions

                    ArrayList<RateComparator> resultList = new ArrayList<>();

                    if (agenciesList.size() > 0) {
                        for (AgencyZone agencyZone : agenciesList) {

                            // Kilo mayor de la tarifa de la agencia_zona
                            RateComparator result = checkKilos(agencyZone, note); // Check kilos y volumen

                            if (result != null) {

                                resultList.add(result);

                            }

                        }

                        resultList = checkBundles(resultList, note); // Check bundles

                        if (resultList.size() > 0) {

                            if (note.isBigShipment()) {

                                checkBigShipment(resultList, note);

                            }
                            if (resultList.size() > 0) {

                                resultList = checkPrice(resultList, note); //Check Price

                                Collections.sort(resultList, Comparator.comparing(item -> item.getPrice())); // Get first

                                RateComparator first = resultList.get(0);

                                checkUrgencyPercent(first, resultList, note); // Check Urgency percent

                                if (note.getBEST_AGENCY() == null) {

                                    note.setBEST_AGENCY(first.getAgencyName());
                                    
                                    logger.writeLog("info", "Agencia seleccionada: " + note.getBEST_AGENCY(), null);
                                }

                            }
                        }
                    } else {
                        logger.writeLog("warning", "No hay agencias registradas para la zona:  " + note.getZone().getName() + ". Acción: Revise las agencias de la Zona", null);
                    }
                    // ================ 3-COMPROBAMOS EL RESTO DE VARIABLES PARA DETERMINAR EL PRECIO FINAL ================//

                }
            } else {
                logger.writeLog("info", "Forzando agencia..." + note.getBEST_AGENCY(), null);
                logger.writeLog("info", "Agencia seleccionada: " + note.getBEST_AGENCY(), null);
            }
            logger.writeLog("info", "FIN DE LA COMPARACIÓN ", null);
        }

    }

    private boolean hasExclusions(Note note, ObservableList<AgencyZone> list) {

        boolean result = true;

        exclusionsDao = new ExclusionsImpl();

        Exclusion ex = exclusionsDao.getExclusionByPostalCode(note.getDestinationPostalCode());

        if (ex != null) {

            if (Integer.valueOf(ex.getAgencyId()) == null && ex.getInclusion_exclusion() != 0) {

                return false;
            }

            if (Integer.valueOf(ex.getAgencyId()) != null && ex.getInclusion_exclusion() == 0) {

                return false;
            }

            switch (ex.getInclusion_exclusion()) {
                case AGENCY_FORCED:

                    agenciesDao = new AgencyImpl();

                    Agency agency = agenciesDao.getAgency(ex.getAgencyId());

                    note.setBEST_AGENCY(agency.getName());
                    
                    logger.writeLog("info", "Aplicando exclusión...", null);
                    logger.writeLog("info", "Forzando agencia " + note.getBEST_AGENCY() + "...", null);
                    logger.writeLog("info", "Agencia seleccionada: " + note.getBEST_AGENCY(), null);

                    break;
                case AGENCY_EXCLUDED:

                    if (list != null) {
                        Iterator<AgencyZone> i = list.iterator();
                        while (i.hasNext()) {
                            AgencyZone agencyZone = i.next();
                            if (agencyZone.getAgencyId() == ex.getAgencyId()) {
                                
                                logger.writeLog("info", "Aplicando exclusión...", null);
                                logger.writeLog("info", "La agencia " + ex.getAgencyName() + " ha sido excluida", null);
                                
                                i.remove();
                            }
                        }

                    }
                    break;

                default:
                    
                    logger.writeLog("info", "Aplicando exclusión...", null);
                    logger.writeLog("info", "No se puede enviar por ninguna agencia. Acción: Revise las exclusiones", null);
                    
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

    private RateComparator checkKilos(AgencyZone agencyZone, Note note) {

        RateComparator result = null;

        int maxKilos = rateDao.getMaxKilo(agencyZone.getAgencyId(), agencyZone.getZoneId());

        double weight = -1;

        try {

            weight = Math.ceil(Double.parseDouble(note.getWeight()));

        } catch (NumberFormatException e) {

            logger.writeLog("severe", "No se ha podido parsear el peso con valor: " + weight, e);
            AlertExceptionService weightAlert = new AlertExceptionService("Error", "Error en parseo de peso", e);

            weightAlert.show();
        }

        if (maxKilos > 0) {
            // ================ 2- SE COMPRUEBA QUE NO SOBREPASE EL MAXIMO DE KILOS ==============//
            if (weight != -1 && (agencyZone.getMaxKilos() == 0 || agencyZone.getMaxKilos() >= weight)) {
                if (maxKilos >= weight) {

                    result = rateDao.ratesNotesCompare(weight, note.getZone().getZoneId(), agencyZone.getAgencyId());

                } else {
                    if (agencyZone.getIncrease() > 0) { // SE APLICA INCREMENTO SI TIENE

                        result = rateDao.ratesNotesCompare(maxKilos, note.getZone().getZoneId(), agencyZone.getAgencyId());
                        double increment = (weight - maxKilos * agencyZone.getIncrease());
                        double newPrice = result.getPrice() + increment;
                        
                        logger.writeLog("info", "Aplicando incremento de precio...", null);
                        logger.writeLog("info", "Se ha aplicado un incremento por kilos en la agencia " + result.getAgencyName(), null);
                        logger.writeLog("info", "-Precio inicial: " + result.getPrice() + "€", null);
                        logger.writeLog("info", "-Incremento " + increment + "€", null);
                        logger.writeLog("info", "-Precio final: " + newPrice + "€", null);
                        
                        result.setPrice(result.getPrice() + increment);

                    } else {
                        logger.writeLog("warning", "No hay registrada tarifa para los kilos " + weight + " en la zona "
                                + note.getZone().getName() + " para la agencia" + agencyZone.getAgencyName() + ". Acción: Revise las tarifas de la zona", null);
                    }
                }
            }
        } else {
            logger.writeLog("warning", "No hay registrada tarifas para la agencia " + agencyZone.getAgencyName() + "en la zona "
                    + note.getZone().getName() + ". Acción: Revise las tarifas de la zona", null);
        }

        return result;
    }

    private ArrayList<RateComparator> checkBundles(ArrayList<RateComparator> list, Note note) {
        ArrayList<RateComparator> result = new ArrayList<>();

        if (list.size() > 0) {

            list.forEach(item -> {
                if (item.getBundles() >= Integer.parseInt(note.getBundles())) {
                    result.add(item);
                }
            });
        }
        if (result.size() == 0) {
            logger.writeLog("warning", "No se ha encontrado agencia. Acción: Revise los bultos", null);
            
            AlertService alert = new AlertService(Alert.AlertType.INFORMATION, "Información de Salida", "El albaran con REF: " + note.getRef() + " no se puede enviar por ninguna agencia", "Revise los bultos");
            alert.show();
        }
        return result;
    }

    private void checkUrgencyPercent(RateComparator first, ArrayList<RateComparator> list, Note note) {
        double urgencyPercent = configDao.getUrgencyPercent();

        double urgencyPrice = 0;

        urgencyPrice = first.getPrice() + (first.getPrice() * urgencyPercent / 100);

        if (urgencyPrice > 0) {

            if (first.getDeliveryTime() > 1) {
                for (int i = 1; i < list.size(); i++) {
                    if (list.get(i).getDeliveryTime() < first.getDeliveryTime()
                            && list.get(i).getPrice() - first.getPrice() < urgencyPrice) {

                        note.setBEST_AGENCY(list.get(i).getAgencyName());
                        
                        logger.writeLog("info", "Aplicando porcentaje de urgencia...", null);
                        logger.writeLog("info", "Se ha aplicado el porcentaje de urgencia:", null);
                        logger.writeLog("info", "-Mejor precio: " + first.getPrice() + "€", null);
                        logger.writeLog("info", "-Precio elegido: " + list.get(i).getPrice() + "€", null);
                        
                        break;
                    }
                }
            }
        }
    }

    private ArrayList<RateComparator> checkPrice(ArrayList<RateComparator> list, Note note) {
        
        logger.writeLog("info", "Comprobando precio...", null);
        
        list.forEach(item -> {

            if (item.getSurchargeFuel() > 0) { //Check surcharge fuel

                double percent = item.getSurchargeFuel() * item.getPrice() / 100;
                double newPrice = item.getPrice() + percent;
              
                logger.writeLog("info", "Aplicando incremento...", null);
                logger.writeLog("info", "Se ha aplicado un incremento por RECARGO DE COMBUSTIBLE a la agencia " + item.getAgencyName() , null);
                logger.writeLog("info", "-Precio inicial: " + item.getPrice() + "€", null);
                logger.writeLog("info", "-Incremento: " + percent, null);
                logger.writeLog("info", "-Nuevo precio: " + newPrice+ "€", null);
               
                item.setPrice(newPrice);

            }

            if (!note.getRefund().isEmpty()) {// Check refund and Comision

                double minRefundPrice = 0;

                double comisionPrice = 0;

                if (item.getMinimumRefund() > 0) {
                    minRefundPrice = item.getPrice() + item.getMinimumRefund();

                }
                double percent = 0;
                if (item.getComision() > 0) {

                    percent = item.getPrice() * item.getComision() / 100;

                    comisionPrice = item.getPrice() + percent;

                }

                if (comisionPrice > 0 && comisionPrice >= minRefundPrice) {
                    
                    logger.writeLog("info", "Aplicando incremento...", null);
                    logger.writeLog("info", "Se ha aplicado un incremento por COMISIÖN a la agencia " + item.getAgencyName(), null);
                    logger.writeLog("info", "-Precio inicial: " + item.getPrice() + "€", null);
                    logger.writeLog("info", "-Incremento: " + percent +"€", null);
                    logger.writeLog("info", "-Nuevo precio: " + comisionPrice + "€", null);
                    
                    item.setPrice(comisionPrice);
                }

                if (minRefundPrice > 0 && minRefundPrice > comisionPrice) {
                  
                    logger.writeLog("info", "Se ha aplicado un incremento por MINIMO REEMBOLSO a la agencia " + item.getAgencyName(), null);
                    logger.writeLog("info", "-Precio inicial: " + item.getPrice() + "€", null);
                    logger.writeLog("info", "-Incremento: " + item.getMinimumRefund() + "€", null);
                    logger.writeLog("info", "-Nuevo precio: " + minRefundPrice + "€", null);
                    
                    item.setPrice(minRefundPrice);
                }

            }

        });

        return list;
    }

    private void checkBigShipment(ArrayList<RateComparator> list, Note note) {
        Iterator<RateComparator> i = list.iterator();
        while (i.hasNext()) {
            RateComparator item = i.next();
            if (!item.isBigShipment()) {
                i.remove();
            }
        }
        if (list.size() == 0) {
          
            logger.writeLog("warning", "No se puede enviar por ninguna agencia. Acción: Revise los las agencias con envío voluminoso", null);
            
            AlertService alert = new AlertService(Alert.AlertType.INFORMATION, "Información de Salida", "El albaran con REF: " + note.getRef() + " no se puede enviar por ninguna agencia", "Revise las"
                    + " agencias con envío voluminoso");
            alert.show();
        }
    }

}
