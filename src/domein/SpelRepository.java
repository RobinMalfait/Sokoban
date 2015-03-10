
package domein;

import java.util.ArrayList;
import java.util.List;
import persistentie.SpelMapper;

class SpelRepository 
{
    private final SpelMapper spelMapper;
    private List<Spel> spellen;

    public SpelRepository()
    {
        this.spelMapper = new SpelMapper();
        this.spellen = new ArrayList<>();
    }

    public List<Spel> geefSpellen()
    {
        spellen = spelMapper.geefSpellen();
        return spellen;
    }
    
    public Spel zoekSpel(int spelnummer)
    {
        return null;
    }
    
    public String[][] geefSpellenString()
    {
        this.geefSpellen();
        String[][] spellenString = new String[this.spellen.size()][];
        
        int teller = 0;
        for(Spel spel: this.spellen)
        {
            spellenString[teller] = new String[2];
            spellenString[teller][0] = String.valueOf(spel.getId());
            spellenString[teller++][1] = spel.getNaam();
        }
            
        return spellenString;
    }
}
