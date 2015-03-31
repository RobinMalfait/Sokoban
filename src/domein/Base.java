
package domein;

import languages.LanguageManager;

public class Base 
{
    protected static LanguageManager lang;

    public static void setLang(LanguageManager lang)
    {
        Base.lang = lang;
    }

    public static void setLanguage(String lang)
    {
        Base.lang.setLanguage(lang);
    }
    
    
    
}
