package domein;

import java.sql.SQLException;

public class DomeinController
{
    private final SpelerRepository spelerRepository;
    private Speler huidigeSpeler;
    
    public DomeinController()
    {
        spelerRepository = new SpelerRepository();
    }
    
    /**
     * Meld een speler aan aan de hand van Gebruikersnaam en wachtwoord.
     * 
     * @param gebruikersnaam
     * @param wachtwoord
     */
    public boolean meldAan(String gebruikersnaam, String wachtwoord) throws SQLException
    {
        Speler speler = spelerRepository.meldAan(gebruikersnaam, wachtwoord);
        if(speler == null) {
            return false;
            // Geen gebruiker gevonden
        }
        else {
            
            // Gebruiker gevonden, deze slaan we op in de domeincontroller.
            this.setHuidigeSpeler(speler);
            return true;
        }
    }

    /**
     *
     * @param huidigeSpeler
     */
    private void setHuidigeSpeler(Speler huidigeSpeler)
    {
        this.huidigeSpeler = huidigeSpeler;
    }

}
