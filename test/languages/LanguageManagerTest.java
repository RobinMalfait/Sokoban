package languages;

import exceptions.TaalException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author robin
 */  
public class LanguageManagerTest
{
    private LanguageManager languageManager;
    private Language EN;
    private Language NL;
    
    @Before
    public void setUp()
    {
        this.languageManager = new LanguageManager();
        this.EN = new EN();
        this.NL = new NL();
    }

    @Test
    public void it_should_add_a_language()
    {
        this.languageManager.addLanguage(this.EN);
        this.languageManager.addLanguage(this.NL);
        
        assertEquals(2, this.languageManager.countLanguages());
    }
    
    @Test
    public void it_should_get_the_choices()
    {
        this.languageManager.addLanguage(this.EN);
        this.languageManager.addLanguage(this.NL);
        
        assertEquals("EN, NL", this.languageManager.getKeuzes());
    }
    
    @Test(expected=TaalException.class)
    public void it_should_throw_an_exception()
    {
        this.languageManager.addLanguage(this.EN);
        this.languageManager.addLanguage(this.NL);
        
        this.languageManager.setLanguage("FR");
    }
    
    @Test
    public void it_should_translate()
    {
        this.EN.map("test", "Test - EN");
        this.NL.map("test", "Test - NL");
        
        this.languageManager.addLanguage(this.NL);
        this.languageManager.addLanguage(this.EN);
        
        this.languageManager.setLanguage("EN");
        assertEquals("Test - EN", this.languageManager.get("test"));
        
        this.languageManager.setLanguage("NL");
        assertEquals("Test - NL", this.languageManager.get("test"));
    }
}
