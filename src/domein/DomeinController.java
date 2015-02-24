package domein;

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
    public boolean meldAan(String gebruikersnaam, String wachtwoord)
    {
        Speler speler = spelerRepository.meldAan(gebruikersnaam, wachtwoord);
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

}
