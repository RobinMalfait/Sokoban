/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import persistentie.SpelMapper;
import persistentie.SpelerMapper;

/**
 *
 * @author Demian
 */
public class SpelTest
{
    private Spel spel;
    private SpelMapper mp;
    private List<Spelbord> spelborden;
    
    @Before
    public void setUp() throws SQLException
    {
        this.mp = new SpelMapper();
        
        this.mp.insertQuery("CREATE TABLE test_spel (id INT, naam varchar(255), nummer INT)");
        this.mp.insertQuery("INSERT INTO test_spel (naam, nummer) VALUES ('test_spel_1', '1')");
        
        this.spel = new Spel(1, "test_spel_1");
        
        this.spelborden = new ArrayList<>();
        
        this.spelborden.add(new Spelbord(1, "test_spelbord_1"));
        this.spelborden.add(new Spelbord(2, "test_spelbord_2"));
        this.spelborden.add(new Spelbord(3, "test_spelbord_3"));
        
        spel.setSpelborden(spelborden);
    }
    
    @Test
    public void getId()
    {
        assertEquals(1, spel.getId());
    }
    
    @Test
    public void getNaam()
    {
        assertEquals("test_spel_1", spel.getNaam());
    }    
    
    @Test
    public void setSpelborden()
    {
        assertEquals(3, spel.getSpelborden().size());
    }
    
    @Test
    public void getHuidigSpelbord()
    {
        spel.setHuidigSpelbord(spelborden.get(0));
        assertEquals(spel.getHuidigSpelbord(), spelborden.get(0));
        
        spel.setHuidigSpelbord(null);
    }
    
    @Test
    public void bepaalVolgendSpelbord1()
    {
        spel.bepaalVolgendSpelbord();
        assertEquals(spel.getHuidigSpelbord(), spelborden.get(0));
    }

    @Test
    public void bepaalVolgendSpelbord2()
    {
        spel.bepaalVolgendSpelbord();
        spel.bepaalVolgendSpelbord();
        
        assertEquals(spel.getHuidigSpelbord(), spelborden.get(1));
    }

    @Test
    public void bepaalVolgendSpelbord3()
    {
        spel.bepaalVolgendSpelbord();
        spel.bepaalVolgendSpelbord();
        spel.bepaalVolgendSpelbord();
        
        assertEquals(spel.getHuidigSpelbord(), spelborden.get(2));
    }
 
    @Test 
    public void isHuidigSpelbordVoltooid_nee()
    {
        spelborden.get(0).setVoltooid(true);
        spelborden.get(1).setVoltooid(true);
         
        assertEquals(spel.isEindeSpel(), false);        
    }
    
    @Test 
    public void isHuidigSpelbordVoltooid_ja()
    {
        spelborden.get(0).setVoltooid(true);
        spelborden.get(1).setVoltooid(true);
        spelborden.get(2).setVoltooid(true);
        
        assertEquals(spel.isEindeSpel(), true);        
    }
    
    @Test
    public void geefSpelbordenString()
    {
        String[][] spelbordenString = { { "1" , "test_spelbord_1" }, { "2" , "test_spelbord_2" }, { "3" , "test_spelbord_3" } };
        Assert.assertArrayEquals(spelbordenString, spel.geefSpelbordenString());
    }   
    
    @After
    public void deletePossibleRecords() throws SQLException
    {
        this.mp.deleteQuery("DROP TABLE test_spel;");
    }
}
