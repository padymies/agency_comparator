package tdt.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.ObservableList;
import tdt.db.dao.IAppConfig;
import tdt.db.dao.IExclusionesDao;
import tdt.db.dao.ITarifaDao;
import tdt.db.dao.IZonaDao;
import tdt.db.daoImpl.AgencyImpl;
import tdt.db.daoImpl.AppConfigImpl;
import tdt.db.daoImpl.ExclusionesImpl;
import tdt.db.daoImpl.TarifaImpl;
import tdt.db.daoImpl.ZonaImpl;
import tdt.model.Agency;
import tdt.model.AgencyZone;
import tdt.model.Albaran;
import tdt.model.RateComparator;
import tdt.model.Exclusion;
import tdt.db.dao.IAgencyDao;

public class ComparatorService {

    private IExclusionesDao exclusionesDao;

    private IAgencyDao agenciasDao;

    private ITarifaDao tarifaDao;

    private IZonaDao zonaDao;

    private IAppConfig configDao;

    public ComparatorService() {
        tarifaDao = new TarifaImpl();

        zonaDao = new ZonaImpl();

        agenciasDao = new AgencyImpl();

        configDao = new AppConfigImpl();
    }

    public void compararAlbaranes(ArrayList<Albaran> albaranes) {

        for (Albaran albaran : albaranes) {

            // ================= 1- SE COMPRUEBA QUE NO ESTÉ FORZADA LA AGENCIA ====================//
            if (albaran.getMEJOR_AGENCIA() == null) {

                ObservableList<AgencyZone> listaAgencias = tarifaDao.obtenerAgenciasPorZona(albaran.getZona().getZoneId());

                checkExclusiones(albaran, listaAgencias);

                ArrayList<RateComparator> resultadoList = new ArrayList<>();

                if (listaAgencias.size() > 0) { // Prueba 
                    for (AgencyZone agenciaZona : listaAgencias) {

                        // Kilo mayor de la tarifa de la agencia_zona
                        int maxKilos = tarifaDao.obtenerMaxKilo(agenciaZona.getAgencyId(), agenciaZona.getZoneId());

                        double peso = -1;
                        try {

                            peso = Math.ceil(Double.parseDouble(albaran.getPeso()));
                        } catch (NumberFormatException e) {
                            AlertExceptionService pesoAlert = new AlertExceptionService("Error", "Error en parseo de peso", e);
                            pesoAlert.showAndWait();
                        }

                        RateComparator result = null;

                        if (maxKilos > 0) {
                            // ================ 2- SE COMPRUEBA QUE NO SOBREPASE EL MAXIMO DE KILOS ==============//
                            if (peso != -1 && (agenciaZona.getMaxKilos() == 0 || agenciaZona.getMaxKilos() >= peso)) {
                                if (maxKilos >= peso) {

                                    result = tarifaDao.compararTarifasAlbaran(peso, albaran.getZona().getZoneId(), agenciaZona.getAgencyId());

                                } else {
                                    if (agenciaZona.getIncrease() > 0) { // SE APLICA INCREMENTO SI TIENE

                                        result = tarifaDao.compararTarifasAlbaran(maxKilos, albaran.getZona().getZoneId(), agenciaZona.getAgencyId());

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

                        if (albaran.getReembolso() != null) {

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

                    double porcentajeUrgencia = configDao.getPorcentajeUrgencia();

                    double precioUrgencia = 0;

                    precioUrgencia = first.getPrice() + (first.getPrice() * porcentajeUrgencia / 100);

                    if (precioUrgencia > 0) {

                        if (first.getDeliveryTime() > 1) {
                            for (int i = 1; i < resultadoList.size(); i++) {
                                if (resultadoList.get(i).getDeliveryTime() < first.getDeliveryTime()
                                        && resultadoList.get(i).getPrice() - first.getPrice() < precioUrgencia) {
                                    albaran.setMEJOR_AGENCIA(resultadoList.get(i).getAgencyName());
                                    break;
                                }
                            }
                        }
                    }

                    if (albaran.getMEJOR_AGENCIA() == null) {
                        albaran.setMEJOR_AGENCIA(first.getAgencyName());
                    }

                }

            }

        }

    }

    private void checkExclusiones(Albaran albaran, ObservableList<AgencyZone> list) {

        exclusionesDao = new ExclusionesImpl();

        Exclusion ex = exclusionesDao.obtenerExclusionPorCP(albaran.getPostalDestino());

        if (ex != null) {
            switch (ex.getInclusion_exclusion()) {
                case 1:
                    // Se tiene que mandar por esta agencia
                    agenciasDao = new AgencyImpl();

                    Agency agencia = agenciasDao.getAgency(ex.getAgencyId());

                    albaran.setMEJOR_AGENCIA(agencia.getName());

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
