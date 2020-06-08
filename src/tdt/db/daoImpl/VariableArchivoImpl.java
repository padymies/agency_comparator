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

            System.out.println("Obtenifino VARIABLES_ARCHIVO ------------>" + sql);

            while (result.next()) {

                String clave = result.getString("clave");

                int inicio = result.getInt("inicio");

                int fin = result.getInt("fin");

                list.put(clave, new VariableArchivo(clave, inicio, fin));
            }

            System.out.println("VARIABLES_ARCHIVO obtenidas");

        } catch (SQLException ex) {

            System.out.println("Error obtenifino listado de VARIABLES_ARCHIVO");

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

            System.out.println("Obtenifino VARIABLES_ARCHIVO ------------>" + sql);

            while (result.next()) {

                String clave = result.getString("clave");

                int inicio = result.getInt("inicio");

                int fin = result.getInt("fin");

                list.add(new VariableArchivo(clave, inicio, fin));
            }

            System.out.println("VARIABLES_ARCHIVO obtenida");

        } catch (SQLException ex) {

            System.out.println("Error obtenifino listado de VARIABLES_ARCHIVO");

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
                + "`clave`='" + variableArchivo.getKey() + "', `inicio`=" + variableArchivo.getStart()
                + ", `fin`=" + variableArchivo.getEnd() + " WHERE `clave`='" + variableArchivo.getKey() + "'";
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

    @Override
    public VariableArchivo getVariable(String clave) {
        Connection conn = null;

        Statement stat = null;

        String sql = "SELECT inicio, fin FROM " + TABLE_NAME + " WHERE clave='" + clave +"'";

        System.out.println(sql);
        VariableArchivo var = null;
        
        conn = DBConnection.getConnection();
        
        try {

            stat = conn.createStatement();

            ResultSet result = stat.executeQuery(sql);

            System.out.println("Obtenifino VARIABLE ARCHIVO ------------>" + sql);

            while (result.next()) {

                int inicio = result.getInt("inicio");

                int fin = result.getInt("fin");

                var = new VariableArchivo(clave, inicio, fin);
            }

            System.out.println("VARIABLE_ARCHIVO obtenida");

        } catch (SQLException ex) {

            System.out.println("Error obtenido VARIABLE_ARCHIVO");

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
        return var;
    }

}
