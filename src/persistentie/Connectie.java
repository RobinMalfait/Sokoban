package persistentie;

abstract class Connectie
{
    
    /**
     * Database Name
     */
    private static final String DB_NAME = "p1g03";

    /**
     * Database Username
     */
    private static final String DB_USER = "root";

    /**
     * Database Password
     */
    private static final String DB_PASS = "root";

    /**
     * Database Host
     */
    private static final String DB_HOST = "localhost";

    /**
     * Database Port
     */
    private static final String DB_PORT = "3306";

    /**
     * Database Connection URL
     */
    public static final String JDBC_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?user=" + DB_USER + "&password=" + DB_PASS;
}
