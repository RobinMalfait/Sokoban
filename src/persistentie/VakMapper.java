/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Kist;
import domein.Mannetje;
import domein.Vak;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Demian
 */
public class VakMapper extends Mapper
{

    /**
     * Geef vakken op basis van spelbord nummer
     *
     * @param spelbordId int
     * @return Vak[][]
     */
    public Vak[][] geefVakken(int spelbordId)
    {
        try
        {
            ResultSet rs = selectQuery("SELECT * FROM Vak WHERE spelbord_id = ? ORDER BY posX ASC, posY ASC", spelbordId);
            return creerVakken(rs);
        } catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Map database terug naar vakken
     *
     * @param rs ResultSet
     * @return Vak[][]
     * @throws SQLException
     */
    public Vak[][] creerVakken(ResultSet rs) throws SQLException
    {
        Vak[][] vakken = new Vak[10][10];
        while (rs.next())
        {
            int type = rs.getInt("type");
            int posX = rs.getInt("posX");
            int posY = rs.getInt("posY");

            vakken[posX][posY] = maakVakObject(type, posX, posY);
        }
        return vakken;
    }

    /**
     * Voegt alle vakken toe aan een Spelbord in een single-query.
     * 
     * @param vakken
     * @param spelbordId 
     */
    public void voegVakkenToe(Vak vakken[][], int spelbordId)
    {
        // Het maken van slechts 1 query.
        String query = "INSERT INTO Vak (spelbord_id, type, posX, posY) VALUES ";

        // Doordat we de array niet wijzigen, maar toch de index nodig hebben, 2 extra variabelen.
        for (Vak vakArray[] : vakken)
        {
            for (Vak vak: vakArray)
            {
                query += String.format("(%d, %d, %d, %d), ", spelbordId, vak.bepaalDatabaseType(), vak.getPosX(), vak.getPosY());
            }
        }
        
        // De query uitvoeren.
        try {
            insertQuery(query.substring(0, query.length() - 2));
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Wijzigt alle vakken van een Spelbord.
     * 
     * @param vakken
     * @param spelbordId 
     */
    public void wijzigVakken(Vak vakken[][], int spelbordId)
    {
        for(Vak vakArray[]: vakken)
            for(Vak vak: vakArray)
            {
                try {
                   updateQuery("UPDATE Vak SET type = ? WHERE spelbord_id = ? AND posX = ? AND posY = ?", vak.bepaalDatabaseType(), spelbordId, vak.getPosX(), vak.getPosY());
                } 
                catch (SQLException ex)
                {
                   Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
                }               
            }
       // Misschien een idee om maar 1 query uit te voeren?
    }

    /**
     * Maakt een object van het Type vak
     * 
     * @param type
     * @param posX
     * @param posY
     * @return Vak
     */
    private static Vak maakVakObject(int type, int posX, int posY)
    {
        switch (type)
        {
            case 0:                                     // Toegankelijk vak - Leeg vak
                return new Vak(posX, posY, true, false);
            case 1:                                     // Muur
                return new Vak(posX, posY, false, false);
            case 2:                                     // Toegankelijk vak - Met Doel
                return new Vak(posX, posY, true, true);
            case 3:                                     // Toegankelijk vak - Met Kist
                return new Vak(posX, posY, true, false, new Kist());
            case 4:                                     // Toegankelijk vak - Met Mannetje
                return new Vak(posX, posY, true, false, new Mannetje());
        }
        
        return null;
    }
}
