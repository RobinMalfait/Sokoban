package domein;

import java.util.List;
import persistentie.SpelbordMapper;

public class Spel 
{    
    private final SpelbordMapper spelbordMapper;
    private List<Spelbord> spelborden;
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
        System.out.println(huidigSpelbord.getSpelbordId());
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

    /**
     * Verplaats de speler (en bijhorende items) volgens een richting
     * 
     * @param richting int
     */
    public void verplaatsSpeler(int richting)
    {
        huidigSpelbord.verplaatsSpeler(richting);
    }

    /**
     * Controleer of het huidig spelbord van het spel voltooid is
     * 
     * @return boolean
     */
    public boolean isHuidigSpelbordVoltooid()
    {
        return huidigSpelbord.isSpelbordVoltooid();
    }

    /*
     * Geeft een 2-dimensionele array van alle Spellen terug.
     *
     * @return String[][]
     */
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
    
    /**
     * Bepaald het volgend spelbord van het huidig Spel
     * 
     * @return Spelbord
     */    
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
    
    /*
     * Controleer of alle spelborden voltooid zijn. Zoja, is het spel voltooid
     *
     * @return boolean
     */
    public boolean isEindeSpel()
    {
        for(Spelbord spelbord: spelborden)
        {
            if(!spelbord.isVoltooid())
                return false;
        }
        return true;
    }

    public void setSpelborden(List<Spelbord> spelborden)
    {
        this.spelborden = spelborden;
    }

    public List<Spelbord> getSpelborden()
    {
        return spelborden;
    }

    public void setHuidigSpelbord(Spelbord huidigSpelbord)
    {
        this.huidigSpelbord = huidigSpelbord;
    }
    
    public void voegSpelbordToe(String naam, int vakken[][])
    {
        int id = this.spelbordMapper.voegSpelbordToe(naam, this.id);
        
        // 0 is de standaardwaarde die geretourneerd wordt.
        if(id == 0) 
        {
            throw new IllegalArgumentException("Het spelbord werd niet toegevoegd");
        }
        else 
        {
            Spelbord nieuwSpelbord = new Spelbord(id, naam);
            this.spelborden.add(nieuwSpelbord);
            
            nieuwSpelbord.configureerSpelbord(vakken);
        }        
    }
    
    
}
