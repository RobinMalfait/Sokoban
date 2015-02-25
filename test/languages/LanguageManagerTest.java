package languages;

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
    
    @Before
    public void setUp()
    {
        this.languageManager = new LanguageManager();
    }

    @Test
    public void it_should_add_a_language()
    {
        Language en = new EN();
        Language nl = new NL();
        
        this.languageManager.addLanguage(en);
        this.languageManager.addLanguage(nl);
        
        assertEquals(2, this.languageManager.countLanguages());
    }
    
    @Test
    public void it_should_get_the_choices()
    {
        Language en = new EN();
        Language nl = new NL();
        
        this.languageManager.addLanguage(en);
        this.languageManager.addLanguage(nl);
        
        assertEquals("EN, NL", this.languageManager.getKeuzes());
    }
}
