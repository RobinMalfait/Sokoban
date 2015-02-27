package domein;

import exceptions.GebruikerBestaatException;
import java.util.*;
import persistentie.SpelerMapper;
import security.BCrypt;

public class SpelerRepository
{
    
    private final SpelerMapper spelerMapper;
    private final List<Speler> spelers;

    /**
     * 
     */
    public SpelerRepository()
    {
        this.spelers = new ArrayList<>();
        this.spelerMapper = new SpelerMapper();
    }

    /**
     *
     *
     * @param gebruikersnaam
     * @param wachtwoord
     * @return
     */
    public Speler zoekSpelerViaGebruikersnaamWachtwoord(String gebruikersnaam, String wachtwoord)
    {
        // Speler zoeken uit de SpelerMapper
        Speler speler = this.spelerMapper.geefSpeler(gebruikersnaam);

        // Controleer of het wachtwoord van de speler overeenkomt met die uit de parameter
        return BCrypt.checkpw(wachtwoord, speler.getWachtwoord()) ? speler : null;
    }

    /**
     * 
     * @param speler 
     */
    public void voegToe(Speler speler)
    {
        if (bestaatSpeler(speler.getGebruikersnaam()))
        {
            throw new GebruikerBestaatException("De gebruikersnaam is al in gebruik.");
        }

        this.spelers.add(speler);
        this.spelerMapper.addSpeler(speler);
    }
    
    /**
     * 
     * @param gebruikersnaam
     * @return 
     */

    private boolean bestaatSpeler(String gebruikersnaam)
    {
        return spelerMapper.geefSpeler(gebruikersnaam) != null;
    }
}
