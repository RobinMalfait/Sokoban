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
     * @param gebruikersnaam
     * @return 
     */
    public Speler geefSpeler(String gebruikersnaam)
    {
        try
        {
            ResultSet rs = selectQuery("SELECT * FROM Speler WHERE gebruikersnaam = '" + gebruikersnaam + "'");
            return verkrijgSpelers(rs).get(0);
        } catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public List<Speler> geefSpelers()
    {
        try
        {
            List<Speler> spelers = new ArrayList<>();
            
            ResultSet rs = this.selectQuery("SELECT * FROM Speler");
            
            return verkrijgSpelers(rs);
        } catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<>();
    }
    
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
