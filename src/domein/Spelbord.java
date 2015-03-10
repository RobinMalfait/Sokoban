package domein;

import java.util.ArrayList;
import java.util.List;
import persistentie.VakMapper;

public class Spelbord
{

    private final int spelbordId;       // Hebben we nodig om data uit de database te halen
    private final String naam;          // Naam de van het Spelbord
    private boolean voltooid = false;   // Al dan niet voltooid
    private int verplaatsingen = 0;     // Het aantal verplatsingen

    private final VakMapper vakMapper;  // Mapper om de vakken/items uit de database op te halen
    private Vak[][] vakken;           // Een lijst van Vakken om de vakken/items bij te houden.

    public Spelbord(int nummer, String naam)
    {
        vakMapper = new VakMapper();
        this.spelbordId = nummer;
        this.naam = naam;
    }

    public boolean isVoltooid()
    {
        return voltooid;
    }

    public int getVerplaatsingen()
    {
        return verplaatsingen;
    }

    public String getNaam()
    {
        return naam;
    }

    public int getSpelbordId()
    {
        return spelbordId;
    }

    public void verhoogVerplaatsingen()
    {
        verplaatsingen++;
    }

    public void maakVoltooid()
    {
        voltooid = true;
    }

    public void geefVakken()
    {
        vakken = vakMapper.geefVakken(spelbordId);
    }

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

    public Vak[][] getVakken()
    {
        return vakken;
    }

}
