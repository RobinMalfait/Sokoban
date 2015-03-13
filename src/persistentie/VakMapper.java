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
             ResultSet rs = selectQuery("SELECT * FROM Item WHERE spelbord_id = ? ORDER BY posX ASC, posY ASC", spelbordId);
             return creerVakken(rs);
        } 
        catch (SQLException ex)
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
        while(rs.next())
        {
            int type = rs.getInt("type");
            int posX = rs.getInt("posX");
            int posY = rs.getInt("posY");
            
            switch(type) {
                case 0:                                     // Muur
                    vakken[posX][posY] = new Vak(posX, posY, false, false);
                    break;
                case 1:                                     // Toegankelijk vak - Leeg vak
                    vakken[posX][posY] = new Vak(posX, posY, true, false);
                    break;
                case 2:                                     // Toegankelijk vak - Met Doel
                    vakken[posX][posY] = new Vak(posX, posY, true, true);
                    break;       
                case 3:                                     // Toegankelijk vak - Met Kist
                    vakken[posX][posY] = new Vak(posX, posY, true, false, new Kist());
                    break;
                case 4:                                     // Toegankelijk vak - Met Mannetje
                    vakken[posX][posY] = new Vak(posX, posY, true, false, new Mannetje());
                    break;
                // Nu nog of er een kist/speler op staat.
            }
        }
        return vakken;
    }
}
