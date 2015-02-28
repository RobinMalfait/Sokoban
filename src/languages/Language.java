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
     * Verkrijg de value voor een bepaalde key.
     * 
     * @param key String
     * @return String
     */
    public String get(String key)
    {
        return this.languages.get(key);
    }
    
    /**
     * Map een key aan een value.
     * 
     * @param key String
     * @param value String
     */
    public void map(String key, String value)
    {
        this.languages.put(key, value);
    }
}
