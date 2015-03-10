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
    
    /**
     * Maak een nieuw Spel-object aan
     * 
     * @param id int
     * @param naam String
     */
    public Spel(int id, String naam)
    {
        spelbordMapper = new SpelbordMapper();
        spelborden = spelbordMapper.geefSpelborden(id);
        
        this.id = id;
        this.naam = naam;
    }
    
    /**
     * Stel het spelbord in
     * 
     * @param spelbordnummer int
     */
    public void setSpelbord(int spelbordnummer)
    {
        for(Spelbord spelbord: spelborden)
        {
            if(spelbord.getSpelbordId() == spelbordnummer)
                this.huidigSpelbord = spelbord;
        }
    }
    
    /**
     * Toon het spelbord
     * 
     * @return String[][]
     */
    public String[][] toonSpelbord()
    {
        return huidigSpelbord.toonSpelbord();
    }

    /**
     * Verkrijg het huidig spelbord
     * 
     * @return Spelbord
     */
    public Spelbord getHuidigSpelbord()
    {
        return huidigSpelbord;
    }

    /**
     * Stel het huidig spelbord in
     * 
     * @param spelbord Spelbord
     */
    public void setHuidigSpelbord(Spelbord spelbord)
    {
        this.huidigSpelbord = spelbord;
    }
    
    /**
     * Verkrijg een lijst van spelborden
     * 
     * @return List&lt;Spelbord&gt;
     */
    public List<Spelbord> geefSpelborden()
    {
        return null;
    }

    /**
     * Verkrijg de id
     * 
     * @return int
     */
    public int getId()
    {
        return id;
    }

    /**
     * Stel de id in
     * 
     * @param id int
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Verkrijg de naam
     * 
     * @return String
     */
    public String getNaam()
    {
        return naam;
    }

    /**
     * Stel de naam in
     * 
     * @param naam String
     */
    public void setNaam(String naam)
    {
        this.naam = naam;
    }
       
    
}
