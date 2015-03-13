package domein;

import java.util.ArrayList;
import java.util.List;
import persistentie.SpelMapper;

class SpelRepository 
{
    private final SpelMapper spelMapper;
    private List<Spel> spellen;

    /**
     * Maak een nieuw SpelRepository-object aan
     */
    public SpelRepository()
    {
        this.spelMapper = new SpelMapper();
        this.spellen = new ArrayList<>();
        this.geefSpellen();
    }

    
    /**
     * Verkrijg een lijst van spellen
     * 
     * @return List&lt;Spel&gt;
     */
    public List<Spel> geefSpellen()
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
    public String[][] geefSpellenString()
    {
        String[][] spellenString = new String[this.spellen.size()][];

        int teller = 0;
        for(Spel spel: this.spellen)
        {
            spellenString[teller] = new String[2];
            spellenString[teller][0] = String.valueOf(spel.getId());
            spellenString[teller][1] = spel.getNaam();
            
            teller++;
        }

        return spellenString;
    }
}
