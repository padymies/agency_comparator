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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tdt.db.DBConnection;
import tdt.db.dao.IExclusionesDao;
import tdt.model.Exclusion;

/**
 *
 * @author Usuario
 */
public class ExclusionesImpl implements IExclusionesDao {

    String TABLE_NAME = "exclusiones_postales";

    @Override
    public ObservableList<Exclusion> obtenerExclusiones() {
        Connection conn = null;

        Statement stat = null;

        String sql = "SELECT e.id_exclusion, e.cp, e.inclusion_exclusion, nombre FROM " + TABLE_NAME + " e LEFT JOIN agencias a USING(id_agencia)";

        ObservableList<Exclusion> list = FXCollections.observableArrayList();

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                System.out.println("OBTENIENDO EXCLUSIONES ------------>" + sql);

                while (result.next()) {

                    int id = result.getInt("id_exclusion");

                    String cp = result.getString("cp");

                    String nombreAgencia = result.getString("nombre");

                    int inclusion_exclusion = result.getInt("inclusion_exclusion");

                    list.add(new Exclusion(id, cp, nombreAgencia, inclusion_exclusion));
                }
            }

        } catch (SQLException ex) {

            System.out.println("Error recuperando Exclusiones");

            Logger.getLogger(ExclusionesImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(ExclusionesImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public Exclusion obtenerExclusion(int id) {
        Connection conn = null;

        Statement stat = null;

        Exclusion exclusion = null;

        String sql = "SELECT cp, id_agencia, inclusion_exclusion FROM " + TABLE_NAME + " WHERE id_exclusion=" + id;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                System.out.println("Recuperando exclusion----------> " + sql);

                if (result.next()) {

                    String cp = result.getString("cp");

                    int idAgencia = result.getInt("id_agencia");

                    int inclusion_exclusion = result.getInt("inclusion_exclusion");

                    exclusion = new Exclusion(id, cp, idAgencia, inclusion_exclusion);
                }

            }

            System.out.println("Exclusion recuperada !!");

        } catch (SQLException ex) {

            System.out.println("Error recuperando exclusion");

            Logger.getLogger(ExclusionesImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(ExclusionesImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exclusion;
    }

    @Override
    public int añadirExclusion(Exclusion exclusion) {
        Connection conn = null;

        Statement stat = null;

        int id = -1;

        String sql = "INSERT INTO " + TABLE_NAME + " (cp, id_agencia, inclusion_exclusion) "
                + "VALUES('" + exclusion.getCp() + "', (SELECT id_agencia FROM agencias a WHERE a.nombre='" +
                exclusion.getNombreAgencia() + "'), " + exclusion.getInclusion_exclusion() + ")";
                
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                System.out.println("Insertando exclusion -----------> " + sql);

                stat.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                ResultSet result = stat.getGeneratedKeys();

                if (result.next()) {

                    id = result.getInt(1);

                } else {
                    System.out.println("Error de inserción");
                }

                System.out.println("Exclusion insertada !!");
            }
        } catch (SQLException ex) {

            System.out.println("Error insertando exclusion");

            Logger.getLogger(ExclusionesImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(ExclusionesImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public boolean actualizarExclusiones(Exclusion exclusion) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "UPDATE " + TABLE_NAME + "  SET "
                + "cp='" + exclusion.getCp() + "', id_agencia= " + exclusion.getId_agencia()
                + ", inclusion_exclusion=" + exclusion.getInclusion_exclusion()
                + " WHERE id_exclusion=" + exclusion.getId();
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                System.out.println("Actualizando exclusion---------------> " + sql);

                stat.executeUpdate(sql);

                System.out.println("Exclusion actualizada !!");

                result = true;
            }
        } catch (SQLException ex) {

            System.out.println("Error actualizando exclusion");

            Logger.getLogger(ExclusionesImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(ExclusionesImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public boolean borrarExclusion(int exclusionId) {
        Connection conn = null;

        Statement stat = null;

        boolean result = false;

        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id_exclusion=" + exclusionId;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                stat.execute(sql);

                System.out.println("Eliminando exclusion-----------------> " + sql);

                result = true;

                System.out.println("Exclusion eliminada !!");
            }
        } catch (SQLException ex) {

            System.out.println("Error borrando exclusion");

            Logger.getLogger(ExclusionesImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(ExclusionesImpl.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;

    }

    @Override
    public Exclusion obtenerExclusionPorCP(String cp) {
        Connection conn = null;

        Statement stat = null;

        Exclusion exclusion = null;

        String sql = "SELECT id_exclusion, id_agencia, inclusion_exclusion FROM " + TABLE_NAME + " WHERE cp=" + cp;

        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                System.out.println("Recuperando exclusion----------> " + sql);

                if (result.next()) {

                    int id= result.getInt("id_exclusion");
                    
                    int idAgencia = result.getInt("id_agencia");

                    int inclusion_exclusion = result.getInt("inclusion_exclusion");

                    exclusion = new Exclusion(id, cp, idAgencia, inclusion_exclusion);
                }

            }

            System.out.println("Exclusion recuperada !!");

        } catch (SQLException ex) {

            System.out.println("Error recuperando exclusion");

            Logger.getLogger(ExclusionesImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(ExclusionesImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exclusion;
    }

}
