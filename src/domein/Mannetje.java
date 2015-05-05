package domein;

public class Mannetje 
{
    private String richting = "Z";
    
    /**
     * Verkrijg de richting
     * 
     * @return 
     */
    public String getRichting()
    {
        return richting;
    }

    /**
     * Stel de richting in
     * 
     * @param richting 
     */
    public void setRichting(String richting)
    {
        this.richting = richting;
    }
}
