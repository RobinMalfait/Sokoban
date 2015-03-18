package domein;

import exceptions.GebruikersnaamException;
import languages.LanguageManager;

public class Speler
{
    private int id;
    private String naam;
    private String voornaam;
    private String gebruikersnaam;
    private String wachtwoord;
    private boolean admin;
    private LanguageManager lang;

    /**
     * Maak een nieuw Speler object.
     *
     * @param naam String
     * @param voornaam String
     * @param gebruikersnaam String
     */
    public Speler(String naam, String voornaam, String gebruikersnaam)
    {
        this.setNaam(naam);
        this.setVoornaam(voornaam);
        this.setGebruikersnaam(gebruikersnaam);
        this.setAdmin(false);
    }

    /**
     * Maak een nieuw Speler object.
     *
     * @param naam String
     * @param voornaam String
     * @param gebruikersnaam String
     * @param wachtwoord String
     */
    public Speler(String naam, String voornaam, String gebruikersnaam, String wachtwoord)
    {
        this(naam, voornaam, gebruikersnaam);

        this.setWachtwoord(wachtwoord);
        this.setAdmin(false);
    }
    
    /**
     * Maak een nieuw Speler object.
     *
     * @param naam String
     * @param voornaam String
     * @param gebruikersnaam String
     * @param wachtwoord String
     * @param admin boolean
     */
    public Speler(String naam, String voornaam, String gebruikersnaam, String wachtwoord, boolean admin)
    {
        this(naam, voornaam, gebruikersnaam, wachtwoord);

        this.setAdmin(admin);
    }

    /**
     * Maak een nieuw Speler object.
     *
     * @param id int
     * @param naam String
     * @param voornaam String
     * @param gebruikersnaam String
     * @param wachtwoord String
     * @param admin boolean
     */
    public Speler(int id, String naam, String voornaam, String gebruikersnaam, String wachtwoord, boolean admin)
    {
        this(naam, voornaam, gebruikersnaam, wachtwoord, admin);

        this.setId(id);
    }

    public void setLang(LanguageManager lang)
    {
        this.lang = lang;
    }

    /**
     * Stel het id in.
     * 
     * @param id int
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Stel de naam in.
     * 
     * @param naam String
     */
    public void setNaam(String naam)
    {
        this.naam = naam;
    }

    /**
     * Stel de voornaam in.
     * 
     * @param voornaam String
     */
    public void setVoornaam(String voornaam)
    {
        this.voornaam = voornaam;
    }

    /**
     * Stel de gebruikersnaam in.
     * 
     * @param gebruikersnaam String
     */
    public void setGebruikersnaam(String gebruikersnaam)
    {
        if (gebruikersnaam.length() < 8)
            throw new GebruikersnaamException(lang.get("err.usernameDR"));

        this.gebruikersnaam = gebruikersnaam;
    }

    /**
     * Stel het wachtwoord in.
     * 
     * @param wachtwoord String
     */
    public void setWachtwoord(String wachtwoord)
    {        
        this.wachtwoord = wachtwoord;
    }

    /**
     * 
     * @param admin boolean
     */
    public void setAdmin(boolean admin)
    {
        this.admin = admin;
    }
    

    /**
     * Verkrijg het id van de speler.
     * 
     * @return int
     */
    public int getId()
    {
        return id;
    }

    /**
     * Verkrijg de naam van de speler.
     * 
     * @return String
     */
    public String getNaam()
    {
        return naam;
    }

    /**
     * Verkrijg de voornaam van de speler.
     * 
     * @return String
     */
    public String getVoornaam()
    {
        return voornaam;
    }

    /**
     * Verkrijg de gebruikersnaam van de speler.
     * 
     * @return String
     */
    public String getGebruikersnaam()
    {
        return gebruikersnaam;
    }

    /**
     * Verkrijg het wachtwoord van de speler.
     * 
     * @return String
     */
    public String getWachtwoord()
    {
        return wachtwoord;
    }

    /**
     * 
     * @return boolean
     */
    public boolean isAdmin()
    {
        return admin;
    }

    /**
     * Transformeer het speler object naar een String.
     * 
     * @return String
     */
    @Override
    public String toString()
    {
        return String.format("Speler(%s: %d, %s: %s, %s: %s, %s: %s, %s)%n", 
                id,
                this.id, 
                lang.get("user.username"),
                this.gebruikersnaam, 
                lang.get("user.firstname"),
                this.voornaam, 
                lang.get("user.name"),
                this.naam,
                (admin ? "admin" : "geen admin"));
    }

}
