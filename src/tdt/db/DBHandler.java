
package tdt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tdt.model.Provider;

/**
 *
 * @author Usuario
 */
public class DBHandler {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./resources/tdt";
    private static final String USERNAME = "tdt";
    private static final String PASSWORD = "tdt";
    private static Connection conn;
    private Statement stat;

    public DBHandler() {
        connect();
        createTableProviders();
    }

    private void connect() {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting db ...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("DB Connection succesful");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("ERROR CONECTANDO A LA BASE DE DATOS " + ex.getMessage());
            ex.getMessage();
        }
    }
    
    public void close() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createTableProviders() {
        String sql = " CREATE TABLE IF NOT EXISTS PROVIDERS"
                + "(id bigint auto_increment NOT NULL PRIMARY KEY,"
                + "name VARCHAR2(255),"
                + "cif VARCHAR2(255))";
        try {
            stat = conn.createStatement();
            System.out.println("Creating table Providers ----------> " + sql);
            stat.execute(sql);
            System.out.println("Create table Providers successful");
        } catch (SQLException ex) {
            System.out.println("ERROR => Create table PROVIDERS");
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Provider> getProviders() {
        String sql = " SELECT * FROM PROVIDERS";
        ObservableList<Provider> list = FXCollections.observableArrayList();
        try {
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(sql);
            System.out.println("Getting providers ------------>" + sql);

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String cif = result.getString("cif");
                list.add(new Provider(id, name, cif));
            }
            stat.close();
            System.out.println("Get providers successful");
        } catch (SQLException ex) {
            System.out.println("Error getting providers list");
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Provider getProvider(int providerId) {
        String sql = "SELECT * FROM PROVIDERS WHERE id=" + providerId;
        try {
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(sql);
            System.out.println("Getting provider ----------> " + sql);

            result.next();
            int id = result.getInt("id");
            String name = result.getString("name");
            String cif = result.getString("cif");
            Provider prov = new Provider(id, name, cif);

            stat.close();
            System.out.println("Get provider successful");
            return prov;
        } catch (SQLException ex) {
            System.out.println("Error getting provider");
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int addProvider(Provider p) {
        String sql = "INSERT INTO PROVIDERS (name, cif) VALUES('"
                + p.getName() + "', '" + p.getCif() + "')";
        try {
            stat = conn.createStatement();
            System.out.println("Insert provider -----------> " + sql);
            stat.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = stat.getGeneratedKeys();
            result.next();
            int id = result.getInt("id");
            stat.close();
            System.out.println("Insert successful");
            return id;
        } catch (SQLException ex) {
            System.out.println("Insert error");
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean updateProvider(Provider p) {
        String sql = "UPDATE PROVIDERS SET "
                + "name='" + p.getName() + "', cif='" + p.getCif() + "'";
        try {
            stat = conn.createStatement();
            System.out.println("Update provider ---------------> " + sql);
            stat.executeUpdate(sql);
            stat.close();
            System.out.println("Updated successful");
            return true;
        } catch (SQLException ex) {
            System.out.println("Updated error");
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteProvider(Provider provider) {
        String sql = "DELETE FROM PROVIDERS WHERE id=" + provider.getId();
        try {
            stat = conn.createStatement();
            stat.execute(sql);
            System.out.println("Deleting provider -----------------> " + sql);
            stat.close();
            System.out.println("Deleted successful");
            return true;
        } catch (SQLException ex) {
            System.out.println("Deleting error");
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
