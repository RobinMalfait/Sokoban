import domein.DomeinController;
import gui.ConsoleApplicatie;
import gui.TaalkeuzeApplicatie;
import java.util.Scanner;
import languages.EN;
import languages.FR;
import languages.LanguageManager;
import languages.NL;
import languages.WVL;

/**
 *
 * @author robin
 */
public class StartUpConsole 
{
    public static void main(String[] args)
    {
        LanguageManager lang = new LanguageManager();

        lang.addLanguage(new NL());
        lang.addLanguage(new FR());
        lang.addLanguage(new EN());
        lang.addLanguage(new WVL());
        
         (new TaalkeuzeApplicatie()).start(
            new DomeinController(lang), 
            new Scanner(System.in)
        );
    }
}
