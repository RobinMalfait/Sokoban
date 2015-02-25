package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    private static final String DB_PASS = "Sokoban123";

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

    /**
     * Get a connection.
     * 
     * @return Connection
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(Connectie.JDBC_URL);
    }
    
}
