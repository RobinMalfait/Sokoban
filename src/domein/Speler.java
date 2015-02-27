
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

    /**
     *
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     *
     * @param naam
     */
    public void setNaam(String naam)
    {
        this.naam = naam;
    }

    /**
     *
     * @param voornaam
     */
    public void setVoornaam(String voornaam)
    {
        this.voornaam = voornaam;
    }

    /**
     *
     * @param gebruikersnaam
     */
    public void setGebruikersnaam(String gebruikersnaam)
    {
        if (gebruikersnaam.length() < 8 || gebruikersnaam.length() > 30)
            throw new GebruikersnaamException("De gebruikersnaam voldoet niet aan de eisen.");

        this.gebruikersnaam = gebruikersnaam;
    }

    /**
     *
     * @param wachtwoord
     */
    public void setWachtwoord(String wachtwoord)
    {
        if (wachtwoord.length() < 8)
            throw new WachtwoordException("Het wachtwoord voldoet niet aan de eisen.");

        this.wachtwoord = wachtwoord;
    }

    /**
     *
     * @return int
     */
    public int getId()
    {
        return id;
    }

    /**
     *
     * @return String
     */
    public String getNaam()
    {
        return naam;
    }

    /**
     *
     * @return String
     */
    public String getVoornaam()
    {
        return voornaam;
    }

    /**
     *
     * @return String
     */
    public String getGebruikersnaam()
    {
        return gebruikersnaam;
    }

    /**
     *
     * @return String
     */
    public String getWachtwoord()
    {
        return wachtwoord;
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return String.format("Speler(%d, %s, %s, %s)%n", this.id, this.gebruikersnaam, this.voornaam, this.naam);
    }

}
