
package domein;

public class Spel 
{    
    private final SpelbordRepository spelbordRepository;
    private Spelbord spelbord;
    
    public Spel(int spelbordnummer)
    {
        spelbordRepository = new SpelbordRepository();
        this.kiesSpelbord(spelbordnummer);
        
    }
    public void kiesSpelbord(int spelbordnummer)
    {
        spelbord = spelbordRepository.kiesSpelbord(spelbordnummer);
    }
    
    public String toonSpelbord()
    {
        return spelbord.toonSpelbord();
    }

    public Spelbord getSpelbord()
    {
        return spelbord;
    }

    public void setSpelbord(Spelbord spelbord)
    {
        this.spelbord = spelbord;
    }
}
