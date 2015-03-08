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

/**
 *
 * @author Demian
 */
public class DomeinControllerTest 
{
    private DomeinController dc;
    private SpelerMapper mp;
    
    @Before
    public void setUp()
    {
        this.dc = new DomeinController();
        this.mp = new SpelerMapper();
        
        Speler speler = new Speler("TestSpeler", "TestSpeler", "TestSpeler", "TestSpeler");
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
        this.dc.registreer("Naam", "Voornaam", "TestSpeler", "Demian123", "Demian123");
    }      
    @Test
    public void registreer_LegalArguments_Registred()
    {
        this.dc.registreer("Naam", "Voornaam", "Test4Registreer", "Demian123", "Demian123");
        assertEquals(this.dc.geefHuidigeSpeler()[1], "Naam");
    }  
    
    
    @Test
    public void it_should_has_a_spelerString()
    {
        this.dc.meldAan("heidiroobrouck", "heidiroobrouck");
        
        
        String[] spelerString = new String[3];
        spelerString[0] = "Heidi";
        spelerString[1] = "Roobrouck";
        spelerString[2] = "heidiroobrouck";      
        Assert.assertArrayEquals(spelerString, this.dc.geefHuidigeSpeler());
    }    
    
    @Test
    public void get_the_username_of_spelerString()
    {
         Assert.assertEquals("Heidi", this.dc.geefHuidigeSpeler()[0]);
    }
    
    @After
    public void deletePossibleRecords()
    {
        try {
            
            this.mp.deleteQuery("DELETE FROM Speler WHERE gebruikersnaam = ?", "TestSpeler");
            this.mp.deleteQuery("DELETE FROM Speler WHERE gebruikersnaam = ?", "Test1Registreer");
            this.mp.deleteQuery("DELETE FROM Speler WHERE gebruikersnaam = ?", "Test2Registreer");
            this.mp.deleteQuery("DELETE FROM Speler WHERE gebruikersnaam = ?", "Test3Registreer");
            this.mp.deleteQuery("DELETE FROM Speler WHERE gebruikersnaam = ?", "Test4Registreer");
            
        } catch (SQLException ex) {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    
}
