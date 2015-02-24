import domein.DomeinController;
import gui.MeldAanApplicatie;

/**
 *
 * @author robin
 */
public class StartUp 
{
    public static void main(String[] args)
    {
        (new MeldAanApplicatie()).start(new DomeinController());
    }
}
