package domein;

import exceptions.GebruikersnaamException;
import exceptions.WachtwoordException;
import java.util.Arrays;
import java.util.List;
import security.BCrypt;

public class DomeinController
{

    private final SpelerRepository spelerRepository;
    private final SpelRepository spelRepository;
    private Speler huidigeSpeler;
    private Spel huidigSpel;
 
    /**
     * Maak een DomeinController-object aan
     */
    public DomeinController()
    {
        spelerRepository = new SpelerRepository();
        spelRepository = new SpelRepository();
    }

    /**
     * Meld een speler aan aan de hand van Gebruikersnaam en wachtwoord.
     *
     * @param gebruikersnaam String
     * @param wachtwoord String
     */
    public void meldAan(String gebruikersnaam, String wachtwoord)
    {
        Speler speler = spelerRepository.zoekSpelerViaGebruikersnaamWachtwoord(gebruikersnaam, wachtwoord);
        
        if (speler == null)
            throw new WachtwoordException("De aanmeldgegevens zijn niet correct.");
        
        this.setHuidigeSpeler(speler);

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
    
    /**
     * Speel een spel met bepaald id
     * 
     * @param id int
     */
    public void speelSpel(int id)
    {
        kiesSpel(id);
        
        this.huidigSpel.setSpelbord(1);
        
    }

    /**
     * Kies een spel
     * 
     * @param spelnummer int
     */
    public void kiesSpel(int spelnummer)
    {
        this.huidigSpel = spelRepository.zoekSpel(spelnummer);
    }

    /**
     * Geef een lijst van spellen in 2-dimensionele String vorm
     * 
     * @return String[][]
     */
    public String[][] geefSpellenString()
    {
        return spelRepository.geefSpellenString();
    } 
    
    /**
     * Stel het huidig spel in
     * 
     * @param spel Spel
     */
    private void setHuidigSpel(Spel spel)
    {
        this.huidigSpel = spel;
    }
    
    /**
     * Toon het spelbord
     * 
     * @return String[][]
     */
    public String[][] toonSpelbord()
    {
        return huidigSpel.toonSpelbord();
    }
}
