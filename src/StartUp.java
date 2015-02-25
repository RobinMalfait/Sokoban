import domein.DomeinController;
import gui.MeldAanApplicatie;
import languages.EN;
import languages.FR;
import languages.LanguageManager;
import languages.NL;

/**
 *
 * @author robin
 */
public class StartUp
{

    public static void main(String[] args)
    {
        LanguageManager languageManager = new LanguageManager();

        languageManager.addLanguage(new NL());
        languageManager.addLanguage(new FR());
        languageManager.addLanguage(new EN());

        DomeinController dc = new DomeinController(languageManager);

        (new MeldAanApplicatie()).start(dc);
    }
}
