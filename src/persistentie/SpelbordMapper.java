/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Spelbord;
import domein.Speler;
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

    public List<Spelbord> geefSpelborden()
    {
        try
        {
            ResultSet rs = selectQuery("SELECT * FROM Spelbord ORDER BY posX ASC, posY ASC");

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

    public Spelbord geefSpelbord(int spelbordnummer)
    {
        try
        {
            ResultSet rs = selectQuery("SELECT * FROM Spelbord WHERE volgorde = ?", spelbordnummer);

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
}
