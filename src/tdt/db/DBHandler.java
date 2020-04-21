package tdt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tdt.db.dao.MappedFileDAO;
import tdt.model.MappedFileModel;
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
    private static final String TABLE_NAME_PROVIDERS = "PROVIDERS";
    private static final String TABLE_NAME_MAPPED = "MAPPED_FILE";
    private static Connection conn;
    private static Statement stat;

    public DBHandler() {
        connect();
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

    public void createTableMappedFile() {

        String sql = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME_MAPPED
                + "(id bigint auto_increment NOT NULL PRIMARY KEY, "
                + " key VARCHAR2(255),"
                + "start INT, "
                + "end INT)";
        try {

            stat = conn.createStatement();
            System.out.println("Creating table Mapped_File ----------> " + sql);
            stat.execute(sql);
            System.out.println("Create table Mapped_File successful");
            stat.close();
            conn.commit();
        } catch (SQLException ex) {
            System.out.println("ERROR => Create table Mapped_File");
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static HashMap<String, MappedFileModel> getMappedFileData() {
        String sql = " SELECT * FROM " + TABLE_NAME_MAPPED;
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
            System.out.println("Get Mapped_File Data successful");
            stat.close();
        } catch (SQLException ex) {
            System.out.println("Error getting providers list");
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static ResultSet getMappedFileDataTest() {
        ResultSet r = null;
        String sql = " SELECT * FROM " + TABLE_NAME_MAPPED;
        ObservableList<MappedFileModel> list = FXCollections.observableArrayList();
        try {
            stat = conn.createStatement();
            r = stat.executeQuery(sql);
            System.out.println(r);
            System.out.println("Get Mapped_File Data successful");
            stat.close();
        } catch (SQLException ex) {
            System.out.println("Error getting providers list");
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public static boolean updateMappedFileData(MappedFileModel data) {
        String sql = "UPDATE " + TABLE_NAME_MAPPED + " SET "
                + "key='" + data.getKey() + "', start='" + data.getStart() + "', end='" + data.getEnd() + "'";
        try {
            stat = conn.createStatement();
            System.out.println("Update MappedFileData ---------------> " + sql);
            stat.executeUpdate(sql);
            System.out.println("Updated successful");
            stat.close();
            conn.commit();
            return true;
        } catch (SQLException ex) {
            System.out.println("Updated error");
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void insertMappedData() {
        try {
            stat = conn.createStatement();
            String sql = "MERGE INTO " + TABLE_NAME_MAPPED + " KEY(key) VALUES"
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
            stat.close();
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(MappedFileDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createTableProviders() {
        String sql = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME_PROVIDERS
                + "(id bigint auto_increment NOT NULL PRIMARY KEY,"
                + "name VARCHAR2(255),"
                + "cif VARCHAR2(255))";
        try {
            stat = conn.createStatement();
            System.out.println("Creating table Providers ----------> " + sql);
            stat.execute(sql);
            System.out.println("Create table Providers successful");
            stat.close();
            conn.commit();
        } catch (SQLException ex) {
            System.out.println("ERROR => Create table PROVIDERS");
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ObservableList<Provider> getProviders() {
        String sql = " SELECT * FROM " + TABLE_NAME_PROVIDERS;
        ObservableList<Provider> list = FXCollections.observableArrayList();
        try {
            stat = conn.createStatement();
            ResultSet result = stat.executeQuery(sql);
            System.out.println("R providers: " + result);
            System.out.println("Getting providers ------------>" + sql);

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String cif = result.getString("cif");
                list.add(new Provider(id, name, cif));
            }
            System.out.println("Get providers successful");
            stat.close();
        } catch (SQLException ex) {
            System.out.println("Error getting providers list");
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static Provider getProvider(int providerId) {
        String sql = "SELECT * FROM " + TABLE_NAME_PROVIDERS + " WHERE id=" + providerId;
        try {
            stat = conn.createStatement();
            ResultSet result = stat.executeQuery(sql);
            System.out.println("Getting provider ----------> " + sql);

            result.next();
            int id = result.getInt("id");
            String name = result.getString("name");
            String cif = result.getString("cif");
            Provider prov = new Provider(id, name, cif);

            System.out.println("Get provider successful");
            stat.close();
            return prov;
        } catch (SQLException ex) {
            System.out.println("Error getting provider");
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static int addProvider(Provider p) {
        String sql = "INSERT INTO " + TABLE_NAME_PROVIDERS + " (name, cif) VALUES('"
                + p.getName() + "', '" + p.getCif() + "')";
        try {
            stat = conn.createStatement();
            System.out.println("Insert provider -----------> " + sql);
            stat.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = stat.getGeneratedKeys();
            result.next();
            int id = result.getInt("id");
            System.out.println("Insert successful");
            stat.close();
            conn.commit();
            return id;
        } catch (SQLException ex) {
            System.out.println("Insert error");
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static boolean updateProvider(Provider p) {
        String sql = "UPDATE " + TABLE_NAME_PROVIDERS + "  SET "
                + "name='" + p.getName() + "', cif='" + p.getCif() + "'";
        try {
            stat = conn.createStatement();
            System.out.println("Update provider ---------------> " + sql);
            stat.executeUpdate(sql);
            System.out.println("Updated successful");
            stat.close();
            conn.commit();
            return true;
        } catch (SQLException ex) {
            System.out.println("Updated error");
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteProvider(Provider provider) {
        String sql = "DELETE FROM " + TABLE_NAME_PROVIDERS + " WHERE id=" + provider.getId();
        try {
            stat = conn.createStatement();
            stat.execute(sql);
            System.out.println("Deleting provider -----------------> " + sql);
            System.out.println("Deleted successful");
            stat.close();
            conn.commit();
            return true;
        } catch (SQLException ex) {
            System.out.println("Deleting error");
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
