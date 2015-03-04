
package domein;

import java.util.List;

public class Spel 
{    
    private final SpelbordRepository spelbordRepository;
    private Spelbord huidigSpelbord;
    
    public Spel(int spelbordnummer)
    {
        spelbordRepository = new SpelbordRepository();
        this.kiesSpelbord(spelbordnummer);
        
    }
    
    public void kiesSpelbord(int spelbordnummer)
    {
        
    }
    
    public String toonSpelbord()
    {
        return huidigSpelbord.toonSpelbord();
    }

    public Spelbord getHuidigSpelbord()
    {
        return huidigSpelbord;
    }

    public void setHuidigSpelbord(Spelbord spelbord)
    {
        this.huidigSpelbord = spelbord;
    }
    
    public List<Spelbord> geefSpelborden()
    {
        return null;
    }
}
