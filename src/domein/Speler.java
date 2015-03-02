package domein;

import exceptions.GebruikersnaamException;
import exceptions.WachtwoordException;

public class Speler
{
    private int id;
    private String naam;
    private String voornaam;
    private String gebruikersnaam;
    private String wachtwoord;
    private boolean admin;

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
        if (gebruikersnaam.length() < 8 || gebruikersnaam.length() > 30)
            throw new GebruikersnaamException("De gebruikersnaam voldoet niet aan de eisen.");

        this.gebruikersnaam = gebruikersnaam;
    }

    /**
     * Stel het wachtwoord in.
     * 
     * @param wachtwoord String
     */
    public void setWachtwoord(String wachtwoord)
    {
        if (wachtwoord.length() < 8)
            throw new WachtwoordException("Het wachtwoord voldoet niet aan de eisen.");

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
        return String.format("Speler(%d, %s, %s, %s, %s)%n", 
                this.id, 
                this.gebruikersnaam, 
                this.voornaam, 
                this.naam,
                (admin ? "Admin" : "Geen admin"));
    }

}
