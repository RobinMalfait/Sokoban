
package domein;

public class Spel 
{    
    private final SpelbordRepository spelbordRepository;
    private Spelbord spelbord;
    
    public Spel(int huidigSpelbord)
    {
        spelbordRepository = new SpelbordRepository();
        
    }
    public void kiesSpelbord(int spelbordnummer)
    {
        spelbord = spelbordRepository.geefSpelbord(spelbordnummer);
    }
    
    public void toonSpelbord()
    {
        spelbord.toonSpelbord();
    }
}
