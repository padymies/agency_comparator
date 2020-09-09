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
import tdt.db.dao.IZonaDao;
import tdt.model.Zona;
import tdt.services.AlertExceptionService;

/**
 *
 * @author Usuario
 */
public class ZonaImpl implements IZonaDao {

    private final String TABLE_NAME = "ZONAS";

    @Override
    public ObservableList<Zona> obtenerZonasUI() {
        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT * FROM " + TABLE_NAME;

        ObservableList<Zona> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

//                System.out.println("OBTENIENDO ZONAS ------------>" + sql);
                while (result.next()) {

                    int idZona = result.getInt("id_zona");

                    String nombreZona = result.getString("nombre_zona");

                    String paisZona = result.getString("pais_zona");

                    String descripcion = result.getString("descripción");

                    list.add(new Zona(idZona, nombreZona, paisZona, descripcion));
                }
            }

        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se han podido obtener las zonas", ex);

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
                Logger.getLogger(ZonaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public Zona obtenerZona(int idZona) {
        Connection conn = null;

        Statement stat = null;

        Zona zona = null;

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE `id_zona`=" + idZona;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

//                System.out.println("Recuperando Zona----------> " + sql);
                result.next();

                int id = result.getInt("id_zona");

                String nombre_zona = result.getString("nombre_zona");

                String pais_zona = result.getString("pais_zona");

                String descripcion = result.getString("descripción");

                zona = new Zona(id, nombre_zona, pais_zona, descripcion);
            }

//            System.out.println("Zona recuperada !!");
        } catch (SQLException ex) {

              AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido obtener la zona", ex);

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
                Logger.getLogger(ZonaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return zona;
    }

    @Override
    public int añadirZona(Zona zona) {
        Connection conn = null;

        Statement stat = null;

        int id = -1;

        String sql = "INSERT INTO " + TABLE_NAME + " ( nombre_zona, pais_zona, descripción) VALUES('"
                + zona.getNombre() + "', '" + zona.getPais() + "', '" + zona.getDescripcion() + "')";
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

//                System.out.println("Insertando zona -----------> " + sql);
                stat.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                ResultSet result = stat.getGeneratedKeys();

                if (result.next()) {

                    id = result.getInt(1);

                } else {
                    System.out.println("Error de inserción zona");
                }

//                System.out.println("Zona insertada !!");
            }
        } catch (SQLException ex) {

              AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido añadir la zona", ex);

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
                Logger.getLogger(ZonaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;

    }

    @Override
    public boolean actualizarZona(Zona zona) {

        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "UPDATE " + TABLE_NAME + "  SET "
                + "`nombre_zona`= '" + zona.getNombre() + "', `pais_zona`= '" + zona.getPais()
                + "', `descripción`= '" + zona.getDescripcion()
                + "' WHERE `id_zona`=" + zona.getIdZona();
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

//                System.out.println("Actualizando zona ---------------> " + sql);
                stat.executeUpdate(sql);

//                System.out.println("Zona actualizada !!");
                result = true;
            }
        } catch (SQLException ex) {

              AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido actualizar la zona", ex);

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
                Logger.getLogger(ZonaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public boolean borrarZona(int zonaId) {

        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE `id_zona`=" + zonaId;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.execute(sql);

//                System.out.println("Eliminando zona-----------------> " + sql);
                result = true;

//                System.out.println("Zona eliminada !!");
            }
        } catch (SQLException ex) {

           AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido borrar la zona", ex);

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
                Logger.getLogger(ZonaImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public ObservableList<String> obtenerNombresZonas() {
        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT nombre_zona FROM " + TABLE_NAME;

        ObservableList<String> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

//                System.out.println("OBTENIENDO NOMBRE DE ZONAS ------------>" + sql);
                while (result.next()) {

                    String nombre = result.getString("nombre_zona");

                    list.add(nombre);
                }
            }

        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se han podido obtener los nombres de zona", ex);

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
                Logger.getLogger(ZonaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public Zona obtenerZonaPorProvincia(String cp) {
        Connection conn = null;

        Statement stat = null;

        Zona zona = null;

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id_zona ="
                + " (SELECT id_zona FROM zonas_provincias WHERE id_provincia= "
                + " (SELECT id_provincia FROM PROVINCIAS WHERE codigo='" + cp + "')) ";

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

//                System.out.println("Recuperando Zona por provincia----------> " + sql);
                while (result.next()) {

                    int id = result.getInt("id_zona");

                    String nombre_zona = result.getString("nombre_zona");

                    String pais_zona = result.getString("pais_zona");

                    String descripcion = result.getString("descripción");

                    zona = new Zona(id, nombre_zona, pais_zona, descripcion);
                }

            }

//            System.out.println("Zona recuperada !!");
        } catch (SQLException ex) {

              AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido obtener las zonas por provincia", ex);

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
                Logger.getLogger(ZonaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return zona;
    }

    @Override
    public Zona obtenerZonaPorPais(String nombrePais) {
        Connection conn = null;

        Statement stat = null;

        Zona zona = null;

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE pais_zona ='" + nombrePais + "'";

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

//                System.out.println("Recuperando Zona por pais----------> " + sql);
                while (result.next()) {

                    int id = result.getInt("id_zona");

                    String nombre_zona = result.getString("nombre_zona");

                    String pais_zona = result.getString("pais_zona");

                    String descripcion = result.getString("descripción");

                    zona = new Zona(id, nombre_zona, pais_zona, descripcion);
                }

            }

//            System.out.println("Zona recuperada !!");
        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido obtener la zona por pais", ex);

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
                Logger.getLogger(ZonaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return zona;
    }

}
