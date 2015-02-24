package gui;

import domein.Speler;
import java.util.List;
import persistentie.SpelerMapper;

/**
 *
 * @author robin
 */
public class MeldAanApplicatie 
{
    public void start()
    {
        List<Speler> spelers = (new SpelerMapper()).geefSpelers();
        
        for(Speler speler : spelers)
        {
            System.out.print(speler);
        }
    }
}
