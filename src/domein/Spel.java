
package domein;

import java.util.List;
import persistentie.SpelbordMapper;

public class Spel 
{    
    private final SpelbordMapper spelbordMapper;
    private final List<Spelbord> spelborden;
    private Spelbord huidigSpelbord;
    
    private int id;
    private String naam;
    
    public Spel(int id, String naam)
    {
        spelbordMapper = new SpelbordMapper();
        spelborden = spelbordMapper.geefSpelborden(id);
        
        this.id = id;
        this.naam = naam;
    }
    
    public void setSpelbord(int spelbordnummer)
    {
        for(Spelbord spelbord: spelborden)
        {
            if(spelbord.getSpelbordId() == spelbordnummer)
                this.huidigSpelbord = spelbord;
        }
    }
    
    public String[][] toonSpelbord()
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
