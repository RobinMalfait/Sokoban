package domein;

import java.util.ArrayList;
import java.util.List;
import persistentie.VakMapper;

public class Spelbord
{
    private final int spelbordId;       // Hebben we nodig om data uit de database te halen
    private final String naam;          // Naam de van het Spelbord
    private boolean voltooid = false;   // Al dan niet voltooid
    private int verplaatsingen = 0;     // Het aantal verplatsingen
    
    private final VakMapper vakMapper;  // Mapper om de vakken/items uit de database op te halen
    private List<Vak> vakken;           // Een lijst van Vakken om de vakken/items bij te houden.
    
    
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
    
    public String[][] toonSpelbord()
    {
        // 
    }
    
    
}
