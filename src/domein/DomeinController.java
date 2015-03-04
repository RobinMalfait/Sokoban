package domein;

import exceptions.GebruikersnaamException;
import exceptions.WachtwoordException;
import exceptions.GebruikerBestaatException;
import java.util.List;
import security.BCrypt;

public class DomeinController
{

    private final SpelerRepository spelerRepository;
    private final SpelbordRepository spelbordRepository;
    private Speler huidigeSpeler;
    private Spel spel;
 
    /**
     * Maak een DomeinController-object aan
     */
    public DomeinController()
    {
        spelerRepository = new SpelerRepository();
        spelbordRepository = new SpelbordRepository();
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
     */
    public void registreer(String naam, String voornaam, String gebruikersnaam, String wachtwoord, String wachtwoordBevestiging)
    {
        if ( !wachtwoord.equals(wachtwoordBevestiging))
            throw new WachtwoordException("Het wachtwoord en de wachtwoordBevestiging komen niet overeen.");
        
        //controle DR Nieuwe Speler
        if(gebruikersnaam.length() < 8)
            throw new GebruikersnaamException("De gebruikersnaam moet minstens 8 karakters lang zijn.");
        
        if(wachtwoord.length() < 8 || !wachtwoord.matches(".*[A-Z].*") || !wachtwoord.matches(".*[a-z].*") || !wachtwoord.matches(".*[0-9].*"))
            throw new WachtwoordException("Het wachtwoord voldoet niet aan de eisen.");

        wachtwoord = BCrypt.hashpw(wachtwoord, BCrypt.gensalt(10));

        Speler nieuweSpeler = new Speler(naam, voornaam, gebruikersnaam, wachtwoord);
        setHuidigeSpeler(nieuweSpeler);
        spelerRepository.voegToe(nieuweSpeler); 
    }
    
    public void speelSpel()
    {
        this.setSpel(new Spel(0)); // De 0 slaat op het eerste Spelbord      
        //spel.toonSpelbord();
    }

    public void setSpel(Spel spel)
    {
        this.spel = spel;
    }
    
    public String toonSpelbord()
    {
        return spel.toonSpelbord()+"test";
    }
    
}
