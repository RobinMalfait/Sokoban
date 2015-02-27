import domein.DomeinController;
import gui.ConsoleApplicatie;
import java.util.Scanner;
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

        (new ConsoleApplicatie()).start(dc, input);

    }
}
