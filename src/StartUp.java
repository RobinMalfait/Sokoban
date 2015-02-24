
import domein.DomeinController;
import gui.MeldAanApplicatie;
import java.sql.SQLException;

/**
 *
 * @author robin
 */
public class StartUp 
{
    public static void main(String[] args) throws SQLException
    {
        (new MeldAanApplicatie()).start(new DomeinController());
    }
}
