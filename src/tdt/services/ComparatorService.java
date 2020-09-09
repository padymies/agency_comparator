package tdt.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.ObservableList;
import tdt.db.dao.IAgenciaDao;
import tdt.db.dao.IAppConfig;
import tdt.db.dao.IExclusionesDao;
import tdt.db.dao.ITarifaDao;
import tdt.db.dao.IZonaDao;
import tdt.db.daoImpl.AgenciaImpl;
import tdt.db.daoImpl.AppConfigImpl;
import tdt.db.daoImpl.ExclusionesImpl;
import tdt.db.daoImpl.TarifaImpl;
import tdt.db.daoImpl.ZonaImpl;
import tdt.model.Agencia;
import tdt.model.AgenciaZona;
import tdt.model.Albaran;
import tdt.model.ComparadorTarifa;
import tdt.model.Exclusion;

public class ComparatorService {

    private IExclusionesDao exclusionesDao;

    private IAgenciaDao agenciasDao;

    private ITarifaDao tarifaDao;

    private IZonaDao zonaDao;

    private IAppConfig configDao;

    public ComparatorService() {
        tarifaDao = new TarifaImpl();

        zonaDao = new ZonaImpl();

        agenciasDao = new AgenciaImpl();

        configDao = new AppConfigImpl();
    }

    public void compararAlbaranes(ArrayList<Albaran> albaranes) {

        for (Albaran albaran : albaranes) {

            // ================= 1- SE COMPRUEBA QUE NO ESTÉ FORZADA LA AGENCIA ====================//
            if (albaran.getMEJOR_AGENCIA() == null) {

                ObservableList<AgenciaZona> listaAgencias = tarifaDao.obtenerAgenciasPorZona(albaran.getZona().getIdZona());

                checkExclusiones(albaran, listaAgencias);

                ArrayList<ComparadorTarifa> resultadoList = new ArrayList<>();

                if (listaAgencias.size() > 0) { // Prueba 
                    for (AgenciaZona agenciaZona : listaAgencias) {

                        // Kilo mayor de la tarifa de la agencia_zona
                        int maxKilos = tarifaDao.obtenerMaxKilo(agenciaZona.getIdAgencia(), agenciaZona.getIdZona());

                        double peso = -1;
                        try {

                            peso = Math.ceil(Double.parseDouble(albaran.getPeso()));
                        } catch (NumberFormatException e) {
                            AlertExceptionService pesoAlert = new AlertExceptionService("Error", "Error en parseo de peso", e);
                            pesoAlert.showAndWait();
                        }

                        ComparadorTarifa result = null;

                        if (maxKilos > 0) {
                            // ================ 2- SE COMPRUEBA QUE NO SOBREPASE EL MAXIMO DE KILOS ==============//
                            if (peso != -1 && (agenciaZona.getMaxKilos() == 0 || agenciaZona.getMaxKilos() >= peso)) {
                                if (maxKilos >= peso) {

                                    result = tarifaDao.compararTarifasAlbaran(peso, albaran.getZona().getIdZona(), agenciaZona.getIdAgencia());

                                } else {
                                    if (agenciaZona.getIncremento() > 0) { // SE APLICA INCREMENTO SI TIENE

                                        result = tarifaDao.compararTarifasAlbaran(maxKilos, albaran.getZona().getIdZona(), agenciaZona.getIdAgencia());

                                        result.setPrecio(result.getPrecio() + (peso - maxKilos * agenciaZona.getIncremento()));

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

                        if (item.getRecargoCombustible() > 0) {

                            double porcentaje = item.getRecargoCombustible() * item.getPrecio() / 100;

                            item.setPrecio(item.getPrecio() + porcentaje);
                        }

                        if (albaran.getReembolso() != null) {

                            double precioReembolso = 0;

                            double precioComision = 0;

                            if (item.getMinimoReembolso() > 0) {
                                precioReembolso = item.getPrecio() + item.getMinimoReembolso();

                            }

                            if (item.getComision() > 0) {

                                double porcentaje = item.getPrecio() * item.getComision() / 100;

                                precioComision = item.getPrecio() + porcentaje;
                            }

                            if (precioComision > 0 && precioComision >= precioReembolso) {
                                item.setPrecio(precioComision);
                            }

                            if (precioReembolso > 0 && precioReembolso > precioComision) {
                                item.setPrecio(precioReembolso);

                            }

                        }

                    });

                    Collections.sort(resultadoList, Comparator.comparing(item -> item.getPrecio()));

                    /*        resultadoList.forEach(data -> {
                        System.out.println("Agencia: " + data.getNombreAgencia() + " Precio: " + data.getPrecio() + " Entrega: " + data.getPlazoEntrega() + "\n");

                    });*/
                    ComparadorTarifa first = resultadoList.get(0);

                    double porcentajeUrgencia = configDao.getPorcentajeUrgencia();

                    double precioUrgencia = 0;

                    precioUrgencia = first.getPrecio() + (first.getPrecio() * porcentajeUrgencia / 100);

                    if (precioUrgencia > 0) {

                        if (first.getPlazoEntrega() > 1) {
                            for (int i = 1; i < resultadoList.size(); i++) {
                                if (resultadoList.get(i).getPlazoEntrega() < first.getPlazoEntrega()
                                        && resultadoList.get(i).getPrecio() - first.getPrecio() < precioUrgencia) {
                                    albaran.setMEJOR_AGENCIA(resultadoList.get(i).getNombreAgencia());
                                    break;
                                }
                            }
                        }
                    }

                    if (albaran.getMEJOR_AGENCIA() == null) {
                        albaran.setMEJOR_AGENCIA(first.getNombreAgencia());
                    }

                }

            }

        }

    }

    private void checkExclusiones(Albaran albaran, ObservableList<AgenciaZona> list) {

        exclusionesDao = new ExclusionesImpl();

        Exclusion ex = exclusionesDao.obtenerExclusionPorCP(albaran.getPostalDestino());

        if (ex != null) {
            switch (ex.getInclusion_exclusion()) {
                case 1:
                    // Se tiene que mandar por esta agencia
                    agenciasDao = new AgenciaImpl();

                    Agencia agencia = agenciasDao.obtenerAgencia(ex.getId_agencia());

                    albaran.setMEJOR_AGENCIA(agencia.getNombre());

                    break;
                case -1:
                    // Se excluye esta agencia
                    list.forEach(item -> {

                        if (item.getIdAgencia() == ex.getId_agencia()) {

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
