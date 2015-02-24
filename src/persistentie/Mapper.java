package persistentie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    public ResultSet selectQuery(String selectQuery) throws SQLException
    {
        Connection conn = this.getConnection();
        
        try
        {
            PreparedStatement query = conn.prepareStatement(selectQuery);

            return query.executeQuery();

        } catch (SQLException ex)
        {
            Logger.getLogger(Mapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
        
    }  

}
