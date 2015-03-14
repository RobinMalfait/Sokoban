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

    public void verplaatsSpeler(int richting)
    {
        huidigSpelbord.verplaatsSpeler(richting);
    }

    public boolean isHuidigSpelbordVoltooid()
    {
        return huidigSpelbord.isSpelbordVoltooid();
    }
    
    public String[][] geefSpelbordenString()
    {
        String[][] spelbordenString = new String[this.spelborden.size()][];

        int teller = 0;
        for(Spelbord spelbord: this.spelborden)
        {
            spelbordenString[teller] = new String[2];
            spelbordenString[teller][0] = String.valueOf(spelbord.getSpelbordId());
            spelbordenString[teller][1] = spelbord.getNaam();
            
            teller++;
        }

        return spelbordenString;        
    }       
    
    public Spelbord bepaalVolgendSpelbord()
    {
        boolean deVolgendeIsDeNieuwe = false;
        
        for(Spelbord spelbord: spelborden)
        {
            if(this.huidigSpelbord == null) // Het eerste spelbord
                return this.huidigSpelbord = spelbord;
            
            if(deVolgendeIsDeNieuwe)
                return this.huidigSpelbord = spelbord;
            
            if(this.huidigSpelbord == spelbord)
            {
                deVolgendeIsDeNieuwe = true;
            }
        }
        return null;        
    }
    
    public boolean isEindeSpel()
    {
        for(Spelbord spelbord: spelborden)
        {
            if(!spelbord.isVoltooid())
                return false;
        }
        return true;
    }
}
