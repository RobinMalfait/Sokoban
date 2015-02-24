package languages;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author robin
 */
public class FR implements Language 
{
    private final Map<String, String> languages = new HashMap<>();

    public FR()
    {
        languages.put("user.username", "");
        languages.put("user.name", "");
        languages.put("user.logged.in", "");
        languages.put("user.logged.out", "");
        languages.put("credentials.wrong", "");
    }
    
    @Override
    public String get(String index)
    {
        return this.languages.get(index);
    }
}
