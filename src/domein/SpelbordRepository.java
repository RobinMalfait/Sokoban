    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import persistentie.SpelbordMapper;

/**
 *
 * @author Demian
 */
public class SpelbordRepository
{
    private final SpelbordMapper spelbordMapper;
    private List<Spelbord> spelborden;

    public SpelbordRepository()
    {
        this.spelbordMapper = new SpelbordMapper();
        this.spelborden = new ArrayList<>();
    }
    
    public List<Spelbord> geefSpelborden()
    {
        spelborden = spelbordMapper.geefSpelborden();
        return spelborden;
    }
    
    public Spelbord kiesSpelbord(int spelbordnummer)
    {
        Spelbord spelbord = spelbordMapper.geefSpelbord(spelbordnummer);
        if(spelbord == null)
            throw new IllegalArgumentException("Het spelbod met opgegeven spelbordnummer bestond niet.");
        
        return spelbord;
    }
}
