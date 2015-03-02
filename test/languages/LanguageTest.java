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
        assertEquals("bar", this.language.get("foo"));
        assertEquals("baz", this.language.get("bar"));
    }
    
    @Test 
    public void it_should_replace_content()
    {
        String expected = this.language.get("test", 
            "name", "robin"
        );
        
        assertEquals("test robin", expected);
    }
    
    @Test 
    public void it_should_replace_multiple_variables()
    {        
        String expected = this.language.get("multiple", 
            "foo", "a",
            "bar", "b"
        );
        
        assertEquals("replace a and b", expected);
    }
    
    @Test
    public void it_should_convert_int_to_string()
    {
        String expected = this.language.get("test", 
            "name", 123
        );
        
        assertEquals("test 123", expected);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void it_should_throw_an_exception()
    {
        this.language.get("multiple",
            "foo", "a",
            "bar"
        );
    }
    
    public class LanguageImpl extends Language
    {
        public LanguageImpl() 
        {
            map("foo", "bar");
            map("bar", "baz");
            map("test", "test :name");
            map("multiple", "replace :foo and :bar");
        }
    }
    
}
