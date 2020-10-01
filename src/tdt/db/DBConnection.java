package tdt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import tdt.services.AlertExceptionService;
import tdt.services.AlertService;
import tdt.services.PropertyService;
import tdt.ui.AppController;

public class DBConnection {

    private static final String TIMEZOME = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static String IP_ADDRESS = "";

    private static final String PORT = "3306";

    private static final String DATABASE_NAME = "tdt_profesional";

    private static final String DB_URL = "jdbc:mysql://";

    private static final String USERNAME = "tdt";

    private static final String PASSWORD = "tdt";

    private static PropertyService props;

    public static AppController app;
    
    public static Connection getConnection() {
        
        props = new PropertyService();

        Object ip = props.getProps("tdt.ip");

        if (ip != null) {

            IP_ADDRESS = ip.toString();

        } else {
            AlertService alert = new AlertService(Alert.AlertType.ERROR, "ERROR DE CONEXIÓN",
                    "No se ha encontrado una dirección ip para conectar a la base de datos", "");

            alert.showAndWait();

        }

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(DB_URL + IP_ADDRESS + ":" + PORT + "/" + DATABASE_NAME + TIMEZOME, USERNAME, PASSWORD);

        } catch (SQLException ex) {
            
            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se ha podido establecer la conexión a la base de datos", ex);

            alert.showAndWait();
        }
        return conn;
    }

}
