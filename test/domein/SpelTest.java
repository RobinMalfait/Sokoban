/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    @Before
    public void setUp() throws SQLException
    {
        this.mp = new SpelMapper();
        
    }
    
    @Test
    public void getId()
    {
        assertEquals(999, spel.getId());
    }
    
    @Test
    public void getNaam()
    {
        assertEquals("SpelTest1", spel.getNaam());
    }    
    
    @After
    public void deletePossibleRecords() throws SQLException
    {
        mp.deleteQuery("DELETE FROM Spel WHERE id = ?", 999);
    }
}
