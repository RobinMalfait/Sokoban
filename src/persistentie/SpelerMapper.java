package persistentie;

import domein.Speler;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpelerMapper extends Mapper
{

    /**
     *
     * @param emailadres
     */
    public Speler geefSpeler(String emailadres)
    {
        return null;
    }

    public List<Speler> geefSpelers()
    {
        List<Speler> spelers = new ArrayList<>();
        
        try {
            ResultSet rs = this.selectQuery("SELECT * FROM speler");
            
            while (rs.next())
            {
                int id = rs.getInt("id");
                String naam = rs.getString("naam");
                String voornaam = rs.getString("voornaam");
                String gebruikersnaam = rs.getString("gebruikersnaam");
                String wachtwoord = rs.getString("wachtwoord");
                
                spelers.add(new Speler(id, naam, voornaam, gebruikersnaam, wachtwoord));
            }
            
            return spelers;
        } catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return spelers;
    }
}
