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
import tdt.db.dao.IRateDao;
import tdt.model.AgencyZone;
import tdt.model.Rate;
import tdt.model.RateComparator;
import tdt.services.AlertExceptionService;

public class RateImpl implements IRateDao {

    private final String TABLE_NAME = "TARIFAS";

    @Override
    public ObservableList<Rate> getRatesUI(int agencyId, int zoneId) {
        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT kg, id_agencia, id_zona, precio FROM " + TABLE_NAME;

        ObservableList<Rate> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                while (result.next()) {

                    int kg = result.getInt("kg");

                    int agencyZoneId = result.getInt("id_agencia_zona");

                    double price = result.getDouble("precio");

                    list.add(new Rate(kg, agencyId, zoneId, agencyZoneId, price));
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
                Logger.getLogger(RateImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public ArrayList<Rate> getRatesByKilos(int idZona, int kg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rate getRate(int idAgencia, int idZona, int kg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addRate(Rate rate) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "INSERT INTO " + TABLE_NAME + " (kg, id_agencia, id_zona, precio) VALUES("
                + rate.getKg() + ", " + rate.getAgencyId() + ", " + rate.getZoneId() + ", " + rate.getPrice() + ")";
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.executeUpdate(sql);

                result = true;

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
                Logger.getLogger(RateImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public boolean updateRate(Rate rate) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "UPDATE " + TABLE_NAME + "  SET "
                + "precio= " + rate.getPrice()
                + " WHERE kg = " + rate.getKg() + " AND id_zona = " + rate.getZoneId() + " AND id_agencia = " + rate.getAgencyId();
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.executeUpdate(sql);

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
                Logger.getLogger(RateImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public boolean deleteRatesFromAgency(int zoneId, int agencyId) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id_zona = " + zoneId + " AND id_agencia = " + agencyId;
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.executeUpdate(sql);

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
                Logger.getLogger(RateImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public RateComparator ratesNotesCompare(double peso, int idZona, int idAgencia) {
        Connection conn = null;

        Statement stat = null;

        String sql = "SELECT"
                + " tar.kg, a.nombre, tar.id_agencia, tar.precio, z.plazo_entrega, a.bultos, a.recargo_combustible,"
                + " a.minimo_reembolso, a.envio_grande,	a.comision, z.incremento, z.max_kilos "
                + "FROM tarifas as tar INNER JOIN agencias a USING (id_agencia)"
                + " INNER JOIN  agencias_zonas z ON (a.id_agencia = z.id_agencia AND tar.id_zona=z.id_zona)"
                + " WHERE tar.kg >=" + peso + " AND tar.id_zona=" + idZona
                + " AND tar.id_agencia=" + idAgencia + " GROUP BY tar.id_agencia";

        RateComparator rateResult = null;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                while (result.next()) {

                    int kg = result.getInt("kg");

                    String agencyName = result.getString("nombre");

                    double price = result.getDouble("precio");

                    int deliveryTime = result.getInt("plazo_entrega");

                    int bundles = result.getInt("bultos");

                    double surcharge = result.getDouble("recargo_combustible");

                    double minimum = result.getDouble("minimo_reembolso");

                    boolean bigShipment = result.getBoolean("envio_grande");

                    double comision = result.getDouble("comision");

                    double increment = result.getDouble("incremento");

                    int maxKilos = result.getInt("max_kilos");

                    rateResult = new RateComparator(kg, agencyName, idAgencia, idZona, price, deliveryTime, bundles, surcharge, minimum, bigShipment, comision, increment, maxKilos);
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
                Logger.getLogger(RateImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rateResult;
    }

    @Override
    public ObservableList<AgencyZone> getAgenciesByZone(int zoneId) {

        Connection conn = null;

        Statement stat = null;

        String sql = "SELECT az.id_agencia, az.incremento, az.plazo_entrega, az.max_kilos, a.nombre, a.bultos, a.envio_grande, a.comision  "
                + "FROM agencias_zonas az LEFT JOIN  agencias a USING(id_agencia) WHERE az.id_zona=" + zoneId;

        ObservableList<AgencyZone> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                while (result.next()) {

                    int agencyId = result.getInt("id_agencia");

                    double increment = result.getDouble("incremento");

                    int deliveryTime = result.getInt("plazo_entrega");

                    int maxKilos = result.getInt("max_kilos");

                    String name = result.getString("nombre");

                    int bundles = result.getInt("bultos");

                    boolean bigShipment = result.getBoolean("envio_grande");

                    double comision = result.getDouble("comision");

                    list.add(new AgencyZone(agencyId, zoneId, increment, deliveryTime, maxKilos, name, bundles, bigShipment, comision));
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
                Logger.getLogger(RateImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public ObservableList<Rate> getRateByAgencyZone(int idZona, int idAgencia) {
        Connection conn = null;

        Statement stat = null;

        String sql = "SELECT kg, precio FROM " + TABLE_NAME + " WHERE id_zona=" + idZona + " AND id_agencia=" + idAgencia;

        ObservableList<Rate> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                while (result.next()) {

                    int kg = result.getInt("kg");

                    double price = result.getDouble("precio");

                    list.add(new Rate(kg, idAgencia, idZona, price));
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
                Logger.getLogger(RateImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public boolean addZoneAgency(int agencyId, int zoneId, double increment, int deliveryTime, int maxKilos) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;
        String sql = "INSERT INTO agencias_zonas (id_agencia, id_zona, incremento, plazo_entrega, max_kilos) VALUES("
                + agencyId + ", " + zoneId + ", " + increment + ", " + deliveryTime + ", " + maxKilos + ")";

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.executeUpdate(sql);

                result = true;

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
                Logger.getLogger(RateImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public boolean updateZoneAgency(int agencyId, int zoneId, double increment, int deliveryTime, int maxKilos) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "UPDATE agencias_zonas SET incremento = " + increment + ", plazo_entrega=" + deliveryTime + ", max_kilos= " + maxKilos
                + " WHERE  id_agencia = " + agencyId + " and id_zona = " + zoneId;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.executeUpdate(sql);

                result = true;

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
                Logger.getLogger(RateImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public boolean deleteZoneAgency(int agencyId, int zoneId) {

        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "DELETE FROM agencias_zonas WHERE id_agencia=" + agencyId + " AND id_zona=" + zoneId;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.execute(sql);

                result = true;

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
                Logger.getLogger(RateImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public int getMaxKilo(int agencyId, int zoneId) {
        Connection conn = null;

        Statement stat = null;

        int kg = 0;

        String sql = "SELECT MAX(kg) as max FROM tarifas t JOIN agencias_zonas z ON (z.id_agencia=t.id_agencia AND t.id_zona=z.id_zona) "
                + "WHERE t.id_agencia =" + agencyId + " AND t.id_zona=" + zoneId;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

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
                Logger.getLogger(RateImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return kg;
    }

    @Override
    public ObservableList<String> getAgenciesNameByZone(String zoneName) {
        Connection conn = null;

        Statement stat = null;

        String sql = "SELECT a.nombre "
                + "FROM agencias_zonas az LEFT JOIN  agencias a USING(id_agencia) "
                + "LEFT JOIN zonas z USING(id_zona) WHERE z.nombre_zona='" + zoneName + "'";

        ObservableList<String> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                while (result.next()) {

                    String name = result.getString("nombre");

                    list.add(name);
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
                Logger.getLogger(RateImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public ObservableList<Rate> copyRate(String agencyName, String zoneName) {
        Connection conn = null;

        Statement stat = null;

        String sql = "SELECT kg, precio FROM tarifas tar LEFT JOIN agencias a USING (id_agencia) LEFT JOIN  agencias_zonas az "
                + "ON (tar.id_agencia = az.id_agencia AND tar.id_zona = az.id_zona) "
                + "LEFT JOIN zonas z on tar.id_zona = z.id_zona "
                + "WHERE z.nombre_zona='" + zoneName + "' AND a.nombre='" + agencyName + "'";

        ObservableList<Rate> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                while (result.next()) {

                    int kg = result.getInt("kg");
                    double precio = result.getDouble("precio");

                    list.add(new Rate(kg, precio));
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
                Logger.getLogger(RateImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public boolean pasteRate(int zoneId, int agencyId, ObservableList<Rate> values) {
        Connection conn = null;

        PreparedStatement ps = null;

        boolean result = false;

        String sql = "INSERT INTO tarifas (kg, id_zona, id_agencia, precio) VALUES (?, ?, ?, ?);";

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                ps = conn.prepareStatement(sql);

                for (Rate tar : values) {

                    ps.setInt(1, tar.getKg());
                    ps.setInt(2, zoneId);
                    ps.setInt(3, agencyId);
                    ps.setDouble(4, tar.getPrice());
                    ps.addBatch();
                }

                ps.executeBatch();

                result = true;
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
                Logger.getLogger(RateImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public boolean deleteRate(Rate tar) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "DELETE FROM tarifas WHERE kg=" + tar.getKg() + " AND id_agencia=" + tar.getAgencyId() + " AND id_zona=" + tar.getZoneId();
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.execute(sql);

                result = true;

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
                Logger.getLogger(RateImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
