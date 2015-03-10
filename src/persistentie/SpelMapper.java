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
    public List<Spel> geefSpellen()
    {
        try
        {
            ResultSet rs = selectQuery("SELECT * FROM Spel");

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
}
