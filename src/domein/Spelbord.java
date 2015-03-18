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
     * @param spelbordId int
     * @param naam String
     */
    public Spelbord(int spelbordId, String naam)
    {
        vakMapper = new VakMapper();
        this.spelbordId = spelbordId;
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
        /*
         M:  Muur
         D:  Doel
         V:  Doel met Kist
         J:  Doel met mannetje
         K:  Vak met Kist 
         Y:  Vak met Mannetje
         -:  Leeg Vak
         */

        if(vakken == null)
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
                        if (vak.bevatKist())           // --> Doel met kist
                        {
                            spelbordString[x][y] = "V";
                        }
                        else if (vak.bevatMannetje())  // --> Doel met mannetje
                        {
                            spelbordString[x][y] = "J";
                        }
                        else                           // --> Doel zonder kist
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

    public void verplaatsSpeler(int richting)
    {
        Vak vakMetMannetje, aanliggendVak, tweedeAanliggendVak;
        
        vakMetMannetje = geefVakMetMannetje();
        aanliggendVak = geefAanliggendVak(vakMetMannetje.getPosX(), vakMetMannetje.getPosY(), richting);

        if (aanliggendVak.isLeeg())             //vak zonder kist of muur
        {
            aanliggendVak.setMannetje(vakMetMannetje.getMannetje());
            vakMetMannetje.setMannetje(null);
        }
        else if (aanliggendVak.bevatKist())     //vak met kist
        {
            tweedeAanliggendVak = geefAanliggendVak(aanliggendVak.getPosX(), aanliggendVak.getPosY(), richting);

            if (tweedeAanliggendVak.isLeeg())   //kist is verplaatsbaar
            {
                tweedeAanliggendVak.setKist(aanliggendVak.getKist());
                aanliggendVak.setKist(null);

                aanliggendVak.setMannetje(vakMetMannetje.getMannetje());
                vakMetMannetje.setMannetje(null);
            }
        }
    }

    public Vak geefVakMetMannetje()
    {
        for (Vak[] vakArray : vakken)
        {
            for (Vak vak : vakArray)
            {
                if (vak.bevatMannetje() == true)
                {
                    return vak;
                }
            }
        }

        return null;
    }

    public Vak geefAanliggendVak(int posX, int posY, int richting)
    {
        if (richting < 1 || richting > 4)
        {
            throw new IllegalArgumentException("Richting moet tussen 0 en 3 liggen");
        }
        
        switch (richting) {
            case 1: posX -= 1; break; // omhoog
            case 2: posX += 1; break; // omlaag
            case 3: posY -= 1; break; // links
            case 4: posY += 1; break; // rechts
        }

        return vakken[posX][posY];
    }
    
    public boolean isSpelbordVoltooid()
    {
        for (Vak[] vakArray : vakken)
        {
            for (Vak vak : vakArray)
            {
                if (vak.bevatKist() && !(vak.isDoel()))
                {
                    return false;
                }
            }
        }
        voltooid = true;
        return true;
    }
    
    public void configureerSpelbord(int[][] vakken)
    {
        this.vakken = this.vakMapper.voegVakkenToe(vakken, spelbordId);
    }
}
