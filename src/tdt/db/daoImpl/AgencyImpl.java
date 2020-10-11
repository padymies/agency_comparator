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
import tdt.db.dao.IAgencyDao;
import tdt.model.Agency;
import tdt.services.AlertExceptionService;

public class AgencyImpl implements IAgencyDao {

    private final String TABLE_NAME = "AGENCIAS";

    @Override
    public ObservableList<Agency> getAgencies() {

        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT * FROM " + TABLE_NAME;

        ObservableList<Agency> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                while (result.next()) {

                    int id = result.getInt("id_agencia");

                    String name = result.getString("nombre");

                    int bundles = result.getInt("bultos");

                    double surchargeFuel = result.getDouble("recargo_combustible");

                    double minimumRefund = result.getDouble("minimo_reembolso");

                    double comision = result.getDouble("comision");

                    boolean bigShipment = result.getBoolean("envio_grande");

                    list.add(new Agency(id, name, bundles, surchargeFuel, minimumRefund, bigShipment, comision));
                }
            }

        } catch (SQLException ex) {

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
                Logger.getLogger(AgencyImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public Agency getAgency(int agencyId) {

        Connection conn = null;

        Statement stat = null;

        Agency agency = null;

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE `id_agencia`=" + agencyId;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                result.next();

                int id = result.getInt("id_agencia");

                String name = result.getString("nombre");

                int bundles = result.getInt("bultos");

                double surchargeFuel = result.getDouble("recargo_combustible");

                double minimumRefund = result.getDouble("minimo_reembolso");

                double comision = result.getDouble("comision");

                boolean bigShipment = result.getBoolean("envio_grande");

                agency = new Agency(id, name, bundles, surchargeFuel, minimumRefund, bigShipment, comision);
            }

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
                Logger.getLogger(AgencyImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return agency;
    }

    @Override
    public int addAgency(Agency agency) {

        Connection conn = null;

        Statement stat = null;

        int id = -1;

        String sql = "INSERT INTO " + TABLE_NAME + " (nombre, bultos, recargo_combustible, minimo_reembolso, envio_grande, comision) VALUES('"
                + agency.getName() + "', " + agency.getBundles() + ", " + agency.getSurchargeFuel()
                + ", " + agency.getMinimumRefund() + ", " + agency.isBigShipment() + ", " + agency.getComision() + ")";
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                ResultSet result = stat.getGeneratedKeys();

                if (result.next()) {

                    id = result.getInt(1);

                }

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
                Logger.getLogger(AgencyImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public boolean updateAgency(Agency agencia) {

        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "UPDATE " + TABLE_NAME + "  SET "
                + "nombre='" + agencia.getName() + "', bultos=" + agencia.getBundles()
                + ", recargo_combustible=" + agencia.getSurchargeFuel()
                + ", minimo_reembolso=" + agencia.getMinimumRefund()
                + ", envio_grande=" + agencia.isBigShipment()
                + ", comision=" + agencia.getComision()
                + " WHERE id_agencia=" + agencia.getAgencyId();
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.executeUpdate(sql);

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
                Logger.getLogger(AgencyImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public boolean deleteAgency(int agenciaId) {

        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE `id_agencia`=" + agenciaId;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.execute(sql);

                result = true;

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
                Logger.getLogger(AgencyImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public ObservableList<String> getAgencyNames() {
        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT nombre FROM " + TABLE_NAME;

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
                Logger.getLogger(AgencyImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

}
