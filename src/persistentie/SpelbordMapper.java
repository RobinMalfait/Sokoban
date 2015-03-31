/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Spelbord;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Demian
 */
public class SpelbordMapper extends Mapper
{

    /**
     * Geef een lijst van spelborden op basis van spel nummer
     * 
     * @param id int
     * @return List&lt;Spelbord&gt;
     */
    public List<Spelbord> geefSpelborden(int id)
    {
        try
        {
            ResultSet rs = selectQuery("SELECT * FROM Spelbord WHERE spel_id = ? ORDER BY nummer ASC", id);

            List<Spelbord> spelbord = verkrijgSpelborden(rs);

            if (!spelbord.isEmpty())
            {
                return spelbord;
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Geef een spelbord terug op basis van spelbord nummer
     * 
     * @param id, int spelId int
     * @return Spelbord
     */
    public Spelbord geefSpelbord(int id, int spelId)
    {
        try
        {
            ResultSet rs = selectQuery("SELECT * FROM Spelbord WHERE id = ? AND spelbord_id = ?", id, spelId);

            List<Spelbord> spelbord = verkrijgSpelborden(rs);

            if (!spelbord.isEmpty())
            {
                return spelbord.get(0);
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Verkrijg spelborden
     * 
     * @param rs ResultSet
     * @return List&lt;Spelbord&gt;
     * @throws SQLException 
     */
    private List<Spelbord> verkrijgSpelborden(ResultSet rs) throws SQLException
    {
        List<Spelbord> spelborden = new ArrayList<>();

        while (rs.next())
        {
            int id = rs.getInt("id");
            String naam = rs.getString("naam");
                
            spelborden.add(new Spelbord(id, naam));
        }

        return spelborden;
    }
    
    public int voegSpelbordToe(String naam, int spelId)
    {
        try {
            return insertQuery("INSERT INTO Spelbord (naam, spel_id, nummer) VALUES (?, ?, 3)", naam, spelId);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; 
    }     
    
    public void verwijderSpelbord(int spelbordId)
    {
        try {
            deleteQuery("DELETE FROM Spelbord WHERE id = ?", spelbordId);
            deleteQuery("DELETE FROM Vak WHERE spelbord_id = ?", spelbordId);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
