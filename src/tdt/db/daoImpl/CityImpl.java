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
import tdt.model.City;
import tdt.services.AlertExceptionService;
import tdt.db.dao.ICityDao;

/**
 *
 * @author Usuario
 */
public class CityImpl implements ICityDao {

    private final String TABLE_NAME = "provincias";

    @Override
    public ObservableList<City> getCities() {
        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT * FROM " + TABLE_NAME;

        ObservableList<City> list = FXCollections.observableArrayList();

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

                    list.add(new City(id, nombre, codigo));
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
                Logger.getLogger(CityImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public City getCity(int provinciaId) {
        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT nombre, codigo FROM " + TABLE_NAME + " WHERE id_provincia =" + provinciaId;

        City p = null;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO City ------------>" + sql);
                while (result.next()) {

                    String nombre = result.getString("nombre");

                    String codigo = result.getString("codigo");

                    p = new City(provinciaId, nombre, codigo);
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
                Logger.getLogger(CityImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p;
    }

    @Override
    public boolean updateCity(City provincia) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "UPDATE " + TABLE_NAME + "  SET "
                + "`nombre`='" + provincia.getName() + "', `codigo`='" + provincia.getCode()
                + "', WHERE `id_provincia`=" + provincia.getId();
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                // System.out.println("Actualizando City ---------------> " + sql);
                stat.executeUpdate(sql);

                // System.out.println("City actualizada !!");
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
                Logger.getLogger(CityImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public int addCity(City provincia) {
        Connection conn = null;

        Statement stat = null;

        int id = -1;

        String sql = "INSERT INTO " + TABLE_NAME + " (nombre, codigo) VALUES('"
                + provincia.getName() + "', '" + provincia.getCode() + "')";

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                // System.out.println("Insertando City -----------> " + sql);
                stat.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                ResultSet result = stat.getGeneratedKeys();

                if (result.next()) {

                    id = result.getInt(1);

                    String sqlProvincias_zonas = "INSERT INTO zonas_provincias (id_zona, id_provincia) VALUES(" + null + ", '" + id + "')";

                    stat.executeUpdate(sqlProvincias_zonas);
                } else {
                    // System.out.println("Error de inserción");
                }

                // System.out.println("City insertada !!");
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
                Logger.getLogger(CityImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public boolean deleteCity(int provinciaId) {
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

                // System.out.println("City eliminada !!");
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
                Logger.getLogger(CityImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public ObservableList<City> getCitiesByZone(int idZona) {
        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT p.nombre, p.codigo FROM " + TABLE_NAME + " p JOIN zonas_provincias z USING (id_provincia) WHERE z.id_zona=" + idZona;

        ObservableList<City> lista = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO Provincias de Zona------------>" + sql);
                while (result.next()) {

                    String nombre = result.getString("nombre");

                    String codigo = result.getString("codigo");

                    City p = new City(nombre, codigo);

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
                Logger.getLogger(CityImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public ObservableList<City> getCitiesNoZone() {

        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT nombre, codigo FROM " + TABLE_NAME + " WHERE id_provincia NOT IN (SELECT id_provincia FROM zonas_provincias)";

        ObservableList<City> lista = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO Provincias sin Zona------------>" + sql);
                while (result.next()) {

                    String nombre = result.getString("nombre");

                    String codigo = result.getString("codigo");

                    City p = new City(nombre, codigo);

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
                Logger.getLogger(CityImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public ObservableList<City> getZoneCities() {
        Connection conn = null;

        Statement stat = null;

        String sql = "SELECT p.id_provincia, p.nombre, p.codigo, z.id_zona, zo.nombre_zona FROM " + TABLE_NAME + " p LEFT JOIN zonas_provincias z USING (id_provincia) LEFT JOIN zonas zo USING (id_zona)";

        ObservableList<City> lista = FXCollections.observableArrayList();

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

                    City p = new City(idProvincia, nombre, codigo, idZona, nombreZona);

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
                Logger.getLogger(CityImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public boolean updateZoneCity(int id_provincia, String nombre_zona) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "UPDATE zonas_provincias zp SET zp.id_zona= (SELECT id_zona FROM zonas z WHERE z.nombre_zona='" + nombre_zona
                + "') WHERE zp.id_provincia=" + id_provincia;
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                // System.out.println("Actualizando Zona-City ---------------> " + sql);
                stat.executeUpdate(sql);

                // System.out.println("Zona-City actualizada !!");
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
                Logger.getLogger(CityImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

}
