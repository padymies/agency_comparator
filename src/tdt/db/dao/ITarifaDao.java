
package tdt.db.dao;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import tdt.model.AgencyZone;
import tdt.model.RateComparator;
import tdt.model.Rate;


public interface ITarifaDao {

    public ObservableList<Rate> obtenerTarifasUI(int idAgencia, int idZona);
    
    public ArrayList<Rate> obtenerTarifasPorKilos(int idZona, int kg);

    public Rate obtenerTarifa(int idAgencia, int idZona, int kg);
    
    public ObservableList<Rate> obtenerTarifasPorZonaAgencia(int idZona, int idAgencia);

    public boolean añadirTarifa(Rate tarifa);

    public boolean actualizarTarifa(Rate tarifa);
    
    public RateComparator compararTarifasAlbaran(double peso, int idZona, int idAgencia);
    
    public ObservableList<AgencyZone> obtenerAgenciasPorZona(int IdZona);
    
    public ObservableList<String> obtenerNombresAgenciasPorZona(String nombreZona);
    
    public boolean añadirAgenciaZona(int idAgencia, int idZona,double incremento, int plazoEntrega, int maxKilos);
    
    public boolean actualizarAgenciaZona(int idAgencia, int idZona,double incremento, int plazoEntrega, int maxKilos);
    
    public boolean borrarAgenciaZona(int idAgencia, int idZona);
    
    public int obtenerMaxKilo(int idAgencia, int idZona);
    
    public ObservableList<Rate> copiarTarifa(String nombreAgencia, String nombreZona);
    
    public boolean pegarTarifa(int idZona, int idAgencia, ObservableList<Rate> values);
    
    public boolean borrarTarifasDeAgencia(int idZona, int idAgencia);
    
    public boolean borrarTarifa(Rate tar);
}
