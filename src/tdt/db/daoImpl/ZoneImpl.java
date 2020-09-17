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
import tdt.model.Zone;
import tdt.services.AlertExceptionService;
import tdt.db.dao.IZoneDao;

/**
 *
 * @author Usuario
 */
public class ZoneImpl implements IZoneDao {

    private final String TABLE_NAME = "ZONAS";

    @Override
    public ObservableList<Zone> getZonesUI() {
        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT * FROM " + TABLE_NAME;

        ObservableList<Zone> list = FXCollections.observableArrayList();

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

                    list.add(new Zone(idZona, nombreZona, paisZona, descripcion));
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
                Logger.getLogger(ZoneImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public Zone getZone(int idZona) {
        Connection conn = null;

        Statement stat = null;

        Zone zona = null;

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE `id_zona`=" + idZona;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

//                System.out.println("Recuperando Zone----------> " + sql);
                result.next();

                int id = result.getInt("id_zona");

                String nombre_zona = result.getString("nombre_zona");

                String pais_zona = result.getString("pais_zona");

                String descripcion = result.getString("descripción");

                zona = new Zone(id, nombre_zona, pais_zona, descripcion);
            }

//            System.out.println("Zone recuperada !!");
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
                Logger.getLogger(ZoneImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return zona;
    }

    @Override
    public int addZone(Zone zona) {
        Connection conn = null;

        Statement stat = null;

        int id = -1;

        String sql = "INSERT INTO " + TABLE_NAME + " ( nombre_zona, pais_zona, descripción) VALUES('"
                + zona.getName() + "', '" + zona.getCountry() + "', '" + zona.getDescription() + "')";
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

//                System.out.println("Zone insertada !!");
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
                Logger.getLogger(ZoneImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;

    }

    @Override
    public boolean updateZone(Zone zona) {

        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "UPDATE " + TABLE_NAME + "  SET "
                + "`nombre_zona`= '" + zona.getName() + "', `pais_zona`= '" + zona.getCountry()
                + "', `descripción`= '" + zona.getDescription()
                + "' WHERE `id_zona`=" + zona.getZoneId();
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

//                System.out.println("Actualizando zona ---------------> " + sql);
                stat.executeUpdate(sql);

//                System.out.println("Zone actualizada !!");
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
                Logger.getLogger(ZoneImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public boolean deleteZone(int zonaId) {

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

//                System.out.println("Zone eliminada !!");
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
                Logger.getLogger(ZoneImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public ObservableList<String> getZoneNames() {
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
                Logger.getLogger(ZoneImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public Zone getZoneByCity(String cp) {
        Connection conn = null;

        Statement stat = null;

        Zone zona = null;

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id_zona ="
                + " (SELECT id_zona FROM zonas_provincias WHERE id_provincia= "
                + " (SELECT id_provincia FROM PROVINCIAS WHERE codigo='" + cp + "')) ";

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

//                System.out.println("Recuperando Zone por provincia----------> " + sql);
                while (result.next()) {

                    int id = result.getInt("id_zona");

                    String nombre_zona = result.getString("nombre_zona");

                    String pais_zona = result.getString("pais_zona");

                    String descripcion = result.getString("descripción");

                    zona = new Zone(id, nombre_zona, pais_zona, descripcion);
                }

            }

//            System.out.println("Zone recuperada !!");
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
                Logger.getLogger(ZoneImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return zona;
    }

    @Override
    public Zone getZoneByCountry(String nombrePais) {
        Connection conn = null;

        Statement stat = null;

        Zone zona = null;

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE pais_zona ='" + nombrePais + "'";

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

//                System.out.println("Recuperando Zone por pais----------> " + sql);
                while (result.next()) {

                    int id = result.getInt("id_zona");

                    String nombre_zona = result.getString("nombre_zona");

                    String pais_zona = result.getString("pais_zona");

                    String descripcion = result.getString("descripción");

                    zona = new Zone(id, nombre_zona, pais_zona, descripcion);
                }

            }

//            System.out.println("Zone recuperada !!");
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
                Logger.getLogger(ZoneImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return zona;
    }

}
