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
import tdt.db.DBConnection;
import tdt.db.dao.IAppConfig;

/**
 *
 * @author Usuario
 */
public class AppConfigImpl implements IAppConfig {

    private String TABLE_NAME = "configuracion_app";
    
    @Override
    public double getPorcentajeUrgencia() {
        
        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT porcentaje_urgencia FROM " + TABLE_NAME;

        double porcentaje = 0;
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                // System.out.println("OBTENIENDO AGENCIAS ------------>" + sql);

                while (result.next()) {

                   porcentaje = result.getDouble("porcentaje_urgencia");

                }
            }

        } catch (SQLException ex) {

            // System.out.println("Error recuperando Agencias");

            Logger.getLogger(AppConfigImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(AppConfigImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return porcentaje;
    }

    @Override
    public boolean actualizarPorcentajeUrgencia(double porcentaje) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "UPDATE " + TABLE_NAME + "  SET "
                + "porcentaje_urgencia=" + porcentaje;
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                // System.out.println("Actualizando agencia ---------------> " + sql);

                stat.executeUpdate(sql);

                // System.out.println("Agencia actualizada !!");

                result = true;
            }
        } catch (SQLException ex) {

            // System.out.println("Error actualizando agencia");

            Logger.getLogger(AppConfigImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(AppConfigImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public boolean borrarPorcentajeUrgencia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPassAdmin() {
         Connection conn = null;

        Statement stat = null;

        String sql = " SELECT pass_admin FROM " + TABLE_NAME;

        String pass = null;
        
        try {

            conn = DBConnection.getConnection();
            
            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                while (result.next()) {

                   pass = result.getString("pass_admin");

                }
            }

        } catch (SQLException ex) {


            Logger.getLogger(AppConfigImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(AppConfigImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pass;
    }

    @Override
    public boolean actualizarPassAdmin(String pass) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "UPDATE " + TABLE_NAME + "  SET "
                + "pass_admin=" + pass;
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.executeUpdate(sql);

                result = true;
            }
        } catch (SQLException ex) {

            Logger.getLogger(AppConfigImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(AppConfigImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
}
