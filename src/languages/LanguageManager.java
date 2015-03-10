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

    /**
     * Maak een nieuw LanguageManager-object aan
     */
    public LanguageManager()
    {
        this.languages = new ArrayList<>();
    }
    
    /**
     * Tel hoeveel talen er geregistreerd zijn.
     * 
     * @return int
     */
    public int countLanguages()
    {
        return this.languages.size();
    }

    /**
     * Voeg een taal toe.
     * 
     * @param lang Language
     */
    public void addLanguage(Language lang)
    {
        this.languages.add(lang);
    }

    /**
     * Verkrijg een lijst van keuzes.
     * 
     * @return String
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
     * Stel de huidige taal in.
     * 
     * @param newLanguage String
     * @return boolean
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
     * Verkrijg de value voor een bepaalde key.
     * @param key String
     * @return String
     */
    public String get(String key)
    {
        return this.language.get(key);
    }

}
