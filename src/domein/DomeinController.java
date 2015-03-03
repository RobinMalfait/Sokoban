package domein;

import exceptions.WachtwoordException;
import security.BCrypt;

public class DomeinController
{

    private final SpelerRepository spelerRepository;
    private Speler huidigeSpeler;

 
    /**
     * Maak een DomeinController-object aan
     */
    public DomeinController()
    {
        spelerRepository = new SpelerRepository();
    }

    /**
     * Meld een speler aan aan de hand van Gebruikersnaam en wachtwoord.
     *
     * @param gebruikersnaam String
     * @param wachtwoord String
     * @return boolean
     */
    public boolean meldAan(String gebruikersnaam, String wachtwoord)
    {
        Speler speler = spelerRepository.zoekSpelerViaGebruikersnaamWachtwoord(gebruikersnaam, wachtwoord);
        
        if (speler == null) {
            return false;  
        } else {
             // Gebruiker gevonden, deze slaan we op in de domeincontroller.
            this.setHuidigeSpeler(speler);
            return true;
        }
    }

    /**
     * Markeer als huidige speler.
     * 
     * @param huidigeSpeler Speler
     */
    private void setHuidigeSpeler(Speler huidigeSpeler)
    {
        this.huidigeSpeler = huidigeSpeler;
    }

    /**
     * Geef de huidige speler terug.
     * 
     * @return String[]
     */
    public String[] geefHuidigeSpeler()
    {
        if (huidigeSpeler == null)
        {
            return null;
        }

        String[] spelerString = new String[3];

        spelerString[0] = huidigeSpeler.getVoornaam();
        spelerString[1] = huidigeSpeler.getNaam();
        spelerString[2] = huidigeSpeler.getGebruikersnaam();

        return spelerString;

    }
    
    /** 
     * Registreer een nieuwe speler.
     * 
     * @param naam String
     * @param voornaam String
     * @param gebruikersnaam String
     * @param wachtwoord String
     * @param wachtwoordBevestiging String
     * @param admin boolean
     */
    public void registreer(String naam, String voornaam, String gebruikersnaam, String wachtwoord, String wachtwoordBevestiging)
    {
        if ( ! wachtwoord.equals(wachtwoordBevestiging)) {
            throw new WachtwoordException("Het wachtwoord en de wachtwoordBevestiging komen niet overeen.");
        }
        
        if(wachtwoord.length() < 8 || !wachtwoord.matches(".*[A-Z].*") || !wachtwoord.matches(".*[a-z].*") || !wachtwoord.matches(".*[0-9].*"))
            throw new WachtwoordException("Het wachtwoord voldoet niet aan de eisen.");
            
        
        wachtwoord = BCrypt.hashpw(wachtwoord, BCrypt.gensalt(10));

        Speler nieuweSpeler = new Speler(naam, voornaam, gebruikersnaam, wachtwoord);        
        setHuidigeSpeler(nieuweSpeler);
        spelerRepository.voegToe(nieuweSpeler);  
    }
    
}
