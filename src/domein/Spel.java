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
    
    //CONSTRUCTOREN
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
    
    //GETTERS
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
     * Verkrijg de naam
     * 
     * @return String
     */
    public String getNaam()
    {
        return naam;
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
     * Geeft een lijst van spelborden terug
     *
     * @return List&lt;Spelbord&gt;
     */
    public List<Spelbord> getSpelborden()
    {
        return spelborden;
    }
    
    
    //SETTERS  
    /**
     * Stel de id in
     * 
     * @param id int
     */
    private void setId(int id)
    {
        this.id = id;
    }
    
    /**
     * Stel de naam in
     * 
     * @param naam String
     */
    private void setNaam(String naam)
    {
        this.naam = naam;
    }
    
    /**
     * Setter om het huidig spelbord bij te houden
     *
     * @param huidigSpelbord Spelbord
     */
    public void setHuidigSpelbord(Spelbord huidigSpelbord)
    {
        this.huidigSpelbord = huidigSpelbord;
    }
    
    /**
     * Stel de spelborden in
     *
     * @param spelborden List&lt;Spelbord&gt;
     */
    public void setSpelborden(List<Spelbord> spelborden)
    {
        this.spelborden = spelborden;
    }
    
    //ACTIES   
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
     * Verplaats de speler (en bijhorende items) volgens een richting
     * 
     * @param richting int
     */
    public void verplaatsSpeler(int richting)
    {
        huidigSpelbord.verplaatsSpeler(richting);
    }

    /**
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
    
    /**
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
    
    /**
     * Controleer of het huidig spelbord van het spel voltooid is
     * 
     * @return boolean
     */
    public boolean isEindeHuidigSpelbord()
    {
        return huidigSpelbord.isSpelbordVoltooid();
    }
    
    /**
     * Voeg een spelbord toe, en daarna ook de vakken
     * @param naam String
     * @param vakken int[][]    
     */
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
