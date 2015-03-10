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
public class Vak
{
    private final int posX;
    private final int posY;

    private boolean doel;
    private boolean toegankelijk;
    
    private Kist kist;
    
    public Vak(int posX, int posY, boolean doel, boolean toegankelijk)
    {
        this.posX = posX;
        this.posY = posY;
        this.doel = doel;
        this.toegankelijk = toegankelijk;
    }

    public Vak(int posX, int posY, boolean doel, boolean toegankelijk, Kist kist)
    {
        this(posX, posY, doel, toegankelijk);
        this.kist = kist;      
    }
    public int getPosX()
    {
        return posX;
    }

    public int getPosY()
    {
        return posY;
    }

    public boolean isDoel()
    {
        return doel;
    }

    public void setDoel(boolean doel)
    {
        this.doel = doel;
    }

    public boolean isToegankelijk()
    {
        return toegankelijk;
    }

    public void setToegankelijk(boolean toegankelijk)
    {
        this.toegankelijk = toegankelijk;
    }

    public Kist getKist()
    {
        return kist;
    }

    public void setKist(Kist kist)
    {
        this.kist = kist;
    }
    
    public boolean bevatKist()
    {
        if(this.kist == null)
            return false;
        return true;
    }
    
    
    
}
