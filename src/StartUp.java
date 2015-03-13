import domein.DomeinController;
import gui.ConsoleApplicatie;
import gui.SpeelSpelSimpeleApplicatie;
import java.util.Scanner;
import languages.LanguageManager;
/**
 *
 * @author robin
 */
public class StartUp
{

    public static void main(String[] args)
    {
        (new ConsoleApplicatie()).start(
            new DomeinController(), 
            new Scanner(System.in)
        );

    }
}
