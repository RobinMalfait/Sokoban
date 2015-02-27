package languages;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author robin
 */
public class LanguageTest
{
    
    private Language language;

    @Before
    public void setUp()
    {
        this.language = new LanguageImpl();
    }

    @Test
    public void it_should_map_and_get_stuff()
    {
        this.language.map("foo", "bar");
        this.language.map("bar", "baz");
        
        assertEquals("bar", this.language.get("foo"));
        assertEquals("baz", this.language.get("bar"));
    }
    
    public class LanguageImpl extends Language
    {
    }
    
}
