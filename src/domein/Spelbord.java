package domein;

import persistentie.VakMapper;

public class Spelbord
{

    private final int spelbordId;       // Hebben we nodig om data uit de database te halen
    private final String naam;          // Naam van het Spelbord
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
         D:  Doel
         V:  Doel met Kist
         K:  Vak met Kist 
         Y:  Vak met Mannetje
         O:  Leeg Vak
         */
        
        geefVakken();

        String[][] spelbordString = new String[vakken.length][vakken[0].length];

        int x = 0;
        int y;

        for (Vak[] vakArray : vakken)
        {
            y = 0;
            for (Vak vak : vakArray)
            {
                if (!vak.isToegankelijk())          //Muur
                {
                    spelbordString[x][y] = "M";
                }

                //toegankelijk vak
                else
                {
                    if (vak.isDoel())               //DOEL
                    {
                        if (vak.bevatKist())          // --> Doel met kist
                        {
                            spelbordString[x][y] = "V";
                        }
                        else                          // --> Doel zonder kist
                        {
                            spelbordString[x][y] = "D";
                        }
                    }

                    else if (vak.bevatMannetje())   //Mannetje
                    {
                        spelbordString[x][y] = "Y";
                    }

                    else if (vak.bevatKist())       //Kist
                    {
                        spelbordString[x][y] = "K";
                    }
                    else                            //Leeg vak
                    {
                        spelbordString[x][y] = "-";
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
