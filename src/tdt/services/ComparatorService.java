package tdt.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.ObservableList;
import tdt.db.dao.IAppConfig;
import tdt.db.daoImpl.AgencyImpl;
import tdt.db.daoImpl.AppConfigImpl;
import tdt.db.daoImpl.ExclusionesImpl;
import tdt.db.daoImpl.TarifaImpl;
import tdt.db.daoImpl.ZoneImpl;
import tdt.model.Agency;
import tdt.model.AgencyZone;
import tdt.model.Note;
import tdt.model.RateComparator;
import tdt.model.Exclusion;
import tdt.db.dao.IAgencyDao;
import tdt.db.dao.IExclusionDao;
import tdt.db.dao.IRateDao;
import tdt.db.dao.IZoneDao;

public class ComparatorService {

    private IExclusionDao exclusionesDao;

    private IAgencyDao agenciasDao;

    private IRateDao tarifaDao;

    private IZoneDao zonaDao;

    private IAppConfig configDao;

    public ComparatorService() {
        tarifaDao = new TarifaImpl();

        zonaDao = new ZoneImpl();

        agenciasDao = new AgencyImpl();

        configDao = new AppConfigImpl();
    }

    public void compararAlbaranes(ArrayList<Note> albaranes) {

        for (Note albaran : albaranes) {

            // ================= 1- SE COMPRUEBA QUE NO ESTÉ FORZADA LA AGENCIA ====================//
            if (albaran.getBEST_AGENCY() == null) {

                ObservableList<AgencyZone> listaAgencias = tarifaDao.getAgenciesByZone(albaran.getZone().getZoneId());

                checkExclusiones(albaran, listaAgencias);

                ArrayList<RateComparator> resultadoList = new ArrayList<>();

                if (listaAgencias.size() > 0) { // Prueba 
                    for (AgencyZone agenciaZona : listaAgencias) {

                        // Kilo mayor de la tarifa de la agencia_zona
                        int maxKilos = tarifaDao.getMaxKilo(agenciaZona.getAgencyId(), agenciaZona.getZoneId());

                        double peso = -1;
                        try {

                            peso = Math.ceil(Double.parseDouble(albaran.getWeight()));
                        } catch (NumberFormatException e) {
                            AlertExceptionService pesoAlert = new AlertExceptionService("Error", "Error en parseo de peso", e);
                            pesoAlert.showAndWait();
                        }

                        RateComparator result = null;

                        if (maxKilos > 0) {
                            // ================ 2- SE COMPRUEBA QUE NO SOBREPASE EL MAXIMO DE KILOS ==============//
                            if (peso != -1 && (agenciaZona.getMaxKilos() == 0 || agenciaZona.getMaxKilos() >= peso)) {
                                if (maxKilos >= peso) {

                                    result = tarifaDao.ratesNotesCompare(peso, albaran.getZone().getZoneId(), agenciaZona.getAgencyId());

                                } else {
                                    if (agenciaZona.getIncrease() > 0) { // SE APLICA INCREMENTO SI TIENE

                                        result = tarifaDao.ratesNotesCompare(maxKilos, albaran.getZone().getZoneId(), agenciaZona.getAgencyId());

                                        result.setPrice(result.getPrice() + (peso - maxKilos * agenciaZona.getIncrease()));

                                    }
                                }
                            }
                        }

                        if (result != null) {

                            resultadoList.add(result);

                        }

                    }
                }
                // ================ 3-COMPROBAMOS EL RESTO DE VARIABLES PARA DETERMINAR EL PRECIO FINAL ================//

                if (resultadoList.size() > 0) {

                    resultadoList.forEach(item -> {

                        if (item.getSurchargeFuel() > 0) {

                            double porcentaje = item.getSurchargeFuel() * item.getPrice() / 100;

                            item.setPrice(item.getPrice() + porcentaje);
                        }

                        if (albaran.getRefund() != null) {

                            double precioReembolso = 0;

                            double precioComision = 0;

                            if (item.getMinimumRefund() > 0) {
                                precioReembolso = item.getPrice() + item.getMinimumRefund();

                            }

                            if (item.getComision() > 0) {

                                double porcentaje = item.getPrice() * item.getComision() / 100;

                                precioComision = item.getPrice() + porcentaje;
                            }

                            if (precioComision > 0 && precioComision >= precioReembolso) {
                                item.setPrice(precioComision);
                            }

                            if (precioReembolso > 0 && precioReembolso > precioComision) {
                                item.setPrice(precioReembolso);

                            }

                        }

                    });

                    Collections.sort(resultadoList, Comparator.comparing(item -> item.getPrice()));

                    /*        resultadoList.forEach(data -> {
                        System.out.println("Agency: " + data.getAgencyName() + " Precio: " + data.getPrice() + " Entrega: " + data.getDeliveryTime() + "\n");

                    });*/
                    RateComparator first = resultadoList.get(0);

                    double porcentajeUrgencia = configDao.getUrgencyPercent();

                    double precioUrgencia = 0;

                    precioUrgencia = first.getPrice() + (first.getPrice() * porcentajeUrgencia / 100);

                    if (precioUrgencia > 0) {

                        if (first.getDeliveryTime() > 1) {
                            for (int i = 1; i < resultadoList.size(); i++) {
                                if (resultadoList.get(i).getDeliveryTime() < first.getDeliveryTime()
                                        && resultadoList.get(i).getPrice() - first.getPrice() < precioUrgencia) {
                                    albaran.setBEST_AGENCY(resultadoList.get(i).getAgencyName());
                                    break;
                                }
                            }
                        }
                    }

                    if (albaran.getBEST_AGENCY() == null) {
                        albaran.setBEST_AGENCY(first.getAgencyName());
                    }

                }

            }

        }

    }

    private void checkExclusiones(Note albaran, ObservableList<AgencyZone> list) {

        exclusionesDao = new ExclusionesImpl();

        Exclusion ex = exclusionesDao.getExclusionByPostalCode(albaran.getDestinationPostalCode());

        if (ex != null) {
            switch (ex.getInclusion_exclusion()) {
                case 1:
                    // Se tiene que mandar por esta agencia
                    agenciasDao = new AgencyImpl();

                    Agency agencia = agenciasDao.getAgency(ex.getAgencyId());

                    albaran.setBEST_AGENCY(agencia.getName());

                    break;
                case -1:
                    // Se excluye esta agencia
                    list.forEach(item -> {

                        if (item.getAgencyId() == ex.getAgencyId()) {

                            list.remove(item);

                        }
                    });
                    break;
                default:
                    System.out.println("No se puede enviar por ninguna agencia => " + ex.getInclusion_exclusion());
                    break;
            }
        }
    }

}
