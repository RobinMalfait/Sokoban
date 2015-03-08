/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import exceptions.GebruikersnaamException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Demian
 */
public class SpelerTest
{
    private Speler speler;

    @Before
    public void setUp()
    {
       speler = new Speler(1, "Dekoninck", "Demian", "DemianDekoninck", "Demian123456", true);
    }
    
    @Test
    public void controleerIdNaConstructor()
    {
        assertEquals(speler.getId(), 1);
    }
    
    @Test
    public void controleerNaamNaConstructor()
    {
        assertEquals(speler.getNaam(), "Dekoninck");
    }
    
    @Test
    public void controleerVoornaamNaConstructor()
    {
        assertEquals(speler.getVoornaam(), "Demian");
    }
    
    @Test
    public void controleerGebruikersnaamNaConstructor()
    {
        assertEquals(speler.getGebruikersnaam(), "DemianDekoninck");
    }
    
    @Test
    public void controleerWachtwoord()
    {
        // Moet normaal een versleuteld wachtwoord zijn, maar voor deze tests wordt er gebruik gemaakt van een plain-text wachtwoord.
        assertEquals(speler.getWachtwoord(), "Demian123456");
    }
    
    @Test
    public void controleerAdminNaConstructor()
    {
        assertEquals(speler.isAdmin(), true);
    }
    
    @Test(expected = GebruikersnaamException.class)
    public void setGebruikersnaam_ShortPassword_GebruikersnaamException()
    {
        speler.setGebruikersnaam("Demian"); // < 8 karakters
    }
    
    @Test
    public void toString_Legal_String()
    {
        assertEquals(String.format("Speler(id: %d, gebruikersnaam: %s, voornaam: %s, naam: %s, %s)%n", 
                speler.getId(), 
                speler.getGebruikersnaam(), 
                speler.getVoornaam(), 
                speler.getNaam(),
                (speler.isAdmin() ? "admin" : "geen admin"))
                , speler.toString());
    }
}
