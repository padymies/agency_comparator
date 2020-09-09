/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import tdt.db.dao.IProvinciaDao;
import tdt.model.Provincia;
import tdt.services.AlertExceptionService;

/**
 *
 * @author Usuario
 */
public class ProvinciaImpl implements IProvinciaDao {

    private final String TABLE_NAME = "provincias";

    @Override
    public ObservableList<Provincia> obtenerProvincias() {
        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT * FROM " + TABLE_NAME;

        ObservableList<Provincia> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO Provincias ------------>" + sql);
                while (result.next()) {

                    int id = result.getInt("id_provincia");

                    String nombre = result.getString("nombre");
                    String codigo = result.getString("codigo");

                    list.add(new Provincia(id, nombre, codigo));
                }
            }

        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se han podido obtener las provincias", ex);

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
                Logger.getLogger(ProvinciaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public Provincia obtenerProvincia(int provinciaId) {
        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT nombre, codigo FROM " + TABLE_NAME + " WHERE id_provincia =" + provinciaId;

        Provincia p = null;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO Provincia ------------>" + sql);
                while (result.next()) {

                    String nombre = result.getString("nombre");

                    String codigo = result.getString("codigo");

                    p = new Provincia(provinciaId, nombre, codigo);
                }
            }

        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido obtener la provincia", ex);

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
                Logger.getLogger(ProvinciaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p;
    }

    @Override
    public boolean actualizarProvincia(Provincia provincia) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "UPDATE " + TABLE_NAME + "  SET "
                + "`nombre`='" + provincia.getNombre() + "', `codigo`='" + provincia.getCodigo()
                + "', WHERE `id_provincia`=" + provincia.getId();
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                // System.out.println("Actualizando Provincia ---------------> " + sql);
                stat.executeUpdate(sql);

                // System.out.println("Provincia actualizada !!");
                result = true;
            }
        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido actualizar la provincia", ex);

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
                Logger.getLogger(ProvinciaImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public int añadirProvincia(Provincia provincia) {
        Connection conn = null;

        Statement stat = null;

        int id = -1;

        String sql = "INSERT INTO " + TABLE_NAME + " (nombre, codigo) VALUES('"
                + provincia.getNombre() + "', '" + provincia.getCodigo() + "')";

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                // System.out.println("Insertando Provincia -----------> " + sql);
                stat.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                ResultSet result = stat.getGeneratedKeys();

                if (result.next()) {

                    id = result.getInt(1);

                    String sqlProvincias_zonas = "INSERT INTO zonas_provincias (id_zona, id_provincia) VALUES(" + null + ", '" + id + "')";

                    stat.executeUpdate(sqlProvincias_zonas);
                } else {
                    // System.out.println("Error de inserción");
                }

                // System.out.println("Provincia insertada !!");
            }
        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido añadir la provincia", ex);

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
                Logger.getLogger(ProvinciaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public boolean borrarProvincia(int provinciaId) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE `id_provincia`=" + provinciaId;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.execute(sql);

                // System.out.println("Eliminando provincia-----------------> " + sql);
                result = true;

                // System.out.println("Provincia eliminada !!");
            }
        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido borrar la provincia", ex);

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
                Logger.getLogger(ProvinciaImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public ObservableList<Provincia> obtenerProvinciasDeZona(int idZona) {
        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT p.nombre, p.codigo FROM " + TABLE_NAME + " p JOIN zonas_provincias z USING (id_provincia) WHERE z.id_zona=" + idZona;

        ObservableList<Provincia> lista = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO Provincias de Zona------------>" + sql);
                while (result.next()) {

                    String nombre = result.getString("nombre");

                    String codigo = result.getString("codigo");

                    Provincia p = new Provincia(nombre, codigo);

                    lista.add(p);
                }
            }

        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se han podido obtener las provincias de zona", ex);

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
                Logger.getLogger(ProvinciaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public ObservableList<Provincia> obtenerProvinciasSinZonaAsociada() {

        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT nombre, codigo FROM " + TABLE_NAME + " WHERE id_provincia NOT IN (SELECT id_provincia FROM zonas_provincias)";

        ObservableList<Provincia> lista = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO Provincias sin Zona------------>" + sql);
                while (result.next()) {

                    String nombre = result.getString("nombre");

                    String codigo = result.getString("codigo");

                    Provincia p = new Provincia(nombre, codigo);

                    lista.add(p);
                }
            }

        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se han podido obtener las provincias sin zona", ex);

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
                Logger.getLogger(ProvinciaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public ObservableList<Provincia> obtenerProvinciasZona() {
        Connection conn = null;

        Statement stat = null;

        String sql = "SELECT p.id_provincia, p.nombre, p.codigo, z.id_zona, zo.nombre_zona FROM " + TABLE_NAME + " p LEFT JOIN zonas_provincias z USING (id_provincia) LEFT JOIN zonas zo USING (id_zona)";

        ObservableList<Provincia> lista = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO Provincias------------>" + sql);
                while (result.next()) {

                    int idProvincia = result.getInt("id_provincia");

                    String nombre = result.getString("nombre");

                    String codigo = result.getString("codigo");

                    int idZona = result.getInt("id_zona");

                    String nombreZona = result.getString("nombre_zona");

                    Provincia p = new Provincia(idProvincia, nombre, codigo, idZona, nombreZona);

                    lista.add(p);
                }
            }

        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se han podido obtener las provincias con zona", ex);

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
                Logger.getLogger(ProvinciaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public boolean actualizarProvinciaZona(int id_provincia, String nombre_zona) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "UPDATE zonas_provincias zp SET zp.id_zona= (SELECT id_zona FROM zonas z WHERE z.nombre_zona='" + nombre_zona
                + "') WHERE zp.id_provincia=" + id_provincia;
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                // System.out.println("Actualizando Zona-Provincia ---------------> " + sql);
                stat.executeUpdate(sql);

                // System.out.println("Zona-Provincia actualizada !!");
                result = true;
            }
        } catch (SQLException ex) {

            
        AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se han podido actualizar la provincias-zona", ex);

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
                Logger.getLogger(ProvinciaImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

}
