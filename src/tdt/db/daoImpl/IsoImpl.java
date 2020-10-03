
package tdt.db.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import tdt.db.DBConnection;
import tdt.db.dao.IsoDao;
import tdt.services.AlertExceptionService;


public class IsoImpl implements IsoDao{

    private String TABLE_NAME = "isos";
    
    
    
    @Override
    public HashMap<String,String> getIsos() {
        
        HashMap<String,String> isos = new HashMap<>();
        
        Connection conn = null;

        Statement stat = null;

        String sql = " SELECT * FROM " + TABLE_NAME;

        
        try {

            conn = DBConnection.getConnection();

            if (conn != null) {

                stat = conn.createStatement();

                ResultSet result = stat.executeQuery(sql);

                while (result.next()) {

                    String key = result.getString("key");
                    String value = result.getString("value");

                    isos.put(key, value);
                }
            }

        } catch (SQLException ex) {

            AlertExceptionService alert = new AlertExceptionService("Conexión a base de datos", "No se han podido obtener los Isos de loas paises", ex);

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
                Logger.getLogger(IsoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return isos;
    }
    
}
