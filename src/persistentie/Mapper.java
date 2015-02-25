package persistentie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        return this.selectQuery(selectQuery, (Object) null);
    }
    
    /**
     * Execute a select query with params.
     * 
     * @param selectQuery
     * @param args
     * @return
     * @throws SQLException 
     */
    public ResultSet selectQuery(String selectQuery, Object... args) throws SQLException
    {
        PreparedStatement query = this.getConnection().prepareStatement(selectQuery);
        
        for (int i = 0; i < args.length; i++)
        {
            query.setObject(i + 1, args[i]);
        }
        
        return query.executeQuery();
    }
    
    /**
     * Execute an insert query.
     * 
     * @param insertQuery
     * @param args
     * @throws SQLException 
     */
    public void insertQuery(String insertQuery, Object... args) throws SQLException
    {
        this.executeQuery(insertQuery, args);
    }
    
    /**
     * Executte an update query.
     * 
     * @param updateQuery
     * @param args
     * @throws SQLException 
     */
    public void updateQuery(String updateQuery, Object... args) throws SQLException
    {
        this.executeQuery(updateQuery, args);
    }
    
    /**
     * Execute a query.
     * 
     * @param query
     * @param args
     * @throws SQLException 
     */
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
