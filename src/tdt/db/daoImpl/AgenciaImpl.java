package tdt.db.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tdt.db.DBConnection;
import tdt.db.dao.IAgenciaDao;
import tdt.model.Agencia;
import tdt.services.AlertExceptionService;

public class AgenciaImpl implements IAgenciaDao {

    private final String TABLE_NAME = "AGENCIAS";

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

                // System.out.println("OBTENIENDO AGENCIAS ------------>" + sql);
                while (result.next()) {

                    int id = result.getInt("id_agencia");

                    String nombre = result.getString("nombre");

                    int bultos = result.getInt("bultos");

                    double recargo_combustible = result.getDouble("recargo_combustible");

                    double minimo_reembolso = result.getDouble("minimo_reembolso");

                    double comision = result.getDouble("comision");

                    boolean envio_grande = result.getBoolean("envio_grande");

                    list.add(new Agencia(id, nombre, bultos, recargo_combustible, minimo_reembolso, envio_grande, comision));
                }
            }

        } catch (SQLException ex) {

            // System.out.println("Error recuperando Agencias");
            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se han podido obtener las agencias", ex);

            alert.showAndWait();

        } finally {

            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(AgenciaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public Agencia obtenerAgencia(int agenciaId) {

        Connection conn = null;

        Statement stat = null;

        Agencia agencia = null;

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE `id_agencia`=" + agenciaId;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("Recuperando agencia----------> " + sql);
                result.next();

                int id = result.getInt("id_agencia");

                String nombre = result.getString("nombre");

                int bultos = result.getInt("bultos");

                double recargo_combustible = result.getDouble("recargo_combustible");

                double minimo_reembolso = result.getDouble("minimo_reembolso");

                double comision = result.getDouble("comision");

                boolean envio_grande = result.getBoolean("envio_grande");

                agencia = new Agencia(id, nombre, bultos, recargo_combustible, minimo_reembolso, envio_grande, comision);
            }

            // System.out.println("Agencia recuperada !!");
        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido obtener la agencia", ex);

            alert.showAndWait();

        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(AgenciaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return agencia;
    }

    @Override
    public int añadirAgencia(Agencia agencia) {

        Connection conn = null;

        Statement stat = null;

        int id = -1;

        String sql = "INSERT INTO " + TABLE_NAME + " (nombre, bultos, recargo_combustible, minimo_reembolso, envio_grande, comision) VALUES('"
                + agencia.getNombre() + "', " + agencia.getBultos() + ", " + agencia.getRecargo_combustible()
                + ", " + agencia.getMinimo_reembolso() + ", " + agencia.isEnvio_grande() + ", " + agencia.getComision() + ")";
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                // System.out.println("Insertando agencia -----------> " + sql);
                stat.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                ResultSet result = stat.getGeneratedKeys();

                if (result.next()) {

                    id = result.getInt(1);

                } else {
                    // System.out.println("Error de inserción");
                }

                // System.out.println("Agencia insertada !!");
            }
        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido añadir la agencia", ex);

            alert.showAndWait();

        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(AgenciaImpl.class.getName()).log(Level.SEVERE, null, ex);
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
                + "`name`='" + agencia.getNombre() + "', `bultos`=" + agencia.getBultos()
                + ", `recargo_combustible`=" + agencia.getRecargo_combustible()
                + ", `minimo_reembolso`=" + agencia.getMinimo_reembolso()
                + ", `envio_grande`=" + agencia.isEnvio_grande()
                + ", `comision`=" + agencia.getComision()
                + "', WHERE `id_agencia`=" + agencia.getId_agencia();
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                // System.out.println("Actualizando agencia ---------------> " + sql);
                stat.executeUpdate(sql);

                // System.out.println("Agencia actualizada !!");
                result = true;
            }
        } catch (SQLException ex) {

               AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido actualizar la agencia", ex);

            alert.showAndWait();

        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(AgenciaImpl.class
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

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE `id_agencia`=" + agenciaId;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.execute(sql);

                // System.out.println("Eliminando agencia-----------------> " + sql);
                result = true;

                // System.out.println("Agencia eliminada !!");
            }
        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido borrar la agencia", ex);

            alert.showAndWait();

        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(AgenciaImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public ObservableList<String> obtenerNombresAgencias() {
        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT nombre FROM " + TABLE_NAME;

        ObservableList<String> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO NOMBRE DE AGENCIAS ------------>" + sql);
                while (result.next()) {

                    String nombre = result.getString("nombre");

                    list.add(nombre);
                }
            }

        } catch (SQLException ex) {

                AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se han podido obtener los nombres de agencias", ex);

            alert.showAndWait();

        } finally {

            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(AgenciaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

}
