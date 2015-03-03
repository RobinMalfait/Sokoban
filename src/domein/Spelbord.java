package domein;

import java.util.List;
import persistentie.VakMapper;

public class Spelbord
{
    private final int spelbordId;
    private String naam;
    private boolean voltooid = false;
    private int verplaatsingen = 0;
    
    private final VakMapper vakMapper;
    private List<Vak> vakken;
    
    
    public Spelbord(int spelbordId, String naam)
    {
        vakMapper = new VakMapper();
        this.spelbordId = spelbordId;        
        this.naam = naam;   
    }

    public boolean isVoltooid()
    {
        return voltooid;
    }

    public int getVerplaatsingen()
    {
        return verplaatsingen;
    }

    public String getNaam()
    {
        return naam;
    }

    public void verhoogVerplaatsingen()
    {
        verplaatsingen++;
    }

    public void maakVoltooid()
    {
        voltooid = true;
    }
    
    public void geefVakken()
    {
        vakken = vakMapper.geefVakken(spelbordId);
    }
    
    public void toonSpelbord()
    {
        String spelbord = "";
        for(Vak vak: vakken)
        {
            // Hier een string van een Map voor de console ofz.
        }
    }
    
    
}
