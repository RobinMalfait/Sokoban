package languages;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author robin
 */
abstract public class Language
{
    protected final Map<String, String> languages = new HashMap<>();
    
    /**
     * 
     * @param index
     * @return 
     */
    public String get(String index)
    {
        return this.languages.get(index);
    }
    
    /**
     * 
     * @param key
     * @param value 
     */
    public void map(String key, String value)
    {
        this.languages.put(key, value);
    }
}
