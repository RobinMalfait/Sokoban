package persistentie;

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
    public static final String JDBC_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?user=" + DB_USER + "&password=" + DB_PASS;
}
