package domein;

import java.sql.SQLException;
import java.util.*;
import persistentie.SpelerMapper;

public class SpelerRepository
{
    private final SpelerMapper spelerMapper; 
    private List<Speler> spelers;

    public SpelerRepository()
    {
        this.spelerMapper = new SpelerMapper();
    }

    /**
     * 
     * 
     * @param gebruikersnaam
     * @param wachtwoord
     */
    public Speler meldAan(String gebruikersnaam, String wachtwoord)
    {
        // Speler zoeken uit de SpelerMapper
        Speler speler = spelerMapper.geefSpeler(gebruikersnaam);
        
        // Controleer of het wachtwoord van de speler overeenkomt met die uit de parameter
        if (speler.getWachtwoord().equals(wachtwoord)) {
            return speler;
        }
        else {
            return null;
        }
        
        
    }

}
