package domein;

import exceptions.WachtwoordBevestigingNietCorrectException;
import security.BCrypt;

public class DomeinController
{

    private final SpelerRepository spelerRepository;
    private Speler huidigeSpeler;

 
    /**
     * 
     */
    public DomeinController()
    {
        spelerRepository = new SpelerRepository();
    }

    /**
     * Meld een speler aan aan de hand van Gebruikersnaam en wachtwoord.
     *
     * @param gebruikersnaam
     * @param wachtwoord
     * @return 
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
        
        wachtwoord = BCrypt.hashpw(wachtwoord, BCrypt.gensalt(10));

        Speler nieuweSpeler = new Speler(naam, voornaam, gebruikersnaam, wachtwoord);        
        setHuidigeSpeler(nieuweSpeler);
        spelerRepository.voegToe(nieuweSpeler);  
    }
    
}
