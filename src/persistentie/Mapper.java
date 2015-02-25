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
        try
        {
            PreparedStatement query = this.getConnection().prepareStatement(selectQuery);
            return query.executeQuery();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Mapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }  
    
    public void insertQuery(String insertQuery, Object... args) throws SQLException
    {
        this.executeQuery(insertQuery, args);
    }
    
    public void updateQuery(String updateQuery, Object... args) throws SQLException
    {
        this.executeQuery(updateQuery, args);
    }
    
    private void executeQuery(String query, Object... args) throws SQLException
    {
        PreparedStatement qry = this.getConnection().prepareStatement(query);
        
        for (int i = 0; i < args.length; i++)
        {
            qry.setObject(i + 1, args[i]);
        }
        
        qry.executeUpdate();
    }
}
