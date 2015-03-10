    package persistentie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class Mapper extends Connectie
{

    /**
     * Execute a select query.
     * 
     * @param selectQuery String
     * @return ResultSet
     * @throws SQLException SQLException
     */
    public ResultSet selectQuery(String selectQuery) throws SQLException
    {
        return this.selectQuery(selectQuery, (Object) null);
    }
    
    /**
     * Execute a select query with params.
     * 
     * @param selectQuery String
     * @param args Object...
     * @return ResultSet
     * @throws SQLException SQLException
     */
    public ResultSet selectQuery(String selectQuery, Object... args) throws SQLException
    {
        PreparedStatement query = this.getConnection().prepareStatement(selectQuery);

        for (int i = 0; i < args.length; i++)
        {
            if(args[i] != null)
                query.setObject(i + 1, args[i]);
        }
        
        return query.executeQuery();
    }
    
    /**
     * Execute an insert query.
     * 
     * @param insertQuery String
     * @param args Object...
     * @throws SQLException SQLException
     */
    public void insertQuery(String insertQuery, Object... args) throws SQLException
    {
        this.executeQuery(insertQuery, args);
    }
    
    /**
     * Executte an update query.
     * 
     * @param updateQuery String
     * @param args Object...
     * @throws SQLException SQLException
     */
    public void updateQuery(String updateQuery, Object... args) throws SQLException
    {
        this.executeQuery(updateQuery, args);
    }
    
    /**
     * Execute a delete query.
     * 
     * @param deleteQuery String
     * @param args Object...
     * @throws SQLException SQLException
     */
    public void deleteQuery(String deleteQuery, Object... args) throws SQLException
    {
        this.executeQuery(deleteQuery, args);
    }
    
    /**
     * Execute a query.
     * 
     * @param query String
     * @param args Object...
     * @throws SQLException SQLException
     */
    private void executeQuery(String query, Object... args) throws SQLException
    {
        try(Connection conn = this.getConnection()) {
            PreparedStatement qry = conn.prepareStatement(query);
        
            for (int i = 0; i < args.length; i++)
            {
                qry.setObject(i + 1, args[i]);
            }

            qry.executeUpdate();
        } catch (Exception e) {
            
        }
    }

}
