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
        (new ConsoleApplicatie()).start(
            new DomeinController(), 
            new Scanner(System.in)
        );

    }
}
