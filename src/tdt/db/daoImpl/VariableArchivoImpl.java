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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tdt.db.DBConnection;
import tdt.db.dao.IVariableArchivoDao;
import tdt.model.VariableArchivo;

/**
 *
 * @author Usuario
 */
public class VariableArchivoImpl implements IVariableArchivoDao {

    private final String TABLE_NAME = "VARIABLES_ARCHIVO";

    @Override
    public HashMap<String, VariableArchivo> HashMapVariableArchivo() {

        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT * FROM " + TABLE_NAME;

        HashMap<String, VariableArchivo> list = new HashMap();
        conn = DBConnection.getConnection();
        try {

            stat = conn.createStatement();

            ResultSet result = stat.executeQuery(sql);

            System.out.println("Obteniendo VARIABLES_ARCHIVO ------------>" + sql);

            while (result.next()) {

                String key = result.getString("key");

                int start = result.getInt("start");

                int end = result.getInt("end");

                list.put(key, new VariableArchivo(key, start, end));
            }

            System.out.println("Get VARIABLES_ARCHIVO successful");

        } catch (SQLException ex) {

            System.out.println("Error obteniendo listado de VARIABLES_ARCHIVO");

            Logger.getLogger(VariableArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VariableArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public ObservableList<VariableArchivo> ObservableVariableArchivo() {

        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT * FROM " + TABLE_NAME;

        ObservableList<VariableArchivo> list = FXCollections.observableArrayList();;

        try {

            conn = DBConnection.getConnection();

            stat = conn.createStatement();

            ResultSet result = stat.executeQuery(sql);

            System.out.println("Obteniendo VARIABLES_ARCHIVO ------------>" + sql);

            while (result.next()) {

                String key = result.getString("key");

                int start = result.getInt("start");

                int end = result.getInt("end");

                list.add(new VariableArchivo(key, start, end));
            }

            System.out.println("Get VARIABLES_ARCHIVO successful");

        } catch (SQLException ex) {

            System.out.println("Error obteniendo listado de VARIABLES_ARCHIVO");

            Logger.getLogger(VariableArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VariableArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public boolean actualizarVariableArchivo(VariableArchivo variableArchivo) {

        Connection conn = null;

        Statement stat = null;

        String sql = "UPDATE " + TABLE_NAME + " SET "
                + "key='" + variableArchivo.getKey() + "', start='" + variableArchivo.getStart()
                + "', end='" + variableArchivo.getEnd() + "'";
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                System.out.println("Actualizando Variable de archivo ---------------> " + sql);

                stat.executeUpdate(sql);

                System.out.println("Actualizacion realizada !! ");

                return true;
            }
        } catch (SQLException ex) {

            System.out.println("Error actualizando Variable de archivo");

            Logger.getLogger(VariableArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VariableArchivoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

}
