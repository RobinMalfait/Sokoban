package domein;

import exceptions.WachtwoordBevestigingNietCorrectException;
import languages.LanguageManager;

public class DomeinController
{

    private final SpelerRepository spelerRepository;
    private Speler huidigeSpeler;
    private LanguageManager languageManager;

    /**
     * 
     * @param languageManager 
     */
    public DomeinController(LanguageManager languageManager)
    {
        this.languageManager = languageManager;
        spelerRepository = new SpelerRepository();
    }

    /**
     * Meld een speler aan aan de hand van Gebruikersnaam en wachtwoord.
     *
     * @param gebruikersnaam
     * @param wachtwoord
     */
    public boolean meldAan(String gebruikersnaam, String wachtwoord)
    {
        Speler speler = spelerRepository.zoekSpelerViaGebruikersnaamWachtwoord(gebruikersnaam, wachtwoord);
        if (speler == null)
            return false;
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

    /**
     * 
     * @return 
     */
    public String[] geefHuidigeSpeler()
    {
        if (huidigeSpeler == null)
        {
            return null;
        }

        String[] spelerString = new String[3];

        spelerString[0] = huidigeSpeler.getNaam();
        spelerString[1] = huidigeSpeler.getVoornaam();
        spelerString[2] = huidigeSpeler.getGebruikersnaam();

        return spelerString;

    }
    
    /** 
     * 
     * @param naam 
     * @param voornaam 
     * @param gebruikersnaam 
     * @param wachtwoord
     * @param wachtwoordBevestiging 
     */
    public void registreer(String naam, String voornaam, String gebruikersnaam, String wachtwoord, String wachtwoordBevestiging)
    {
        if ( ! wachtwoord.equals(wachtwoordBevestiging)) {
            throw new WachtwoordBevestigingNietCorrectException();
        }

        Speler nieuweSpeler = new Speler(naam, voornaam, gebruikersnaam, wachtwoord);
        setHuidigeSpeler(nieuweSpeler);
        spelerRepository.voegToe(nieuweSpeler);  
    }
    
    /**
     * 
     * @return 
     */
    public LanguageManager getLanguageManager()
    {
        return this.languageManager;
    }

}
