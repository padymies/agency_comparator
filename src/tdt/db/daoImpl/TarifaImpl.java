package tdt.db.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tdt.db.DBConnection;
import tdt.db.dao.ITarifaDao;
import tdt.model.AgenciaZona;
import tdt.model.ComparadorTarifa;
import tdt.model.Tarifa;
import tdt.services.AlertExceptionService;

public class TarifaImpl implements ITarifaDao {

    private final String TABLE_NAME = "TARIFAS";

    @Override
    public ObservableList<Tarifa> obtenerTarifasUI(int idAgencia, int idZona) {
        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT kg, id_agencia, id_zona, precio FROM " + TABLE_NAME;

        ObservableList<Tarifa> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO TARIFAS ------------>" + sql);
                while (result.next()) {

                    int kg = result.getInt("kg");

                    int idAgenciaZona = result.getInt("id_agencia_zona");

                    double precio = result.getDouble("precio");

                    list.add(new Tarifa(kg, idAgencia, idZona, idAgenciaZona, precio));
                }
            }

        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Base de datos", "No se han podido obtener las tarifas", ex);

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
                Logger.getLogger(TarifaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public ArrayList<Tarifa> obtenerTarifasPorKilos(int idZona, int kg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tarifa obtenerTarifa(int idAgencia, int idZona, int kg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean añadirTarifa(Tarifa tarifa) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "INSERT INTO " + TABLE_NAME + " (kg, id_agencia, id_zona, precio) VALUES("
                + tarifa.getKg() + ", " + tarifa.getIdAgencia() + ", " + tarifa.getIdZona() + ", " + tarifa.getPrecio() + ")";
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                // System.out.println("Insertando Tarifa-----------> " + sql);
                stat.executeUpdate(sql);

                result = true;

                // System.out.println("Tarifa insertada !!");
            }
        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Base de datos", "No se ha podido añadir la tarifa", ex);

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
                Logger.getLogger(TarifaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public boolean actualizarTarifa(Tarifa tarifa) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "UPDATE " + TABLE_NAME + "  SET "
                + "precio= " + tarifa.getPrecio()
                + " WHERE kg = " + tarifa.getKg() + " AND id_zona = " + tarifa.getIdZona() + " AND id_agencia = " + tarifa.getIdAgencia();
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                // System.out.println("Actualizando Tarifa ---------------> " + sql);
                stat.executeUpdate(sql);

                // System.out.println("Tarifa actualizada !!");
                result = true;
            }
        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Base de datos", "No se ha podido actualizar la tarifa", ex);

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
                Logger.getLogger(TarifaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public boolean borrarTarifasDeAgencia(int idZona, int idAgencia) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id_zona = " + idZona + " AND id_agencia = " + idAgencia;
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                // System.out.println("Borrando Tarifa ---------------> " + sql);
                stat.executeUpdate(sql);

                // System.out.println("Tarifa borrada !!");
                result = true;
            }
        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Base de datos", "No se han podido borrar las tarifas", ex);

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
                Logger.getLogger(TarifaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public ComparadorTarifa compararTarifasAlbaran(double peso, int idZona, int idAgencia) {
        Connection conn = null;

        Statement stat = null;

        String sql = "SELECT"
                + " tar.kg, a.nombre, tar.id_agencia, tar.precio, z.plazo_entrega, a.bultos, a.recargo_combustible,"
                + " a.minimo_reembolso, a.envio_grande,	a.comision, z.incremento, z.max_kilos "
                + "FROM tarifas as tar INNER JOIN agencias a USING (id_agencia)"
                + " INNER JOIN  agencias_zonas z ON (a.id_agencia = z.id_agencia AND tar.id_zona=z.id_zona)"
                + " WHERE tar.kg >=" + peso + " AND tar.id_zona=" + idZona
                + " AND tar.id_agencia=" + idAgencia + " GROUP BY tar.id_agencia";

        ComparadorTarifa resultado = null;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO TARIFAS ------------>" + sql);
                while (result.next()) {

                    int kg = result.getInt("kg");

                    String nombreAgencia = result.getString("nombre");

                    double precio = result.getDouble("precio");

                    int plazoEntrega = result.getInt("plazo_entrega");

                    int bultos = result.getInt("bultos");

                    double recargo = result.getDouble("recargo_combustible");

                    double minimoReembolso = result.getDouble("minimo_reembolso");

                    boolean envioGrande = result.getBoolean("envio_grande");

                    double comision = result.getDouble("comision");

                    double incremento = result.getDouble("incremento");

                    int maxKilos = result.getInt("max_kilos");

                    resultado = new ComparadorTarifa(kg, nombreAgencia, idAgencia, idZona, precio, plazoEntrega, bultos, recargo, minimoReembolso, envioGrande, comision, incremento, maxKilos);
                }
            }

        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Base de datos", "No se ha podido comparar las tarifas-albaran", ex);

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
                Logger.getLogger(TarifaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    @Override
    public ObservableList<AgenciaZona> obtenerAgenciasPorZona(int idZona) {

        Connection conn = null;

        Statement stat = null;

        String sql = "SELECT az.id_agencia, az.incremento, az.plazo_entrega, az.max_kilos, a.nombre, a.bultos, a.envio_grande, a.comision  "
                + "FROM agencias_zonas az LEFT JOIN  agencias a USING(id_agencia) WHERE az.id_zona=" + idZona;

        ObservableList<AgenciaZona> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO AGENCIAS POR ZONA ------------>" + sql);
                while (result.next()) {

//                    int idAgenciaZona = result.getInt("id_agencia_zona");
                    int idAgencia = result.getInt("id_agencia");

                    double incremento = result.getDouble("incremento");

                    int plazoEntrega = result.getInt("plazo_entrega");

                    int maxKilos = result.getInt("max_kilos");

                    String nombre = result.getString("nombre");

                    int bultos = result.getInt("bultos");

                    boolean envioGrande = result.getBoolean("envio_grande");

                    double comision = result.getDouble("comision");

                    list.add(new AgenciaZona(idAgencia, idZona, incremento, plazoEntrega, maxKilos, nombre, bultos, envioGrande, comision));
                }
            }

        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Base de datos", "No se han podido obtener las tarifas por zona", ex);

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
                Logger.getLogger(TarifaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public ObservableList<Tarifa> obtenerTarifasPorZonaAgencia(int idZona, int idAgencia) {
        Connection conn = null;

        Statement stat = null;

        String sql = "SELECT kg, precio FROM " + TABLE_NAME + " WHERE id_zona=" + idZona + " AND id_agencia=" + idAgencia;

        ObservableList<Tarifa> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO TARIFA POR ZONA Y AGENCIA ------------>" + sql);
                while (result.next()) {

                    int kg = result.getInt("kg");

                    double precio = result.getDouble("precio");

                    list.add(new Tarifa(kg, idAgencia, idZona, precio));
                }
            }

        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Base de datos", "No se ha podido obtener las tarifaspor zona-agencia", ex);

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
                Logger.getLogger(TarifaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public boolean añadirAgenciaZona(int idAgencia, int idZona, double incremento, int plazoEntrega, int maxKilos) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;
        String sql = "INSERT INTO agencias_zonas (id_agencia, id_zona, incremento, plazo_entrega, max_kilos) VALUES("
                + idAgencia + ", " + idZona + ", " + incremento + ", " + plazoEntrega + ", " + maxKilos + ")";

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                // System.out.println("Insertando Agencia-Zona -----------> " + sql);
                stat.executeUpdate(sql);

                result = true;

                // System.out.println("Agencia-Zona insertada !!");
            }
        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Base de datos", "No se ha podido añadir la agencia-zona", ex);

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
                Logger.getLogger(TarifaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public boolean actualizarAgenciaZona(int idAgencia, int idZona, double incremento, int plazoEntrega, int maxKilos) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "UPDATE agencias_zonas SET incremento = " + incremento + ", plazo_entrega=" + plazoEntrega + ", max_kilos= " + maxKilos
                + " WHERE  id_agencia = " + idAgencia + " and id_zona = " + idZona;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                // System.out.println("Actualizando Agencia-Zona -----------> " + sql);
                stat.executeUpdate(sql);

                result = true;

                // System.out.println("Agencia-Zona actualizada !!");
            }
        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Base de datos", "No se ha podido actualizar la agencia-zona", ex);

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
                Logger.getLogger(TarifaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public boolean borrarAgenciaZona(int idAgencia, int idZona) {

        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "DELETE FROM agencias_zonas WHERE id_agencia=" + idAgencia + " AND id_zona=" + idZona;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.execute(sql);

                // System.out.println("Eliminando agencia de zona-----------------> " + sql);
                result = true;

                // System.out.println("Agencia eliminada de la zona !!");
            }
        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Base de datos", "No se ha podido borrar la agencia-zona", ex);

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
                Logger.getLogger(TarifaImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public int obtenerMaxKilo(int idAgencia, int idZona) {
        Connection conn = null;

        Statement stat = null;

        int kg = 0;

        String sql = "SELECT MAX(kg) as max FROM tarifas t JOIN agencias_zonas z ON (z.id_agencia=t.id_agencia AND t.id_zona=z.id_zona) "
                + "WHERE t.id_agencia =" + idAgencia + " AND t.id_zona=" + idZona;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO Maximo kilo ------------>" + sql);
                while (result.next()) {

                    kg = result.getInt("max");

                }
            }

        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Base de datos", "No se ha podido obtener el max kilo", ex);

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
                Logger.getLogger(TarifaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return kg;
    }

    @Override
    public ObservableList<String> obtenerNombresAgenciasPorZona(String nombreZona) {
        Connection conn = null;

        Statement stat = null;

        String sql = "SELECT a.nombre "
                + "FROM agencias_zonas az LEFT JOIN  agencias a USING(id_agencia) "
                + "LEFT JOIN zonas z USING(id_zona) WHERE z.nombre_zona='" + nombreZona + "'";

        ObservableList<String> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO NOMBRE AGENCIAS POR ZONA ------------>" + sql);
                while (result.next()) {

                    String nombre = result.getString("nombre");

                    list.add(nombre);
                }
            }

        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Base de datos", "No se han podido obtener los nombres de agencias por zona", ex);

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
                Logger.getLogger(TarifaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public ObservableList<Tarifa> copiarTarifa(String nombreAgencia, String nombreZona) {
        Connection conn = null;

        Statement stat = null;

        String sql = "SELECT kg, precio FROM tarifas tar LEFT JOIN agencias a USING (id_agencia) LEFT JOIN  agencias_zonas az "
                + "ON (tar.id_agencia = az.id_agencia AND tar.id_zona = az.id_zona) "
                + "LEFT JOIN zonas z on tar.id_zona = z.id_zona "
                + "WHERE z.nombre_zona='" + nombreZona + "' AND a.nombre='" + nombreAgencia + "'";

        ObservableList<Tarifa> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO  tarifas para Importar ------------>" + sql);
                while (result.next()) {

                    int kg = result.getInt("kg");
                    double precio = result.getDouble("precio");

                    list.add(new Tarifa(kg, precio));
                }
            }

        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Base de datos", "No se ha podido copiar la tarifa", ex);

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
                Logger.getLogger(TarifaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public boolean pegarTarifa(int idZona, int idAgencia, ObservableList<Tarifa> values) {
        Connection conn = null;

        PreparedStatement ps = null;

        boolean result = false;

        String sql = "INSERT INTO tarifas (kg, id_zona, id_agencia, precio) VALUES (?, ?, ?, ?);";

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                ps = conn.prepareStatement(sql);

                // System.out.println("Insertando Agencia-Zona -----------> " + sql);
                for (Tarifa tar : values) {

                    ps.setInt(1, tar.getKg());
                    ps.setInt(2, idZona);
                    ps.setInt(3, idAgencia);
                    ps.setDouble(4, tar.getPrecio());
                    ps.addBatch();
                }

                ps.executeBatch();

                result = true;

                // System.out.println("Agencia-Zona insertada !!");
            }
        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Base de datos", "No se ha podido pegar la tarifa", ex);

            alert.showAndWait();

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(TarifaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public boolean borrarTarifa(Tarifa tar) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "DELETE FROM tarifas WHERE kg=" + tar.getKg() + " AND id_agencia=" + tar.getIdAgencia() + " AND id_zona=" + tar.getIdZona();
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.execute(sql);

                // System.out.println("Eliminando agencia de zona-----------------> " + sql);
                result = true;

                // System.out.println("Agencia eliminada de la zona !!");
            }
        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Base de datos", "No se ha podido borrar la tarifa", ex);

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
                Logger.getLogger(TarifaImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
