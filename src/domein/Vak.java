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
    
    //CONSTRUCTOREN
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
    
    //GETTERS
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
     * Is het vak toegankelijk of niet
     * 
     * @return boolean
     */
    public boolean isToegankelijk()
    {
        return toegankelijk;
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
     * Verkrijg het Mannetje
     * 
     * @return Mannetje
     */
    public Mannetje getMannetje()
    {
        return mannetje;
    }
    
    //SETTERS
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
     * Stel toegankelijk in
     * 
     * @param toegankelijk boolean
     */
    public void setToegankelijk(boolean toegankelijk)
    {
        this.toegankelijk = toegankelijk;
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
     * Stel een mannetje in
     * 
     * @param mannetje Mannetje
     */
    public void setMannetje(Mannetje mannetje)
    {
        this.mannetje = mannetje;
    }
    
    //ACTIES
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
    
    /**
     * Is het vak leeg?
     * 
     * @return boolean
     */
    public boolean isLeeg()
    {
        if(!toegankelijk || bevatKist())  //muur of kist;
            return false;
        return true;
    } 
    
    /**
     * Bepaal het databasetype van het vak
     * 
     * @return int
     */
    public int bepaalDatabaseType()
    {
        if(toegankelijk)
        {
            if(mannetje != null)
            {
                return 4;
            }
            if(kist != null)
            {
                return 3;
            }
            if(doel)
            {
                return 2;
            }
            return 0;
        }
        else 
        {
            return 1;
        }
    }
    
}
