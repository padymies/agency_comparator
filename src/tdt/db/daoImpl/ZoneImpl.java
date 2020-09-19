
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
import tdt.db.dao.IZoneDao;
import tdt.model.Zone;
import tdt.services.AlertExceptionService;


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

                while (result.next()) {

                    int zoneId = result.getInt("id_zona");

                    String zoneName = result.getString("nombre_zona");

                    String zoneCountry = result.getString("pais_zona");

                    String description = result.getString("descripción");

                    list.add(new Zone(zoneId, zoneName, zoneCountry, description));
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
    public Zone getZone(int zoneId) {
        Connection conn = null;

        Statement stat = null;

        Zone zone = null;

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE `id_zona`=" + zoneId;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                result.next();

                int id = result.getInt("id_zona");

                String zoneName = result.getString("nombre_zona");

                String zoneCountry = result.getString("pais_zona");

                String description = result.getString("descripción");

                zone = new Zone(id, zoneName, zoneCountry, description);
            }

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
        return zone;
    }

    @Override
    public int addZone(Zone zone) {
        Connection conn = null;

        Statement stat = null;

        int id = -1;

        String sql = "INSERT INTO " + TABLE_NAME + " ( nombre_zona, pais_zona, descripción) VALUES('"
                + zone.getName() + "', '" + zone.getCountry() + "', '" + zone.getDescription() + "')";
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                ResultSet result = stat.getGeneratedKeys();

                if (result.next()) {

                    id = result.getInt(1);

                } else {
                    System.out.println("Error de inserción zona");
                }

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
    public boolean updateZone(Zone zone) {

        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "UPDATE " + TABLE_NAME + "  SET "
                + "`nombre_zona`= '" + zone.getName() + "', `pais_zona`= '" + zone.getCountry()
                + "', `descripción`= '" + zone.getDescription()
                + "' WHERE `id_zona`=" + zone.getZoneId();
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.executeUpdate(sql);

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
    public boolean deleteZone(int zoneId) {

        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE `id_zona`=" + zoneId;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.execute(sql);

                result = true;

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

                while (result.next()) {

                    String name = result.getString("nombre_zona");

                    list.add(name);
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

        Zone zone = null;

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id_zona ="
                + " (SELECT id_zona FROM zonas_provincias WHERE id_provincia= "
                + " (SELECT id_provincia FROM PROVINCIAS WHERE codigo='" + cp + "')) ";

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                while (result.next()) {

                    int id = result.getInt("id_zona");

                    String zoneName = result.getString("nombre_zona");

                    String zoneCountry = result.getString("pais_zona");

                    String description = result.getString("descripción");

                    zone = new Zone(id, zoneName, zoneCountry, description);
                }

            }

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
        return zone;
    }

    @Override
    public Zone getZoneByCountry(String countryName) {
        Connection conn = null;

        Statement stat = null;

        Zone zone = null;

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE pais_zona ='" + countryName + "'";

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                while (result.next()) {

                    int id = result.getInt("id_zona");

                    String zoneName = result.getString("nombre_zona");

                    String zoneCountry = result.getString("pais_zona");

                    String description = result.getString("descripción");

                    zone = new Zone(id, zoneName, zoneCountry, description);
                }

            }

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
        return zone;
    }

}
