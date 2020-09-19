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
import tdt.db.dao.ICityDao;
import tdt.model.City;
import tdt.services.AlertExceptionService;

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

                stat.executeUpdate(sql);

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
    public int addCity(City city) {
        Connection conn = null;

        Statement stat = null;

        int id = -1;

        String sql = "INSERT INTO " + TABLE_NAME + " (nombre, codigo) VALUES('"
                + city.getName() + "', '" + city.getCode() + "')";

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                ResultSet result = stat.getGeneratedKeys();

                if (result.next()) {

                    id = result.getInt(1);

                    String sqlCityZone = "INSERT INTO zonas_provincias (id_zona, id_provincia) VALUES(" + null + ", '" + id + "')";

                    stat.executeUpdate(sqlCityZone);
                }
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
    public boolean deleteCity(int cityId) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE `id_provincia`=" + cityId;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.execute(sql);

                result = true;

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
    public ObservableList<City> getCitiesByZone(int zoneId) {
        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT p.nombre, p.codigo FROM " + TABLE_NAME + " p JOIN zonas_provincias z USING (id_provincia) WHERE z.id_zona=" + zoneId;

        ObservableList<City> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                while (result.next()) {

                    String name = result.getString("nombre");

                    String code = result.getString("codigo");

                    City city = new City(name, code);

                    list.add(city);
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
        return list;
    }

    @Override
    public ObservableList<City> getCitiesNoZone() {

        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT nombre, codigo FROM " + TABLE_NAME + " WHERE id_provincia NOT IN (SELECT id_provincia FROM zonas_provincias)";

        ObservableList<City> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                while (result.next()) {

                    String name = result.getString("nombre");

                    String code = result.getString("codigo");

                    City p = new City(name, code);

                    list.add(p);
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
        return list;
    }

    @Override
    public ObservableList<City> getZoneCities() {
        Connection conn = null;

        Statement stat = null;

        String sql = "SELECT p.id_provincia, p.nombre, p.codigo, z.id_zona, zo.nombre_zona FROM " + TABLE_NAME + " p LEFT JOIN zonas_provincias z USING (id_provincia) LEFT JOIN zonas zo USING (id_zona)";

        ObservableList<City> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO Provincias------------>" + sql);
                while (result.next()) {

                    int cityId = result.getInt("id_provincia");

                    String name = result.getString("nombre");

                    String code = result.getString("codigo");

                    int zoneId = result.getInt("id_zona");

                    String zoneName = result.getString("nombre_zona");

                    City p = new City(cityId, name, code, zoneId, zoneName);

                    list.add(p);
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
        return list;
    }

    @Override
    public boolean updateZoneCity(int cityId, String zoneName) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "UPDATE zonas_provincias zp SET zp.id_zona= (SELECT id_zona FROM zonas z WHERE z.nombre_zona='" + zoneName
                + "') WHERE zp.id_provincia=" + cityId;
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.executeUpdate(sql);

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
