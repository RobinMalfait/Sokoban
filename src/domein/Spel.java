package domein;

import exceptions.SpelException;
import exceptions.SpelbordException;
import java.util.ArrayList;
import java.util.List;
import persistentie.SpelMapper;
import persistentie.SpelbordMapper;

public class Spel 
{    
    private final SpelbordMapper spelbordMapper;
    private final SpelMapper spelMapper;
    
    private List<Spelbord> spelborden;
    private Spelbord huidigSpelbord;
    
    private int id;
    private String naam;
    
    //CONSTRUCTOREN
 
    public Spel(String naam)
    {
        spelbordMapper = new SpelbordMapper();
        spelMapper = new SpelMapper();
        
        spelborden = new ArrayList<>();
        this.naam = naam;
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
    
    public Spelbord kiesSpelbord(int id)
    {
        for(Spelbord spelbord: spelborden)
        {
            if(spelbord.getSpelbordId() == id)
                return spelbord;
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
    public boolean isEindeSpelbord()
    {
        return huidigSpelbord.isEindeSpelbord();
    }
    
    /**
     * Voeg een spelbord toe, en daarna ook de vakken
     * @param naam String    
     */
    public void voegSpelbordToe(String naam)
    {
        for (Spelbord spelbord : spelborden)
        {
            if (spelbord.getNaam().equals(naam))
                throw new SpelbordException("Er bestaat al een spelbord met deze naam.");
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
     * 
     * @return 
     */
    public boolean controleerSpel()
    {
        for (Spelbord spelbord : spelborden)
        {
            if(spelbord.controleerSpelbord())
                return true;
        }
        return false;        
    }
    
    /**
     * Sla de wijzigingen in het huidige spel op.
     */
    public void slaOp()
    {
        this.id = this.spelMapper.voegSpelToe(naam);
        
        if(id == 0) 
        {
            throw new SpelException("Spel werd niet opgeslaan.");
        }
        else 
        {
            for(Spelbord spelbord: spelborden)
                spelbord.voegToe(this.id);
        }
    }

    public void verwijderSpelbord(int spelbordId)
    {
        Spelbord spelbord = zoekSpelbord(spelbordId);
        
        if (spelbord == null)
            throw new IllegalArgumentException("Het spelbord met id " + spelbordId + " werd niet gevonden in het spel " + this.naam + ".");
        else
        {
            spelbordMapper.verwijderSpelbord(spelbordId);
            spelborden.remove(spelbord);
        }
        
    }
    
    private Spelbord zoekSpelbord(int spelbordId)
    {
        for(Spelbord spelbord : spelborden)
        {
            if(spelbord.getSpelbordId() == spelbordId)
                return spelbord;
        }
        
        return null;
    }
}
