package domein;

import exceptions.SpelException;
import exceptions.SpelbordException;
import java.util.ArrayList;
import java.util.List;
import persistentie.SpelMapper;
import persistentie.SpelbordMapper;

public class Spel extends Base
{    
    private final SpelbordMapper spelbordMapper;
    private final SpelMapper spelMapper;
    
    private List<Spelbord> spelborden;
    private Spelbord huidigSpelbord;
    
    private int id;
    private String naam;
    /**
     * Maak een nieuw Spel-bject aan
     * 
     * @param naam 
     */
    public Spel(String naam)
    {
        spelbordMapper = new SpelbordMapper();
        spelMapper = new SpelMapper();
        
        spelborden = new ArrayList<>();
        this.naam = naam;
        
        lang.get(naam);
    }
    
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
        
        spelMapper = new SpelMapper();
        this.id = id;
        this.naam = naam;
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
    public String[][] geefLijstSpelborden()
    {
        String[][] spelbordenLijst = new String[this.spelborden.size()][];

        int teller = 0;
        for(Spelbord spelbord: this.spelborden)
        {
            spelbordenLijst[teller] = new String[2];
            spelbordenLijst[teller][0] = String.valueOf(spelbord.getSpelbordId());
            spelbordenLijst[teller][1] = spelbord.getNaam();
            
            teller++;
        }

        return spelbordenLijst;
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
    
    public void kiesSpelbord(int id)
    {
        this.huidigSpelbord = null;
        
        for(Spelbord spelbord: spelborden)
        {
            if(spelbord.getSpelbordId() == id)
                this.huidigSpelbord = spelbord;
        }
        
        if(this.huidigSpelbord == null)
            throw new SpelException("Kon geen Spelbord kiezen voor het huidig spel.");
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
    public boolean isEindeSpelbord()
    {
        return huidigSpelbord.isEindeSpelbord();
    }
    
    /**
     * Voeg een spelbord toe, en daarna ook de vakken
     * 
     * @param naam String    
     */
    public void voegSpelbordToe(String naam)
    {
        for (Spelbord spelbord : spelborden)
        {
            if (spelbord.getNaam().equals(naam))
                throw new SpelbordException(lang.get("game.board.exists"));
        }
                
        Spelbord nieuwSpelbord = new Spelbord(naam);
        
        this.huidigSpelbord = nieuwSpelbord;
        this.spelborden.add(nieuwSpelbord);               
    }

    /**
     * Stel de vakken van het huidige spelbord in op hun beginwaarde.
     */
    public void resetSpelbord()
    {
        huidigSpelbord.resetSpelbord();
    }

    /**
     * Geef het aantal verplaatsingen van het huidige spelbord.
     * 
     * @return int
     */
    public int geefAantalVerplaatsingen()
    {
        return this.huidigSpelbord.getVerplaatsingen();
    }
    
    /**
     * Plaats een item op een vak.
     * 
     * @param coordinaat String
     * @param naam String
     */
    public void voerVakIn(String coordinaat, String naam)
    {
        this.huidigSpelbord.voerVakIn(coordinaat, naam);
    }    
    
    /** 
     * Controleer of het gewijzigde spel aan de eisen voldoet.
     * Elk spelbord moet evenveel doelen als kisten bevatten en juist één mannetje hebben.
     */
    public void controleerSpel()
    {
        if(geefCorrecteSpelborden().isEmpty())
            throw new SpelException("Het spel kent geen voltooide spelborden");
    }
     
    /**
     * Controleer het spelbord
     */
    public void controleerSpelbord()
    {
        this.huidigSpelbord.controleerSpelbord();
    }   
    /**
     * Sla de wijzigingen in het huidige spel op.
     */
    public void slaOp()
    {
        this.id = this.spelMapper.voegSpelToe(naam);
        
        if(id == 0) 
        {
            throw new SpelException(lang.get("game.notSaved"));
        }
        else 
        {
            for(Spelbord spelbord: spelborden)
                spelbord.voegToe(this.id);
        }
    }

    /**
     * Wijzig het spelbord, sla het op.
     */
    public void wijzigSpelbord()
    {
        this.huidigSpelbord.slaOp();
        
    }
     
    /**
     * Verwijder een spelbord
     * 
     * @param spelbordId 
     */
    public void verwijderSpelbord(int spelbordId)
    {
        Spelbord spelbord = zoekSpelbord(spelbordId);
        
        if (spelbord == null)
            throw new SpelbordException(lang.get("game.board.notFound", ":id", spelbordId));
        else
        {
            spelbordMapper.verwijderSpelbord(spelbordId);
            spelborden.remove(spelbord);
        }
    }
    
    /**
     * Zoek een spel
     * 
     * @param spelbordId
     * @return 
     */
    private Spelbord zoekSpelbord(int spelbordId)
    {
        for(Spelbord spelbord : spelborden)
        {
            if(spelbord.getSpelbordId() == spelbordId)
                return spelbord;
        }
        
        return null;
    }

    /**
     * Verwijder alle spelborden
     */
    public void verwijderAlleSpelborden()
    {
        for(Spelbord spelbord : spelborden)
        {
            spelbordMapper.verwijderSpelbord(spelbord.getSpelbordId());
        }
    }
    
    /**
     * Geef voltooide Spelborden
     * 
     * @return List&gt;Spelbord&lt;
     */
    public List<Spelbord> geefCorrecteSpelborden()
    {
        List<Spelbord> voltooideSpelborden = new ArrayList<>();
        for (Spelbord spelbord : spelborden)
        {
            try 
            {
                spelbord.controleerSpelbord();
                voltooideSpelborden.add(spelbord);
            }
            catch(SpelbordException e)
            {
                
            }
        }   
        return voltooideSpelborden;
    }

    /**
     * Geef het anatal voltooide spelborden
     * 
     * @return int
     */
    public int geefAantalVoltooideSpelborden()
    {
        int aantal = 0;
        for(Spelbord spelbord: spelborden)
            if(spelbord.isVoltooid())
                aantal++;
        
        return aantal;
    }

    /**
     * Geef het anatal spelborden
     * 
     * @return int
     */
    public int geefAantalSpelborden()
    {
        return this.spelborden.size();
    }
}
