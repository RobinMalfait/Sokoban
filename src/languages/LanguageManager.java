package languages;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author robin
 */
public class LanguageManager 
{
    private Language language;
    
    private final List<Language> languages;

    public LanguageManager()
    {
        this.languages = new ArrayList<>();
        
        this.languages.add(new NL());
        this.languages.add(new EN());
    }
    
    public String getKeuzes()
    {
        String output = "";
        
        for (Language language : languages)
        {
            output += ", " + language.getClass().getSimpleName();
        }
        
        return output.substring(2);
    }
    
    public boolean setLanguage(String newLanguage)
    {
        for (Language language : languages)
        {
            if (language.getClass().getSimpleName().equals(newLanguage))
            {
                this.language = language;
                return true;
            }
        }
        
        return false;
    }
    
    public String get(String index)
    {
        return this.language.get(index);
    }
    
    
}
