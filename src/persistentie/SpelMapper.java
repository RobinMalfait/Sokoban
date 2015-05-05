/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Spel;
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
public class SpelMapper extends Mapper
{
    /**
     * Geef een lijst van spellen
     * 
     * @return List&lt;Spel&gt;
     */
    public List<Spel> geefSpellen()
    {
        try
        {
            ResultSet rs = selectQuery("SELECT * FROM Spel ORDER BY nummer ASC");

            List<Spel> spellen = verkrijgSpellen(rs);

            if (!spellen.isEmpty())
            {
                return spellen;
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;        
    }
    
    /**
     * Geef een Spel met een specifiek id 
     * 
     * @param id
     * @return Spel
     */
    public Spel geefSpel(int id)
    {
        try
        {
            ResultSet rs = selectQuery("SELECT * FROM Spel WHERE id = ?", id);

            List<Spel> spellen = verkrijgSpellen(rs);

            if (!spellen.isEmpty())
            {
                return spellen.get(0);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;        
    }
    /**
     * Verkrijg spellen
     * 
     * @param rs ResultSet
     * @return List&lt;Spel&gt;
     * @throws SQLException 
     */
    private List<Spel> verkrijgSpellen(ResultSet rs) throws SQLException
    {
        List<Spel> spellen = new ArrayList<>();

        while (rs.next())
        {
            int id = rs.getInt("id");
            String naam = rs.getString("naam");
            
            spellen.add(new Spel(id, naam));
        }

        return spellen;
    }
    
    /**
     * Voeg een spel toe
     * 
     * @param naam
     * @return int
     */
    public int voegSpelToe(String naam)
    {
        try {
            return insertQuery("INSERT INTO Spel (naam, nummer) VALUES (?, 3)", naam);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; 
    }            

    public void verwijderSpel(int spelId)
    {
        try {
            deleteQuery("DELETE FROM Spel WHERE id = ?", spelId);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
}
