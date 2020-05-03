package tdt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import tdt.main.AppController;

/**
 *
 * @author Usuario
 */
public class DBConnection {

    private static final String TIMEZOME = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    ;
    private static final String DB_URL = "jdbc:mysql://192.168.180.149:3306/tdt_profesional";
    private static final String USERNAME = "pady";
    private static final String PASSWORD = "pady";
    
    public static AppController app;
    

    public static Connection getConnection() {
        Connection conn = null;
        try {
            System.out.println("Pady: Connectando a la base de datos ...");
            conn = DriverManager.getConnection(DB_URL + TIMEZOME, USERNAME, PASSWORD);
            if (conn != null) {
                System.out.println("Conexión exitosa !!");
                

            }
        } catch (SQLException ex) {
            System.out.println("ERROR CONECTANDO A LA BASE DE DATOS " + ex.getMessage());
            ex.getMessage();
        }
        return conn;
    }

}
