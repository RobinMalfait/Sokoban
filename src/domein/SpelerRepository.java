package domein;

import java.util.*;
import persistentie.SpelerMapper;

public class SpelerRepository
{
    private final SpelerMapper Spelermapper; 
    private List<Speler> spelers;

    public SpelerRepository()
    {
        this.Spelermapper = new SpelerMapper();
    }
    
    /**
     * 
     * @param gebruikersnaam
     * @param wachtwoord
     */
    public Speler meldAan(String gebruikersnaam, String wachtwoord) 
    {
        return null;       
    }
    /**
     * 
     * 
     * @param gebruikersnaam
     * @param wachtwoord
     */
    public Speler meldAan(String gebruikersnaam, String wachtwoord)
    {
        // TODO - implement SpelerRepository.meldAan
        throw new UnsupportedOperationException();
    }

}
