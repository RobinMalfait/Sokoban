
package domein;

import java.util.List;

public class Spel 
{    
    private final SpelbordRepository spelbordRepository;
    private Spelbord huidigSpelbord;
    
    private int id;
    private String naam;
    
    public Spel(int spelbordnummer)
    {
        spelbordRepository = new SpelbordRepository();
        this.kiesSpelbord(spelbordnummer);
    }
    
    public Spel(int id, String naam)
    {
        spelbordRepository = new SpelbordRepository();
        
        this.id = id;
        this.naam = naam;
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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNaam()
    {
        return naam;
    }

    public void setNaam(String naam)
    {
        this.naam = naam;
    }
       
    
}
