
package tdt.db.dao;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import tdt.model.AgenciaZona;
import tdt.model.ComparadorTarifa;
import tdt.model.Tarifa;


public interface ITarifaDao {

    public ObservableList<Tarifa> obtenerTarifasUI(int idAgencia, int idZona);
    
    public ArrayList<Tarifa> obtenerTarifasPorKilos(int idZona, int kg);

    public Tarifa obtenerTarifa(int idAgencia, int idZona, int kg);
    
    public ObservableList<Tarifa> obtenerTarifasPorZonaAgencia(int idZona, int idAgencia);

    public boolean añadirTarifa(Tarifa tarifa);

    public boolean actualizarTarifa(Tarifa tarifa);
    
    public ComparadorTarifa compararTarifasAlbaran(double peso, int idZona, int idAgencia);
    
    public ObservableList<AgenciaZona> obtenerAgenciasPorZona(int IdZona);
    
    public ObservableList<String> obtenerNombresAgenciasPorZona(String nombreZona);
    
    public boolean añadirAgenciaZona(int idAgencia, int idZona,double incremento, int plazoEntrega, int maxKilos);
    
    public boolean actualizarAgenciaZona(int idAgencia, int idZona,double incremento, int plazoEntrega, int maxKilos);
    
    public boolean borrarAgenciaZona(int idAgencia, int idZona);
    
    public int obtenerMaxKilo(int idAgencia, int idZona);
    
    public ObservableList<Tarifa> copiarTarifa(String nombreAgencia, String nombreZona);
    
    public boolean pegarTarifa(int idZona, int idAgencia, ObservableList<Tarifa> values);
    
    public boolean borrarTarifasDeAgencia(int idZona, int idAgencia);
}
