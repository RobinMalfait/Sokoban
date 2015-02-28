package persistentie;

import domein.Speler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpelerMapper extends Mapper
{

    /**
     * Geef een speler terug.
     * 
     * @param gebruikersnaam String
     * @return Speler | null
     */
    public Speler geefSpeler(String gebruikersnaam)
    {
        try
        {
            ResultSet rs = selectQuery("SELECT * FROM Speler WHERE gebruikersnaam = ?", gebruikersnaam);
            
            List<Speler> spelers = verkrijgSpelers(rs);
            
            if ( ! spelers.isEmpty()) {
                return spelers.get(0);
            }
            
        } catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    /**
     * Geef een lijst van spelers terug.
     * 
     * @return List&lt;Speler&gt;
     */
    public List<Speler> geefSpelers()
    {
        try
        {
            ResultSet rs = this.selectQuery("SELECT * FROM Speler");
            
            return verkrijgSpelers(rs);
        } catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<>();
    }
    
    /**
     * Voeg een speler toe aan de database;
     * 
     * @param speler Speler
     */
    public void addSpeler(Speler speler)
    {
        try {
            
            this.insertQuery("INSERT INTO Speler (gebruikersnaam, wachtwoord, naam, voornaam) VALUES (?, ?, ?, ?)",
                speler.getGebruikersnaam(),
                speler.getWachtwoord(),
                speler.getNaam(),
                speler.getVoornaam()
            );
            
        } catch (SQLException ex) {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Verkrijg een lijst van spelers.
     * 
     * @param rs ResultSet
     * @return  List&lt;Speler&gt;
     * @throws SQLException 
     */
    private List<Speler> verkrijgSpelers(ResultSet rs) throws SQLException
    {
        List<Speler> spelers = new ArrayList<>();
        
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
    }

}
