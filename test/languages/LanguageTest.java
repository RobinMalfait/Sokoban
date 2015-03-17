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
        String given = this.language.get("test", 
            "name", "demian"
        );
        
        assertEquals("test demian", given);
    }
    
    @Test 
    public void it_should_replace_multiple_variables()
    {        
        String given = this.language.get("multiple", 
            "foo", "a",
            "bar", "b"
        );
        
        assertEquals("replace a and b", given);
    }
    
    @Test
    public void it_should_convert_int_to_string()
    {
        String given = this.language.get("test", 
            "name", 123
        );
        
        assertEquals("test 123", given);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void it_should_throw_an_exception()
    {
        this.language.get("multiple",
            "foo", "a",
            "bar" // Missing replacement, should throw an error!
        );
    }
    
    @Test
    public void it_should_give_a_singular_response()
    {
        String given = this.language.choice("singular_plural", 1);
        
        assertEquals("singular", given);
    }
    
    @Test 
    public void it_should_give_a_plural_response()
    {
        String given = this.language.choice("singular_plural", 42);
        
        assertEquals("plural", given);
    }
           
    @Test 
    public void it_should_give_a_plural_response_for_zero()
    {
        String given = this.language.choice("singular_plural", 0);
        
        assertEquals("plural", given);
    }
    
    @Test
    public void it_should_give_a_singular_response_with_replacements()
    {
        int punten = 1;
        String given = this.language.choice("punten", punten,
            "score", punten,
            "speler", "Bram"
        );
        
        assertEquals("Speler Bram heeft een score van 1 punt", given);
    }
    
    @Test
    public void it_should_give_a_plural_response_with_replacements()
    {
        int punten = 900;
        String given = this.language.choice("punten", punten,
            "score", punten,
            "speler", "bob"
        );
        
        assertEquals("Speler bob heeft een score van 900 punten", given);
    }
    
    @Test
    public void it_can_have_variables_in_1_side()
    {
        int x = 12;
        
        String given1 = this.language.choice("partial_variables", x,
            "x", x
        );
        assertEquals("deel 12", given1);
        
        String given2 = this.language.choice("partial_variables", 1);
        assertEquals("deel 1", given2);
        
    }
    
    @Test
    public void it_should_give_the_key_if_no_value_is_represent()
    {
        String given = this.language.get("test.jibberish");
        
        assertEquals("test.jibberish", given);
    }
    
    public class LanguageImpl extends Language
    {
        public LanguageImpl() 
        {
            map("foo", "bar");
            map("bar", "baz");
            map("test", "test :name");
            map("multiple", "replace :foo and :bar");
            map("singular_plural", "singular|plural");
            map("punten", "Speler :speler heeft een score van :score punt|Speler :speler heeft een score van :score punten");
            map("partial_variables", "deel 1|deel :x");
        }
    }
    
}
