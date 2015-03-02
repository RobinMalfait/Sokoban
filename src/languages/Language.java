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
     * Verkrijg de value voor een bepaalde key, en vervang de variabelen.
     * 
     * @param key
     * @param replacements
     * @return 
     */
    public String get(String key, Object... replacements)
    {
        String data = this.get(key);
        
        if (replacements.length % 2 != 0) {
            throw new IllegalArgumentException("Keys and values must be pairs.");
        }
        
        for(int i = 0; i < replacements.length; i += 2)
        {
            String k = (String) replacements[i].toString();
            String v = (String) replacements[i + 1].toString();
            data = data.replace(":" + k, v);
        }
        
        return data;
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
