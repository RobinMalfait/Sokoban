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
    public void meldAan(String gebruikersnaam, String wachtwoord)
    {
        Speler speler = spelerRepository.meldAan(gebruikersnaam, wachtwoord);
        if(speler ==  null) {
            // Geen gebruiker gevonden
        }
        else {
            // Gebruiker gevonden, deze slaan we op in de domeincontroller.
            this.setHuidigeSpeler(speler);
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
    
    

}
