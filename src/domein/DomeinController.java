package domein;

import exceptions.GebruikersnaamException;
import exceptions.WachtwoordException;
import languages.EN;
import languages.FR;
import languages.LanguageManager;
import languages.NL;
import security.BCrypt;

public class DomeinController
{

    private final SpelerRepository spelerRepository;
    private final SpelRepository spelRepository;
    private Speler huidigeSpeler;
    private Spel huidigSpel;
    private final LanguageManager lang;
 
    /**
     * Maak een DomeinController-object aan
     * @param lang
     */
    public DomeinController()
    {
        spelerRepository = new SpelerRepository();
        spelRepository = new SpelRepository();
        
        this.lang = new LanguageManager();

        lang.addLanguage(new NL());
        lang.addLanguage(new FR());
        lang.addLanguage(new EN());
    }

    /**
     * Geef de LanguageManager terug
     * 
     * @return LanguageManager
     */
    public LanguageManager getLang()
    {
        return lang;
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
            throw new WachtwoordException(lang.get("err.login"));
        
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
            throw new WachtwoordException(lang.get("err.passwordrepeat"));
        
        //controle DR Nieuwe Speler
        if(gebruikersnaam.length() < 8)
            throw new GebruikersnaamException(lang.get("err.usernameDR"));
        
        if(wachtwoord.length() < 8 || !wachtwoord.matches(".*[A-Z].*") || !wachtwoord.matches(".*[a-z].*") || !wachtwoord.matches(".*[0-9].*"))
            throw new WachtwoordException(lang.get("err.passwordDR"));

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
    public void kiesSpel(int id)
    {
        // zoek het spelobject in de spelrepository 
        this.huidigSpel = spelRepository.zoekSpel(id);
        
        // Selecteer het eerste spelbord van het gekozen spel.
        this.huidigSpel.bepaalVolgendSpelbord();
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
     * Geef een lijst van spelborden in 2-dimensionele String vorm
     * 
     * @return String[][]
     */
    public String[][] geefSpelbordenString()
    {
        return huidigSpel.geefSpelbordenString();
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

    /**
     * Verplaats de speler (en bijhorende items) volgens een richting
     * 
     * @param richting int
     */
    public void verplaatsSpeler(int richting)
    {
        /*
        richting
        1: omhoog
        2: omlaag
        3: links
        4: rechts
        */
        
        huidigSpel.verplaatsSpeler(richting);
    }

    /**
     * Controleer of het huidig spelbord van het spel voltooid is
     * 
     * @return boolean
     */
    public boolean isSpelbordVoltooid()
    {
        return huidigSpel.isHuidigSpelbordVoltooid();
    }

    /**
     * Bepaald het volgend spelbord van het huidig Spel
     */
    public void bepaalVolgendSpelbord()
    {
        this.huidigSpel.bepaalVolgendSpelbord();
    }
    
    /**
     * Controleer of alle spelborden voltooid zijn, is het spel voltooid 
     * 
     * @return boolean
     */
    public boolean isEindeSpel()
    {
        return this.huidigSpel.isEindeSpel();
    }
}
