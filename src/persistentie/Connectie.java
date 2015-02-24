package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class Connectie
{
    
    /**
     * Database Name
     */
    private static final String DB_NAME = "demian_sokoban";

    /**
     * Database Username
     */
    private static final String DB_USER = "demian_project";

    /**
     * Database Password
     */
    private static final String DB_PASS = "xF3+]OiN[$9V";

    /**
     * Database Host
     */
    private static final String DB_HOST = "195.238.75.48";

    /**
     * Database Port
     */
    private static final String DB_PORT = "3306";

    /**
     * Database Connection URL
     */
    private static final String JDBC_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?user=" + DB_USER + "&password=" + DB_PASS;


    
    public Connection getConnection()
    {
        try
        {
            return DriverManager.getConnection(Connectie.JDBC_URL);
        } catch (SQLException ex)
        {
            Logger.getLogger(Connectie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
