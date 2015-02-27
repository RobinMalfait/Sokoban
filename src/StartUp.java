import domein.DomeinController;
import gui.MeldAanApplicatie;
import gui.RegistreerApplicatie;
import gui.TaalkeuzeApplicatie;
import java.util.Scanner;
import languages.EN;
import languages.FR;
import languages.NL;
import languages.LanguageManager;

/**
 *
 * @author robin
 */
public class StartUp
{

    public static void main(String[] args)
    {
        
        DomeinController dc = new DomeinController();
        Scanner input = new Scanner(System.in);

        (new TaalkeuzeApplicatie()).start(dc, input);

    }
}
