package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class Mapper extends Connectie
{

    /**
     * Execute a select query.
     * 
     * @param selectQuery
     * @return 
     */
    public ResultSet selectQuery(String selectQuery)
    {
        try
        {
            Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
            
            PreparedStatement query = conn.prepareStatement(selectQuery);

            return query.executeQuery();
            
        } catch (SQLException ex)
        {
            Logger.getLogger(Mapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

}
