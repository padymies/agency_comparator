package tdt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import tdt.services.MyLogger;
import tdt.ui.AppController;

/**
 *
 * @author Usuario
 */
public class DBConnection {

    private static final String TIMEZOME = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    private static final String IP_ADDRESS= "192.168.180.175";
    
    private static final String PORT = "3306";
    
    private static final String DATABASE_NAME = "tdt_profesional";
    
    private static final String DB_URL = "jdbc:mysql://" + IP_ADDRESS + ":" + PORT + "/"+ DATABASE_NAME;
    
    private static final String USERNAME = "pady";
    
    private static final String PASSWORD = "pady";
    

    public static AppController app;

    public static Connection getConnection() {
        Connection conn = null;
        try {

            System.out.println("Pady: Connectando a la base de datos ...");

            conn = DriverManager.getConnection(DB_URL + TIMEZOME, USERNAME, PASSWORD);
            
            MyLogger.writeLog("Conectando a la base de datos...", MyLogger.Severity.INFO);

            if (conn != null) {

                System.out.println("Conexión establecida !!");

                MyLogger.writeLog("Conexión establecida", MyLogger.Severity.SUCCESS);

            }
        } catch (SQLException ex) {

            System.out.println("ERROR CONECTANDO A LA BASE DE DATOS " + ex.getMessage());

            MyLogger.writeLog("Error de conexión a la base de datos ... ", MyLogger.Severity.ERROR);

            ex.getMessage();
        }
        return conn;
    }

}
