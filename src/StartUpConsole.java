import domein.DomeinController;
import gui.ConsoleApplicatie;
import java.util.Scanner;
import languages.EN;
import languages.FR;
import languages.LanguageManager;
import languages.NL;

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
        
         (new ConsoleApplicatie()).start(
            new DomeinController(lang), 
            new Scanner(System.in)
        );
    }
}
