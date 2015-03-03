/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Muur;
import domein.Toegankelijk;
import domein.Vak;

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
public class VakMapper extends Mapper
{
    // 
    public List<Vak> geefVakken(int spelbordId)
    {
        try 
        {
             ResultSet rs = selectQuery("SELECT * FROM Item WHERE spelbord_id = ?", spelbordId);
             return creerVakken(rs);
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Vak> creerVakken(ResultSet rs) throws SQLException 
    {
        List<Vak> vakken = new ArrayList();
        while(rs.next())
        {
            int type = rs.getInt("type");
            int posX = rs.getInt("posX");
            int posY = rs.getInt("posY");
            
            switch(type) {
                case 0:                                     // Muur
                    vakken.add(new Muur(posX, posY));
                    break;
                case 1:                                     // Leeg Vak - Geen doel
                    vakken.add(new Toegankelijk(posX, posY, false));
                    break;
                case 2:                                     // Leeg Vak - Met Doel
                    vakken.add(new Toegankelijk(posX, posY, true));
                    break;                
            }
        }
        return vakken;
    }
}
