package domein;

public class DomeinController
{

    private Speler huidigeSpeler;
    private SpelerRepository spelerRepository;

    /**
     *
     * @param gebruikersnaam
     * @param wachtwoord
     */
    public void meldAan(String gebruikersnaam, String wachtwoord)
    {
        //
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
