package languages;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author robin
 */
public class EN implements Language 
{
    private final Map<String, String> languages = new HashMap<>();

    public EN()
    {
        languages.put("user.username", "username");
        languages.put("user.name", "name");
        languages.put("user.logged.in", "logged in");
        languages.put("user.logged.out", "logged out");
        languages.put("credentials.wrong", "wrong credentials");
    }
    
    @Override
    public String get(String index)
    {
        return this.languages.get(index);
    }
}
