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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tdt.db.DBHandler;
import tdt.model.MappedFileModel;

/**
 *
 * @author Usuario
 */
public final class MappedFileDAO {
    
    private static Connection conn;
    private static Statement stat;
    
    private final String TABLE_NAME = "MAPPED_FILE";
    
    public MappedFileDAO(Connection conn) {
        MappedFileDAO.conn = conn;
        createTableMappedFile();
        insertData();
    }
    
    public void createTableMappedFile() {
        String sql = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "(id bigint auto_increment NOT NULL PRIMARY KEY, "
                + " key VARCHAR2(255),"
                + "start INT, "
                + "end INT)";
        try {
            stat = conn.createStatement();
            System.out.println("Creating table Mapped_File ----------> " + sql);
            stat.execute(sql);
            System.out.println("Create table Mapped_File successful");
            insertData();
            
        } catch (SQLException ex) {
            System.out.println("ERROR => Create table Mapped_File");
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public HashMap<String, MappedFileModel> getMappedFileData() {
        String sql = " SELECT * FROM " + TABLE_NAME;
        HashMap<String, MappedFileModel> list = new HashMap();
        try {
            stat = conn.createStatement();
            ResultSet result = stat.executeQuery(sql);
            System.out.println("Getting Mapped_File ------------>" + sql);
            
            while (result.next()) {
                String key = result.getString("key");
                int start = result.getInt("start");
                int end = result.getInt("end");
                list.put(key, new MappedFileModel(key, start, end));
            }
            stat.close();
            System.out.println("Get Mapped_File Data successful");
        } catch (SQLException ex) {
            System.out.println("Error getting providers list");
            Logger.getLogger(MappedFileDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ResultSet getMappedFileDataTest() {
        ResultSet r = null;
        String sql = " SELECT * FROM " + TABLE_NAME;
        ObservableList<MappedFileModel> list = FXCollections.observableArrayList();
        try {
            stat = conn.createStatement();
            r = stat.executeQuery(sql);
            System.out.println(r);
            stat.close();
            System.out.println("Get Mapped_File Data successful");
        } catch (SQLException ex) {
            System.out.println("Error getting providers list");
            Logger.getLogger(MappedFileDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    public boolean updateMappedFileData(MappedFileModel data) {
        String sql = "UPDATE " + TABLE_NAME + " SET "
                + "key='" + data.getKey() + "', start='" + data.getStart() + "', end='" + data.getEnd() + "'";
        try {
            stat = conn.createStatement();
            System.out.println("Update MappedFileData ---------------> " + sql);
            stat.executeUpdate(sql);
            stat.close();
            System.out.println("Updated successful");
            return true;
        } catch (SQLException ex) {
            System.out.println("Updated error");
            Logger.getLogger(MappedFileDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void insertData() {
        try {
            stat= conn.createStatement();
            
            String sql =  "MERGE INTO " + TABLE_NAME + " KEY(key) VALUES"
                    + "('CLIENTE', 1, 5),"
                    + "('DEPARTAMENTO', 6, 10),"
                    + "('REF', 11, 40),"
                    + "('FECHA', 41, 48),"
                    + "('TIPO_SERV', 49, 52),"
                    + "('VARIANTE', 53, 53),"
                    + "('NOMBRE_REM', 54, 83),"
                    + "('DIRECC_REM', 84, 113),"
                    + "('POBLACION_REM', 114, 143),"
                    + "('NOMBRE_DESTINO', 144, 184),"
                    + "('DIRECC_DESTINO', 185, 283),"
                    + "('VIA_DESTINO', 284, 286),"
                    + "('NUMERO_DESTINO', 287, 296),"
                    + "('PISO_DESTINO', 297, 298),"
                    + "('TFNO_DESTINO', 299, 310),"
                    + "('POBLACION_DESTINO', 311, 350),"
                    + "('POSTAL_DESTINO', 351, 355),"
                    + "('BULTOS', 356, 358),"
                    + "('DOCUMENTOS', 359, 361),"
                    + "('PAQUETES', 362, 364),"
                    + "('ANCHO', 365, 367),"
                    + "('ALTO', 368, 370),"
                    + "('LARGO', 371, 373),"
                    + "('PESO', 374, 385),"
                    + "('REEMBOLSO', 386, 397),"
                    + "('VALOR', 398, 409),"
                    + "('CUENTA_CLIENTE', 410, 421),"
                    + "('MONEDA', 422, 422),"
                    + "('OBSERVACIONES', 423, 492),"
                    + "('SABADO', 493, 493),"
                    + "('HORA_ENTRADA', 494, 498),"
                    + "('RETORNO', 499, 499),"
                    + "('GESTION_DESTINO', 500, 500),"
                    + "('PORTES_DEBIDOS', 501, 501),"
                    + "('FORMA_PAGO', 502, 504),"
                    + "('EMAIL', 505, 554),"
                    + "('PAIS', 555, 594),"
                    + "('GLS', 595, 600)";
            stat = conn.createStatement();
            boolean s = stat.execute(sql);
            System.out.println(s);
        } catch (SQLException ex) {
            Logger.getLogger(MappedFileDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
