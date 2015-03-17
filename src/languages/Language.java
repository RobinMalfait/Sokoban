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
        String value = this.languages.get(key);
        
        return value == null ? key : value;
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
        
        return this.replaceData(data, replacements);
    }
    
    /**
     * Choose between singular and plural
     * 
     * @param key
     * @param count
     * @return 
     */
    public String choice(String key, int count)
    {
        String data = this.get(key);
        
        return this.choose(data, count);
    }

    /**
     * Choose between singular and plural + replacements
     * 
     * @param key
     * @param count
     * @param replacements
     * @return 
     */
    public String choice(String key, int count, Object... replacements)
    {
        String data = this.get(key);
        
        data = this.choose(data, count);
        
        return this.replaceData(data, replacements);
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
    
    /**
     * Replace data in a given String
     * 
     * @param data
     * @param replacements
     * @return
     * @throws IllegalArgumentException 
     */
    private String replaceData(String data, Object[] replacements) throws IllegalArgumentException
    {
        if (replacements.length % 2 != 0) {
            throw new IllegalArgumentException("Keys and values must be pairs.");
        }
        
        for(int i = 0; i < replacements.length; i += 2)
        {
            String k = replacements[i].toString();
            String v = replacements[i + 1].toString();
            data = data.replace(":" + k, v);
        }
        
        return data;
    }
    
    /**
     * Kies enkelvoud of meervoud
     * 
     * @param data String
     * @param count int
     * @return String
     */
    private String choose(String data, int count)
    {
        String parts[] = data.split("\\|");
        
        if (count == 1) {
            return parts[0];
        }
        
        return parts[1];
    }
}
