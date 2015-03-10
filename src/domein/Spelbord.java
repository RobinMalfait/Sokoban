package domein;

import persistentie.VakMapper;

public class Spelbord
{

    private final int spelbordId;       // Hebben we nodig om data uit de database te halen
    private final String naam;          // Naam de van het Spelbord
    private boolean voltooid = false;   // Al dan niet voltooid
    private int verplaatsingen = 0;     // Het aantal verplatsingen

    private final VakMapper vakMapper;  // Mapper om de vakken/items uit de database op te halen
    private Vak[][] vakken;             // Een lijst van Vakken om de vakken/items bij te houden.

    /**
     * Maak een nieuw Spelbord object-aan
     * 
     * @param nummer int
     * @param naam String
     */
    public Spelbord(int nummer, String naam)
    {
        vakMapper = new VakMapper();
        this.spelbordId = nummer;
        this.naam = naam;
    }

    /**
     * Is het spelbord voltooid of niet?
     * 
     * @return boolean
     */
    public boolean isVoltooid()
    {
        return voltooid;
    }

    /**
     * Verkrijg het aantal verplaatsingen
     * 
     * @return int
     */
    public int getVerplaatsingen()
    {
        return verplaatsingen;
    }

    /**
     * Verkrijg de naam
     * 
     * @return String
     */
    public String getNaam()
    {
        return naam;
    }

    /**
     * Verkrijg het spelbord id nummer
     *
     * @return int
     */
    public int getSpelbordId()
    {
        return spelbordId;
    }

    /**
     * Verhoog het aantal verplaatsingen met 1
     */
    public void verhoogVerplaatsingen()
    {
        verplaatsingen++;
    }

    /**
     * Voltooi een spelbord
     */
    public void maakVoltooid()
    {
        voltooid = true;
    }

    /**
     * Stel de vakken in voor het spelbord
     */
    public void geefVakken()
    {
        vakken = vakMapper.geefVakken(spelbordId);
    }

    /**
     * Toon het spelbord
     * 
     * @return String[][]
     */
    public String[][] toonSpelbord()
    {
        // Dit moet nog verbeterd worden. In een array enzo. Dit is om te testen.
        /*
         M:  Muur
         X:  Doel
         V:  Doel met Kist
         O:  Leeg Vak
         K:  Leeg Vak met Kist 
         */
        geefVakken();
        String[][] spelbordString = new String[10][10];
        int x = 0;
        int y = 0;
        for (Vak[] vakArray : vakken)
        {
            y = 0;
            for (Vak vak : vakArray)
            {
                if (!vak.isToegankelijk())
                {
                    // Muur
                    spelbordString[x][y] = "M";
                } else
                {
                    // Toegankelijk vak
                    if (vak.isDoel())
                    {
                        // Doel
                        if (vak.bevatKist())
                        {
                           spelbordString[x][y] = "V";
                        } else
                        {
                            spelbordString[x][y] = "X";
                        }
                    } else
                    {
                        // Leeg Vak
                        if (vak.bevatKist())
                        {
                            spelbordString[x][y] = "K";
                        } else
                        {
                            spelbordString[x][y] = "O";
                        }
                    }
                }
                y++;
            }
            x++;
        }
        return spelbordString;
    }

    /**
     * Verkrijg de vakken
     * 
     * @return Vak[][]
     */
    public Vak[][] getVakken()
    {
        return vakken;
    }

}
