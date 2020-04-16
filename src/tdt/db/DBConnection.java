/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class  DBConnection {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./resources/tdt";
    private static final String USERNAME = "tdt";
    private static final String PASSWORD = "tdt";
    private static Connection conn;

    
    
    
    private static final String CREATE_TABLE = " CREATE TABLE IF NOT EXISTS PROVIDERS"
            + "(id bigint auto_increment NOT NULL PRIMARY KEY,"
            + "name VARCHAR2(255),"
            + "cif VARCHAR2(255))";

    private static final String INSERT_PROVIDER = " INSERT INTO PROVIDERS"
            + " (name,cif) VALUES('DHL', '123')";
    private static final String SELECT_PROVIDERS = " SELECT * FROM PROVIDERS";

    
    private static Connection openConnection() {
         try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Open Connection...");
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stat = conn.createStatement();
            stat.execute(CREATE_TABLE);
            stat.close();
        } catch (Exception ex) {
            System.out.println("Error opening connection");
            ex.getMessage();
        }
         return conn;
    }
    
    private static void CloseConnection() {
        try {
            conn.commit();
            conn.close();
            System.out.println("Database Closed");
        } catch (SQLException ex) {
            System.out.println("Error closing connection");
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ObservableList<Provider> getProviders() {
        openConnection();
        ObservableList<Provider> list = FXCollections.observableArrayList();
        try {
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(SELECT_PROVIDERS);

            while (result.next()) {

                int id = result.getInt("id");
                String name = result.getString("name");
                String cif = result.getString("cif");
                list.add(new Provider(id, name, cif));
                System.out.println("RESULTADO: " + list);
            }
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        CloseConnection();
        return list;
    }
    
    public static int addProvider(Provider p) {
        try {
            openConnection();
            Statement stat = conn.createStatement();
            stat.executeUpdate("INSERT INTO PROVIDERS (name, cif) VALUES('"
                    + p.getName()+"', '"+ p.getCif() +"')", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stat.getGeneratedKeys();
            rs.next();
            int id = rs.getInt("id");
            System.out.println(id);
            stat.close();
            return id;
            } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        CloseConnection();
        return 0;
    }
     public static boolean deleteProvider(Provider provider) {
        try {
            openConnection();
            Statement stat = conn.createStatement();
            // TODO: Write query
            stat.execute("DELETE FROM PROVIDERS WHERE id=" + provider.getId());
            stat.close();
            return true;
            } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        CloseConnection();
        return false;
    }

}
