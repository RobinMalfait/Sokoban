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
    }
    
    /**
     * 
     * @return 
     */
    public int countLanguages()
    {
        return this.languages.size();
    }

    /**
     * 
     * @param lang 
     */
    public void addLanguage(Language lang)
    {
        this.languages.add(lang);
    }

    /**
     * 
     * @return 
     */
    public String getKeuzes()
    {
        String output = "";

        for (Language lang : languages)
        {
            output += ", " + lang.getClass().getSimpleName();
        }

        return output.substring(2);
    }

    /**
     * 
     * @param newLanguage
     * @return 
     */
    public boolean setLanguage(String newLanguage)
    {
        for (Language lang : languages)
        {
            if (lang.getClass().getSimpleName().equals(newLanguage))
            {
                this.language = lang;
                return true;
            }
        }

        return false;
    }

    /**
     * 
     * @param index
     * @return 
     */
    public String get(String index)
    {
        return this.language.get(index);
    }

}
