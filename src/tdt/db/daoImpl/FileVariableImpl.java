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
import tdt.model.FileVariable;
import tdt.services.AlertExceptionService;
import tdt.db.dao.IFileVariableDao;

public class FileVariableImpl implements IFileVariableDao {

    private final String TABLE_NAME = "VARIABLES_ARCHIVO";

    @Override
    public HashMap<String, FileVariable> hashMapFileVariable() {

        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT * FROM " + TABLE_NAME;

        HashMap<String, FileVariable> list = new HashMap();
        conn = DBConnection.getConnection();
        try {

            stat = conn.createStatement();

            ResultSet result = stat.executeQuery(sql);

            // System.out.println("Obtenifino VARIABLES_ARCHIVO ------------>" + sql);

            while (result.next()) {

                String clave = result.getString("clave");

                int inicio = result.getInt("inicio");

                int fin = result.getInt("fin");

                list.put(clave, new FileVariable(clave, inicio, fin));
            }

            // System.out.println("VARIABLES_ARCHIVO obtenidas");

        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido obtener las variables de archivo", ex);

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
                Logger.getLogger(FileVariableImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public ObservableList<FileVariable> observableFileVariable() {

        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT * FROM " + TABLE_NAME;

        ObservableList<FileVariable> list = FXCollections.observableArrayList();;

        try {

            conn = DBConnection.getConnection();

            stat = conn.createStatement();

            ResultSet result = stat.executeQuery(sql);

            // System.out.println("Obtenifino VARIABLES_ARCHIVO ------------>" + sql);

            while (result.next()) {

                String clave = result.getString("clave");

                int inicio = result.getInt("inicio");

                int fin = result.getInt("fin");

                list.add(new FileVariable(clave, inicio, fin));
            }

            // System.out.println("VARIABLES_ARCHIVO obtenida");

        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido obtener las variables de archivo", ex);

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
                Logger.getLogger(FileVariableImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public boolean updateFileVariable(FileVariable variableArchivo) {

        Connection conn = null;

        Statement stat = null;

        String sql = "UPDATE " + TABLE_NAME + " SET "
                + "`clave`='" + variableArchivo.getKey() + "', `inicio`=" + variableArchivo.getStart()
                + ", `fin`=" + variableArchivo.getEnd() + " WHERE `clave`='" + variableArchivo.getKey() + "'";
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                // System.out.println("Actualizando Variable de archivo ---------------> " + sql);

                stat.executeUpdate(sql);

                // System.out.println("Actualizacion realizada !! ");

                return true;
            }
        } catch (SQLException ex) {

          AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido actualizar la variable de archivo", ex);

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
                Logger.getLogger(FileVariableImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public FileVariable getVariable(String clave) {
        Connection conn = null;

        Statement stat = null;

        String sql = "SELECT inicio, fin FROM " + TABLE_NAME + " WHERE clave='" + clave +"'";

        // System.out.println(sql);
        FileVariable var = null;
        
        conn = DBConnection.getConnection();
        
        try {

            stat = conn.createStatement();

            ResultSet result = stat.executeQuery(sql);

            // System.out.println("Obtenifino VARIABLE ARCHIVO ------------>" + sql);

            while (result.next()) {

                int inicio = result.getInt("inicio");

                int fin = result.getInt("fin");

                var = new FileVariable(clave, inicio, fin);
            }

            // System.out.println("VARIABLE_ARCHIVO obtenida");

        } catch (SQLException ex) {

             AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido obtener la variable de archivo", ex);

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
                Logger.getLogger(FileVariableImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return var;
    }

}
