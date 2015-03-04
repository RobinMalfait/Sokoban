/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Demian
 */
public class DomeinControllerTest
{
    private DomeinController dc;
    @Before
    public void setUp()
    {
        this.dc = new DomeinController();
        this.dc.meldAan("heidiroobrouck", "heidiroobrouck");
    } 
    
    @Test
    public void it_should_has_a_spelerString()
    {
        String[] spelerString = new String[3];
        spelerString[0] = "Heidi";
        spelerString[1] = "Roobrouck";
        spelerString[2] = "heidiroobrouck";      
        Assert.assertArrayEquals(spelerString, this.dc.geefHuidigeSpeler());
    }    
    public void get_the_username_of_spelerString()
    {
         Assert.assertEquals("Heidi", this.dc.geefHuidigeSpeler()[0]);
    }
    
    
    

    
}
