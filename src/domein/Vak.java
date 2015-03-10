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
    
    /**
     * Maak een nieuw Vak-object aan
     * 
     * @param posX int
     * @param posY int
     * @param doel boolean
     * @param toegankelijk boolean
     */
    public Vak(int posX, int posY, boolean doel, boolean toegankelijk)
    {
        this.posX = posX;
        this.posY = posY;
        this.doel = doel;
        this.toegankelijk = toegankelijk;
    }

    /**
     * Maak een nieuw Vak-object aan
     * 
     * @param posX int
     * @param posY int
     * @param doel boolean
     * @param toegankelijk boolean
     * @param kist Kist 
     */
    public Vak(int posX, int posY, boolean doel, boolean toegankelijk, Kist kist)
    {
        this(posX, posY, doel, toegankelijk);
        this.kist = kist;      
    }
    
    /**
     * Verkrijg de X positie
     * 
     * @return int
     */
    public int getPosX()
    {
        return posX;
    }

    /**
     * Verkrijg de Y positie
     * 
     * @return int
     */
    public int getPosY()
    {
        return posY;
    }

    /**
     * Is het een doel?
     * 
     * @return boolean
     */
    public boolean isDoel()
    {
        return doel;
    }

    /**
     * Stel doel in
     * 
     * @param doel boolean
     */
    public void setDoel(boolean doel)
    {
        this.doel = doel;
    }

    /**
     * Is het vak toegankelijk of niet
     * 
     * @return boolean
     */
    public boolean isToegankelijk()
    {
        return toegankelijk;
    }

    /**
     * Maak het vak toegankelijk of niet
     * 
     * @param toegankelijk boolean
     */
    public void setToegankelijk(boolean toegankelijk)
    {
        this.toegankelijk = toegankelijk;
    }

    /**
     * Verkrijg de kist
     * 
     * @return Kist
     */
    public Kist getKist()
    {
        return kist;
    }

    /**
     * Stel een kist in 
     * 
     * @param kist Kist
     */
    public void setKist(Kist kist)
    {
        this.kist = kist;
    }
    
    /**
     * Bevat het vak een kist?
     * 
     * @return boolean
     */
    public boolean bevatKist()
    {
        if(this.kist == null)
            return false;
        return true;
    }
    
    
    
}
