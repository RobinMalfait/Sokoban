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
        languages.put("user.username", "gebruikersnaam");
        languages.put("user.name", "naam");
        languages.put("user.logged.in", "ingelogd");
        languages.put("user.logged.out", "uitgelogd");
        languages.put("credentials.wrong", "foute gegevens");
    }
    
    @Override
    public String get(String index)
    {
        return this.languages.get(index);
    }
}
