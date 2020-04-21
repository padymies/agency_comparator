/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdt.db.dao;

import java.sql.Connection;
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
public final class ProviderDAO {

    private static Connection conn;
    private static Statement stat;
    private final String TABLE_NAME = "PROVIDERS";

    public ProviderDAO(Connection conn) {
        ProviderDAO.conn = conn;
        createTableProviders();
    }

    public void createTableProviders() {
        String sql = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME
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
            Logger.getLogger(ProviderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Provider> getProviders() {
        String sql = " SELECT * FROM " + TABLE_NAME;
        ObservableList<Provider> list = FXCollections.observableArrayList();
        try {
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(sql);
            System.out.println("R providers: " + result);
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
            Logger.getLogger(ProviderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Provider getProvider(int providerId) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=" + providerId;
        try {
            stat = conn.createStatement();
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
            Logger.getLogger(ProviderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int addProvider(Provider p) {
        String sql = "INSERT INTO " + TABLE_NAME + " (name, cif) VALUES('"
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
            Logger.getLogger(ProviderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean updateProvider(Provider p) {
        String sql = "UPDATE " + TABLE_NAME + "  SET "
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
            Logger.getLogger(ProviderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteProvider(Provider provider) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id=" + provider.getId();
        try {
            stat = conn.createStatement();
            stat.execute(sql);
            System.out.println("Deleting provider -----------------> " + sql);
            stat.close();
            System.out.println("Deleted successful");
            return true;
        } catch (SQLException ex) {
            System.out.println("Deleting error");
            Logger.getLogger(ProviderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
