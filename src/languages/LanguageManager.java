package languages;

import exceptions.TaalException;
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
        String delimeter = ", ";

        for (Language lang : languages)
        {
            output += delimeter + lang.getClass().getSimpleName();
        }

        return output.substring(delimeter.length());
    }

    /**
     * Stel de huidige taal in.
     * 
     * @param newLanguage String
     */
    public void setLanguage(String newLanguage)
    {
        for (Language lang : this.languages)
        {
            if (lang.getClass().getSimpleName().equals(newLanguage))
            {
                this.language = lang;
            }
        }
        if(this.language == null)
            throw new TaalException("De taal die u invoerde werd niet teruggevonden.");
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
