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
     *
     * @param gebruikersnaam
     * @param wachtwoord
     */
    public void meldAan(String gebruikersnaam, String wachtwoord)
    {
        //spelerRepository.geefSpeler("dd", "dd");
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
