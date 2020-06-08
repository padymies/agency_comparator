package tdt.services;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import tdt.db.dao.IAgenciaDao;
import tdt.db.dao.IExclusionesDao;
import tdt.db.dao.ITarifaDao;
import tdt.db.dao.IZonaDao;
import tdt.db.daoImpl.AgenciaImpl;
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

    public ComparatorService() {
        tarifaDao = new TarifaImpl();

        zonaDao = new ZonaImpl();

        agenciasDao = new AgenciaImpl();
    }

    public void compararAlbaranes(ArrayList<Albaran> albaranes) {

        for (Albaran albaran : albaranes) {

            // ================= 1- SE COMPRUEBA QUE NO ESTÉ FORZADA LA AGENCIA ====================//
            if (albaran.getMEJOR_AGENCIA() == null) {

                ObservableList<AgenciaZona> listaAgencias = tarifaDao.obtenerAgenciasPorZona(albaran.getZona().getIdZona());

                ArrayList<ComparadorTarifa> resultadoList = new ArrayList<>();

                for (AgenciaZona agenciaZona : listaAgencias) {

                    int maxKilos = tarifaDao.obtenerMaxKilo(agenciaZona.getIdAgencia(), agenciaZona.getIdZona());

                    double peso = Math.ceil(Double.parseDouble(albaran.getPeso()));

                    ComparadorTarifa result = null;

                    // ================ 2- SE COMPRUEBA QUE NO SOBREPASE EL MAXIMO DE KILOS ==============//
                    if (maxKilos >= peso && agenciaZona.getMaxKilos() <= peso) {

                        result = tarifaDao.compararTarifasAlbaran(peso, albaran.getZona().getIdZona(), agenciaZona.getIdAgencia());

                    } else {
                        if (agenciaZona.getIncremento() > 0) { // SE APLICA INCREMENTO SI TIENE

                            result = tarifaDao.compararTarifasAlbaran(maxKilos, albaran.getZona().getIdZona(), agenciaZona.getIdAgencia());

                            System.out.println("INCREMENTO => " + (peso - maxKilos * agenciaZona.getIncremento()));

                            result.setPrecio(result.getPrecio() + (peso - maxKilos * agenciaZona.getIncremento()));

                            System.out.println("Precio: " + result.getPrecio());

                        } else { // NO SE AÑADE A LA LISTA DE RESULTADOS PORQUE NO SE PUEDE ENVIAR POR ESA

                        }
                    }

                    // ================ 3-COMPROBAMOS EL RESTO DE VARIABLES PARA DETERMINAR EL PRECIO FINAL ================//
                    if (result != null) {

                        resultadoList.add(result);

                    }

                }

                System.out.println("Albaran " + albaran.getRef() + "resultado: " + resultadoList);

            }

        }

    }

    private void buscarMejoresAgencias(Albaran albaran, ArrayList<ComparadorTarifa> list) {

        exclusionesHandler(albaran, list);

        list.forEach(item -> {

            double peso = Math.ceil(Double.parseDouble(albaran.getPeso()));

            if (item.getMaxKilos() <= peso) {

            } else {

            }

        });
    }

    private void exclusionesHandler(Albaran albaran, ArrayList<ComparadorTarifa> list) {

        exclusionesDao = new ExclusionesImpl();

        Exclusion ex = exclusionesDao.obtenerExclusionPorCP(albaran.getPostalDestino());

        if (ex != null) {
            if (ex.getInclusion_exclusion() == 1) {
                // Se tiene que mandar por esta agencia
                agenciasDao = new AgenciaImpl();

                Agencia agencia = agenciasDao.obtenerAgencia(ex.getId_agencia());

                albaran.setMEJOR_AGENCIA(agencia.getNombre());

            } else if (ex.getInclusion_exclusion() == -1) {
                // Se excluye esta agencia
                list.forEach(item -> {

                    if (item.getIdAgencia() == ex.getId_agencia()) {

                        list.remove(item);

                    }
                });

            } else {
                System.out.println("No se puede enviar por ninguna agencia => " + ex.getInclusion_exclusion());
            }
        }
    }

    private boolean checkBultos(AgenciaZona agenciaZona, Albaran al) {

        int bultos = -1;

        try {

            bultos = Integer.parseInt(al.getBultos());

        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        if (bultos != -1 && bultos <= agenciaZona.getBultos()) {
            return true;
        }
        return false;
    }
    
    
    private boolean checkEnvioGrande(AgenciaZona agenciaZona, Albaran al) {
        
        
        return false;
    }
}
