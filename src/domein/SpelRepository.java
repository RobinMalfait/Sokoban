package domein;

import exceptions.SpelException;
import java.util.ArrayList;
import java.util.List;
import languages.LanguageManager;
import persistentie.SpelMapper;

class SpelRepository 
{
    private final SpelMapper spelMapper;
    private List<Spel> spellen;
    private final LanguageManager lang;

    /**
     * Maak een nieuw SpelRepository-object aan
     */
    public SpelRepository(LanguageManager lang)
    {
        this.spelMapper = new SpelMapper();
        this.spellen = new ArrayList<>();
        this.spellen = this.geefSpellen();
        this.lang = lang;
    }

    /**
     * Verkrijg een lijst van spellen
     * 
     * @return List&lt;Spel&gt;
     */
    private List<Spel> geefSpellen()
    {
        return spelMapper.geefSpellen();
    }
    
    /**
     * Zoek een spel op basis van spelnummer
     * 
     * @param spelnummer int
     * @return Spel
     */
    public Spel zoekSpel(int spelnummer)
    {
        for(Spel spel: spellen)
        {
            if(spel.getId() == spelnummer)
                return spel;
        }
        return null;
    }
    
    /**
     * Geef een lijst van spellen
     * 
     * @return String[][]
     */
    public String[][] geefLijstSpellen()
    {
        String[][] spellenLijst = new String[this.spellen.size()][];

        int teller = 0;
        for(Spel spel: this.spellen)
        {
            spellenLijst[teller] = new String[2];
            spellenLijst[teller][0] = String.valueOf(spel.getId());
            spellenLijst[teller][1] = spel.getNaam();
            
            teller++;
        }

        return spellenLijst;
    }
    
    /**
     * Voegt een spel toe
     * 
     * @param naam String
     */
    public Spel voegSpelToe(String naam)
    {       
        for(Spel spel: spellen)
        {
            if(spel.getNaam().equals(naam))
            {
                throw new SpelException(lang.get("game.alreadyExists", ":name", naam));
            }
            else 
            {
                Spel nieuwSpel = new Spel(naam);
                this.spellen.add(nieuwSpel);
                return nieuwSpel;
            }                
        }
        return null;
    }

    void verwijderSpel(int spelId)
    {
        Spel spel = zoekSpel(spelId);
        
        if (spel == null)
            throw new SpelException(lang.get("game.notFound", ":id", spelId));
        
        spel.verwijderAlleSpelborden();
        
        spelMapper.verwijderSpel(spelId);
    }
    
}
