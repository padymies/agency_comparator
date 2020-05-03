package tdt.db.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import tdt.db.DBConnection;
import tdt.db.dao.IAgenciaDao;
import tdt.model.Agencia;
import tdt.services.MyLogger;
import tdt.services.MyLogger.Severity;

public class AgenciaImpl implements IAgenciaDao {

    private final String TABLE_NAME = "AGENCIAS";

    private TextArea log;

    @Override
    public ObservableList<Agencia> obtenerAgencias() {

        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT * FROM " + TABLE_NAME;

        ObservableList<Agencia> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                System.out.println("Recueprando Agencias ------------>" + sql);

                while (result.next()) {

                    int id = result.getInt("id_agencia");

                    String nombre = result.getString("nombre");

                    int plazo_entrega = result.getInt("plazo_entrega");

                    int bultos = result.getInt("bultos");

                    double recargo_combustible = result.getDouble("recargo_combustible");

                    double minimo_reembolso = result.getDouble("minimo_reembolso");

                    boolean envio_grande = result.getBoolean("envio_grande");

                    list.add(new Agencia(id, nombre, plazo_entrega, bultos, recargo_combustible, minimo_reembolso, envio_grande));
                }
            }

            MyLogger.writeLog(
                    "Conectando a la base de datos...", Severity.INFO);
            MyLogger.writeLog(
                    "Conexión establecida", Severity.SUCCESS);
            MyLogger.writeLog(
                    "Error al cargar albarán nº 14402", Severity.WARNING);
            MyLogger.writeLog(
                    "Error al cargar albarán nº 14402", Severity.ERROR);
            System.out.println(
                    "Agencias recuperadas !!" + list);

        } catch (SQLException ex) {

            System.out.println("Error recuperando Agencias");

            Logger.getLogger(AgenciaImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(VariableArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public Agencia obtenerAgencia(int agenciaId) {

        Connection conn = null;

        Statement stat = null;

        Agencia agencia = null;

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id_agencia=" + agenciaId;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                System.out.println("Recuperando agencia----------> " + sql);

                result.next();

                int id = result.getInt("id_agencia");

                String nombre = result.getString("nombre");

                int plazo_entrega = result.getInt("plazo_entrega");

                int bultos = result.getInt("bultos");

                double recargo_combustible = result.getDouble("recargo_combustible");

                double minimo_reembolso = result.getDouble("minimo_reembolso");

                boolean envio_grande = result.getBoolean("envio_grande");

                agencia = new Agencia(id, nombre, plazo_entrega, bultos, recargo_combustible, minimo_reembolso, envio_grande);
            }

            MyLogger.writeLog("PADY", Severity.INFO);

            System.out.println("Agencia recuperada !!");
        } catch (SQLException ex) {

            System.out.println("Error recuperando agencia");

            Logger.getLogger(IAgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(VariableArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return agencia;
    }

    @Override
    public int añadirAgencia(Agencia agencia) {

        Connection conn = null;

        Statement stat = null;

        int id = -1;

        String sql = "INSERT INTO " + TABLE_NAME + " (nombre, plazo_entrega, bultos, recargo_combustible, minimo_reembolso, envio_grande) VALUES('"
                + agencia.getNombre() + "', " + agencia.getPlazo_entrega() + ", " + agencia.getBultos() + ", " + agencia.getRecargo_combustible()
                + ", " + agencia.getMinimo_reembolso() + ", " + agencia.isEnvio_grande() + ")";
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                System.out.println("Insertando agencia -----------> " + sql);

                stat.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                ResultSet result = stat.getGeneratedKeys();

                result.next();

                id = result.getInt("id_agencia");

                System.out.println("Agencia insertada !!");
            }
        } catch (SQLException ex) {

            System.out.println("Error insertando agencia");

            Logger.getLogger(IAgenciaDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(VariableArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public boolean actualizarAgencia(Agencia agencia) {

        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "UPDATE " + TABLE_NAME + "  SET "
                + "name='" + agencia.getNombre() + "', plazo_entrega=" + agencia.getPlazo_entrega()
                + ", bultos=" + agencia.getBultos() + ", recargo_combustible=" + agencia.getRecargo_combustible()
                + ", minimo_reembolso=" + agencia.getMinimo_reembolso()
                + ", envio_grande=" + agencia.isEnvio_grande()
                + "', WHERE id_agencia=" + agencia.getId_agencia();
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                System.out.println("Actualizando agencia ---------------> " + sql);

                stat.executeUpdate(sql);

                System.out.println("Agencia actualizada !!");

                result = true;
            }
        } catch (SQLException ex) {

            System.out.println("Error actualizando agencia");

            Logger.getLogger(IAgenciaDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(VariableArchivoImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public boolean borrarAgencia(int agenciaId) {
        
        Connection conn = null;
        
        Statement stat = null;
        
        boolean result = false;
        
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id_agencia=" + agenciaId;
        
        try {
        
            conn = DBConnection.getConnection();
            
            if (conn != null) {
            
                stat = conn.createStatement();
                
                stat.execute(sql);
                
                System.out.println("Eliminando agencia-----------------> " + sql);
                
                result = true;
                
                System.out.println("Agencia eliminada !!");
            }
        } catch (SQLException ex) {
            
            System.out.println("Error borrando agencia");
            
            Logger.getLogger(AgenciaImpl.class.getName()).log(Level.SEVERE, null, ex);
        
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(VariableArchivoImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

}
