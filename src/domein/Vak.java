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
    private Mannetje mannetje;
    
    /**
     * Maak een nieuw Vak-object aan
     * 
     * @param posX int
     * @param posY int
     * @param doel boolean
     * @param toegankelijk boolean
     */
    public Vak(int posX, int posY, boolean toegankelijk, boolean doel)
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
    public Vak(int posX, int posY, boolean toegankelijk, boolean doel, Kist kist)
    {
        this(posX, posY, toegankelijk, doel);
        this.kist = kist;      
    }
    
    /**
     * Maak een nieuw Vak-object aan
     * 
     * @param posX int
     * @param posY int
     * @param doel boolean
     * @param toegankelijk boolean
     * @param mannetje Mannetje 
     */
    public Vak(int posX, int posY, boolean toegankelijk, boolean doel, Mannetje mannetje)
    {
        this(posX, posY, toegankelijk, doel);
        this.mannetje = mannetje;      
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
    private void setDoel(boolean doel)
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
    private void setToegankelijk(boolean toegankelijk)
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
    private void setKist(Kist kist)
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
    
    /**
     * Stel een mannetje in
     * 
     * @param mannetje Mannetje
     */
    private void setMannetje(Mannetje mannetje)
    {
        this.mannetje = mannetje;
    }
    
    /**
     * Bevat het vak een mannetje?
     * 
     * @return boolean
     */
    public boolean bevatMannetje()
    {
        if(this.mannetje == null)
            return false;
        return true;
    }
    
    
    
}
