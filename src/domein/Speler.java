package domein;

public class Speler
{
    private int id;
    private String naam;
    private String voornaam;
    private String gebruikersnaam;
    private String wachtwoord;

    /**
     * Create a Speler object.
     * 
     * @param naam
     * @param voornaam
     * @param gebruikersnaam 
     */
    public Speler(String naam, String voornaam, String gebruikersnaam)
    {
        this.setNaam(naam);
        this.setVoornaam(voornaam);
        this.setGebruikersnaam(gebruikersnaam);
    }
    
    /**
     * Create a Speler object.
     * 
     * @param naam
     * @param voornaam
     * @param gebruikersnaam
     * @param wachtwoord 
     */
    public Speler(String naam, String voornaam, String gebruikersnaam, String wachtwoord)
    {
        this(naam, voornaam, gebruikersnaam);
        
        this.setWachtwoord(wachtwoord);
    }

    /**
     * Create a Speler object.
     * 
     * @param id
     * @param naam
     * @param voornaam
     * @param gebruikersnaam 
     * @param wachtwoord 
     */
    public Speler(int id, String naam, String voornaam, String gebruikersnaam, String wachtwoord)
    {
        this(naam, voornaam, gebruikersnaam, wachtwoord);
        
        this.setId(id);
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setNaam(String naam)
    {
        this.naam = naam;
    }

    public void setVoornaam(String voornaam)
    {
        this.voornaam = voornaam;
    }

    public void setGebruikersnaam(String gebruikersnaam)
    {
        this.gebruikersnaam = gebruikersnaam;
    }

    public void setWachtwoord(String wachtwoord)
    {
        this.wachtwoord = wachtwoord;
    }

    
    
}
