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
    
    public int countLanguages()
    {
        return this.languages.size();
    }

    public void addLanguage(Language lang)
    {
        this.languages.add(lang);
    }

    public String getKeuzes()
    {
        String output = "";

        for (Language lang : languages)
        {
            output += ", " + lang.getClass().getSimpleName();
        }

        return output.substring(2);
    }

    public boolean setLanguage(String newLanguage)
    {
        for (Language lang : languages)
        {
            if (language.getClass().getSimpleName().equals(newLanguage))
            {
                this.language = lang;
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
