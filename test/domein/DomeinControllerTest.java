/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import exceptions.GebruikerBestaatException;
import exceptions.WachtwoordException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import persistentie.SpelerMapper;
import security.BCrypt;

/**
 *
 * @author Demian
 */
public class DomeinControllerTest
{

    private DomeinController dc;
    private SpelerMapper mp;
    private Speler speler;

    @Before
    public void setUp()
    {
        this.dc = new DomeinController();
        this.mp = new SpelerMapper();
        
        speler = new Speler("TestSpeler", "TestSpeler", "TestSpeler", BCrypt.hashpw("TestSpeler", BCrypt.gensalt(10)));
        this.mp.addSpeler(speler);
    }

    /* Registreer */
    @Test(expected = WachtwoordException.class)
    public void registreer_PasswordNotEqual_WachtwoordException()
    {
        this.dc.registreer("Naam", "Voornaam", "Test1Registreer", "123456", "12345");
    }

    @Test(expected = WachtwoordException.class)
    public void registreer_PasswordNot8Characters_WachtwoordException()
    {
        this.dc.registreer("Naam", "Voornaam", "Test2Registreer", "123456", "123456");
    }

    @Test(expected = WachtwoordException.class)
    public void registreer_PasswordNoUpperCaseChars_WachtwoordException()
    {
        this.dc.registreer("Naam", "Voornaam", "Test3Registreer", "demian123", "demian123");
    }

    @Test(expected = GebruikerBestaatException.class)
    public void registreer_UsernameExist_GebruikerBestaatException()
    {
        this.dc.registreer("Naam", "Voornaam", speler.getGebruikersnaam(), "Demian123", "Demian123");
    }

    @Test
    public void registreer_LegalArguments_Registred()
    {
        this.dc.registreer("Naam", "Voornaam", "Test4Registreer", "Demian123", "Demian123");
        assertEquals(this.dc.geefHuidigeSpeler()[1], "Naam");
    }

    /* Inloggen */
    @Test(expected = WachtwoordException.class)
    public void meldAan_wrongUsername_WachtwoordException()
    {
        this.dc.meldAan("Test1MeldAan", "Demian123");
    }

    @Test(expected = WachtwoordException.class)
    public void meldAan_wrongPasswordUsernameInDatabase_WachtwoordException()
    {

        this.dc.meldAan("heidiroobrouck", "Demian123");
    }

    @Test(expected = WachtwoordException.class)
    public void meldAan_wrongUsernamePasswordInDatabase_WachtwoordException()
    {
        this.dc.meldAan("Test3MeldAan", speler.getWachtwoord());
    }

    @Test
    public void meldAan_LegalArguments_LoggedIn()
    {
        this.dc.meldAan(speler.getGebruikersnaam(), "TestSpeler");
    }

    /* Spelerdata */
    @Test
    public void geefHuidigeSpeler_Legal_UserString()
    {
        this.dc.meldAan(speler.getGebruikersnaam(), "TestSpeler");
         
        String[] spelerData = new String[3];
        spelerData[0] = speler.getNaam();
        spelerData[1] = speler.getVoornaam();
        spelerData[2] = speler.getGebruikersnaam();
 
        Assert.assertArrayEquals(spelerData, this.dc.geefHuidigeSpeler());
    }

    @After
    public void deletePossibleRecords()
    {
        try
        {

            this.mp.deleteQuery("DELETE FROM Speler WHERE gebruikersnaam = ?", "TestSpeler");
            this.mp.deleteQuery("DELETE FROM Speler WHERE gebruikersnaam = ?", "Test1Registreer");
            this.mp.deleteQuery("DELETE FROM Speler WHERE gebruikersnaam = ?", "Test2Registreer");
            this.mp.deleteQuery("DELETE FROM Speler WHERE gebruikersnaam = ?", "Test3Registreer");
            this.mp.deleteQuery("DELETE FROM Speler WHERE gebruikersnaam = ?", "Test4Registreer");

        } catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
