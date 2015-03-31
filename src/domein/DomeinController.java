package domein;

import exceptions.GebruikersnaamException;
import exceptions.SpelException;
import exceptions.WachtwoordException;
import languages.LanguageManager;
import persistentie.VakMapper;
import security.BCrypt;

public class DomeinController
{

    private final SpelerRepository spelerRepository;
    private final SpelRepository spelRepository;
    private Speler huidigeSpeler;
    private Spel huidigSpel;
    private final LanguageManager lang;
    private final VakMapper vakMapper = new VakMapper();

    /**
     * Maak een DomeinController-object aan
     * 
     * @param lang LanguageManager;
     */
    public DomeinController(LanguageManager lang)
    {
        spelerRepository = new SpelerRepository();
        spelRepository = new SpelRepository();
        this.lang = lang;
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
        {
            throw new WachtwoordException(lang.get("err.login"));
        }

        this.setHuidigeSpeler(speler);
        this.huidigeSpeler.setLang(lang);

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

        String[] spelerString = new String[4];

        spelerString[0] = huidigeSpeler.getVoornaam();
        spelerString[1] = huidigeSpeler.getNaam();
        spelerString[2] = huidigeSpeler.getGebruikersnaam();
        spelerString[3] = String.valueOf(huidigeSpeler.isAdmin());

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
        if ( ! wachtwoord.equals(wachtwoordBevestiging))
        {
            throw new WachtwoordException(lang.get("err.passwordrepeat"));
        }

        //controle DR Nieuwe Speler
        if (gebruikersnaam.length() < 8)
        {
            throw new GebruikersnaamException(lang.get("err.usernameDR"));
        }

        if (wachtwoord.length() < 8 || !wachtwoord.matches(".*[A-Z].*") || !wachtwoord.matches(".*[a-z].*") || !wachtwoord.matches(".*[0-9].*"))
        {
            throw new WachtwoordException(lang.get("err.passwordDR"));
        }

        wachtwoord = BCrypt.hashpw(wachtwoord, BCrypt.gensalt(10));

        Speler nieuweSpeler = new Speler(naam, voornaam, gebruikersnaam, wachtwoord);
        setHuidigeSpeler(nieuweSpeler);
        huidigeSpeler.setLang(lang);
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

        if (this.huidigSpel == null)
        {
            throw new SpelException("Er werd geen spel gevonden met nummer " + id);
        }
        else
        {
            this.huidigSpel.bepaalVolgendSpelbord(); // Selecteer het eerste spelbord van het gekozen spel.
        }
    }

    /**
     * Kies een spelbord van het huidig spel
     *
     * @param id int
     */
    public void kiesSpelbord(int id)
    {
        // zoek het spelobject in de spelrepository 
        this.huidigSpel.kiesSpelbord(id);

        if (this.huidigSpel == null)
        {
            throw new SpelException("Er werd geen spel gevonden met nummer " + id);
        }
        else
        {
            this.huidigSpel.bepaalVolgendSpelbord(); // Selecteer het eerste spelbord van het gekozen spel.
        }
    }
    /**
     * Geef een lijst van spellen in 2-dimensionele String vorm
     *
     * @return String[][]
     */
    public String[][] geefLijstSpellen()
    {
        return spelRepository.geefLijstSpellen();
    }

    /**
     * Geef een lijst van spelborden in 2-dimensionele String vorm
     *
     * @return String[][]
     */
    public String[][] geefLijstSpelborden()
    {
        return huidigSpel.geefLijstSpelborden();
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
     * Verkrijg de naam van het huidige spel.
     * 
     * @return String
     */
    public String geefNaamHuidigSpel()
    {
        return huidigSpel.getNaam();
    }
    
    /**
     * Verkrijg de naam van het huidige spelbord.
     * 
     * @return String
     */
    public String geefNaamHuidigSpelbord()
    {
        return huidigSpel.getHuidigSpelbord().getNaam();
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
    public boolean isEindeSpelbord()
    {
        return huidigSpel.isEindeSpelbord();
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

    /**
     * Voeg een spel toe aan de repository, en in de database.
     * 
     * @param naam String
     */
    public void voegSpelToe(String naam)
    {
        this.huidigSpel = this.spelRepository.voegSpelToe(naam);      
    }

    /**
     * Voeg een spelbord toe, met de nodige vakken aan een Spel en in de
     * database.
     * 
     * @param naam String
     * @param vakken int[][]
     */
    public void voegSpelbordToe(String naam)
    {
        this.huidigSpel.voegSpelbordToe(naam);
    }

    /**
     * Retourneet of de gebruiker admin is of niet
     *
     * @return boolean
     */
    public boolean isAdmin()
    {
        if(this.huidigeSpeler == null)
            return false;
        
        return this.huidigeSpeler.isAdmin();
    }

    public LanguageManager getLanguageManager() {
        return this.lang;
    }

    /**
     * Stel de taal in.
     * 
     * @param language String
     */
    public void setLanguage(String language) {
        this.lang.setLanguage(language);
    }
    
    /**
     * Stel de vakken van het huidige spelbord in op hun beginwaarde.
     */
    public void resetSpelbord() 
    {
        this.huidigSpel.resetSpelbord();
    }
    
    /**
     * Plaats een item op een vak.
     * 
     * @param coordinaat String
     * @param naam String
     */
    public void voerVakIn(String coordinaat, String naam)
    {
        this.huidigSpel.voerVakIn(coordinaat, naam);
    }
    
    /**
     * Sla het huidige gewijzigde spel op.
     */
    public void slaHuidigSpelOp()
    {
        this.huidigSpel.slaOp();
    }
    
    /**
     * Geef het aantal verplaatsingen van het huidige spelbord.
     * 
     * @return int
     */
    public int geefAantalVerplaatsingen()
    {
        return this.huidigSpel.geefAantalVerplaatsingen();
    }
    
    /**
     * Controleer of het gewijzigde spel aan de eisen voldoet.
     * 
     * @return boolean
     */
    public boolean controleerSpel()
    {
        return this.huidigSpel.controleerSpel();
    }
}
