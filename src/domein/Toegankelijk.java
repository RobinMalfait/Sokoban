/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author Demian
 */
public class Toegankelijk extends Vak
{
    private final boolean doel;
    
    public Toegankelijk(int posX, int posY, boolean doel)
    {
        super(posX, posY);
        this.doel = doel;
    }

    public boolean isDoel()
    {
        return doel;
    }
    
    
}
