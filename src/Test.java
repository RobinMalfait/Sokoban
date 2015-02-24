import domein.Speler;
import java.util.List;
import persistentie.SpelerMapper;

/**
 *
 * @author robin
 */
public class Test 
{
    public static void main(String[] args)
    {
        List<Speler> spelers = (new SpelerMapper()).geefSpelers();
    }
}
