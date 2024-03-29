package domein;

import exceptions.GebruikersnaamException;
import java.util.*;
import persistentie.SpelerMapper;
import security.BCrypt;

public class SpelerRepository extends Base
{
    
    private final SpelerMapper spelerMapper;
    private final List<Speler> spelers;

    /**
     * Maak een nieuw spelerRepository-object aan
     */
    public SpelerRepository()
    {
        this.spelers = new ArrayList<>();
        this.spelerMapper = new SpelerMapper();
    }

    /**
     * Zoek een speler via de gebruikersnaam en het wachtwoord.
     *
     * @param gebruikersnaam String
     * @param wachtwoord String
     * @return Speler | null
     */
    public Speler zoekSpelerViaGebruikersnaamWachtwoord(String gebruikersnaam, String wachtwoord)
    {
        // Speler zoeken uit de SpelerMapper
        Speler speler = this.spelerMapper.geefSpeler(gebruikersnaam);
        
        // Als er geen speler gevonden werd in de repository
        if(speler == null)
            return null;
        
        // Controleer of het wachtwoord van de speler overeenkomt met die uit de parameter
        return BCrypt.checkpw(wachtwoord, speler.getWachtwoord()) ? speler : null;
        
    }

    /**
     * Voeg een speler toe
     * 
     * @param speler Speler
     */
    public void voegToe(Speler speler)
    {
        if (bestaatSpeler(speler.getGebruikersnaam()))
            throw new GebruikersnaamException(lang.get("user.username.exists"));

        this.spelers.add(speler);
        this.spelerMapper.addSpeler(speler);
    }
    
    /**
     * Controleer of er al een speler bestaat met deze gebruikersnaam.
     * 
     * @param gebruikersnaam String
     * @return boolean
     */
    private boolean bestaatSpeler(String gebruikersnaam)
    {
        return spelerMapper.geefSpeler(gebruikersnaam) != null;
    }
}
