package domein;

import exceptions.SpelException;
import exceptions.SpelbordException;
import persistentie.SpelbordMapper;
import persistentie.VakMapper;

public class Spelbord extends Base
{

    private int spelbordId;       // Hebben we nodig om data uit de database te halen
    private final String naam;          // Naam van het Spelbord
    private boolean voltooid = false;   // Al dan niet voltooid
    private int verplaatsingen = 0;     // Het aantal verplatsingen

    private final VakMapper vakMapper;  // Mapper om de vakken/items uit de database op te halen
    private final SpelbordMapper spelbordMapper;  // Mapper om de vakken/items uit de database op te halen
    private Vak[][] vakken;             // Een lijst van Vakken om de vakken/items bij te houden.

    //CONSTRUCTOREN
    /**
     * Maak een nieuw Spelbord-object aan
     *
     * @param spelbordId int
     * @param naam String
     */
    public Spelbord(int spelbordId, String naam)
    {
        vakMapper = new VakMapper();
        spelbordMapper = new SpelbordMapper();

        this.spelbordId = spelbordId;
        this.naam = naam;
    }

    /**
     * Maak een nieuw Spelbord-object aan
     *
     * @param naam
     */
    public Spelbord(String naam)
    {
        vakMapper = new VakMapper();
        spelbordMapper = new SpelbordMapper();

        this.naam = naam;

        vakken = new Vak[10][10];
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                vakken[x][y] = new Vak(x, y, true, false);
            }
        }

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
     * Verkrijg de naam
     *
     * @return String
     */
    public String getNaam()
    {
        return naam;
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
     * Verkrijg de vakken
     *
     * @return Vak[][]
     */
    public Vak[][] getVakken()
    {
        return vakken;
    }

    /**
     * Voltooi een spel
     *
     * @param voltooid
     */
    public void setVoltooid(boolean voltooid)  //Nodig voor testklasse SpelTest
    {
        this.voltooid = voltooid;
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
         
         N:  Mannetje gericht naar Omhoog
         O:  Mannetje gericht naar Rechts
         Z:  Mannetje gericht naar Omlaag
         W:  Mannetje gericht naar Links
        
         -:  Leeg Vak
         */

        if (vakken == null)
        {
            geefVakken();
        }

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
                } //toegankelijk vak
                else
                {
                    if (vak.isDoel())               //DOEL
                    {
                        if (vak.bevatKist())           // --> Doel met kist
                        {
                            spelbordString[x][y] = "V";
                        } else if (vak.bevatMannetje())  // --> Doel met mannetje
                        {
                            spelbordString[x][y] = "J";
                        } else                           // --> Doel zonder kist
                        {
                            spelbordString[x][y] = "D";
                        }
                    } else if (vak.bevatMannetje())   //Mannetje
                    {
                        spelbordString[x][y] = vak.geefRichtingMannetje();
                    } else if (vak.bevatKist())       //Kist
                    {
                        spelbordString[x][y] = "K";
                    } else                            //Leeg vak
                    {
                        spelbordString[x][y] = "_";
                    }
                }
                y++;
            }
            x++;
        }

        return spelbordString;
    }

    /**
     * Verplaats de speler in een bepaalde richting
     *
     * @param richting
     */
    public void verplaatsSpeler(int richting)
    {
        Vak vakMetMannetje, aanliggendVak, tweedeAanliggendVak;

        vakMetMannetje = geefVakMetMannetje();
        vakMetMannetje.stelRichtingMannetjeIn(richting);

        aanliggendVak = geefAanliggendVak(vakMetMannetje.getPosX(), vakMetMannetje.getPosY(), richting);

        if (aanliggendVak.isLeeg())             //vak zonder kist of muur
        {
            aanliggendVak.setMannetje(vakMetMannetje.getMannetje());
            vakMetMannetje.setMannetje(null);

            verplaatsingen++;
        } else if (aanliggendVak.bevatKist())     //vak met kist
        {
            tweedeAanliggendVak = geefAanliggendVak(aanliggendVak.getPosX(), aanliggendVak.getPosY(), richting);

            if (tweedeAanliggendVak.isLeeg())   //kist is verplaatsbaar
            {
                tweedeAanliggendVak.setKist(aanliggendVak.getKist());
                aanliggendVak.setKist(null);

                aanliggendVak.setMannetje(vakMetMannetje.getMannetje());
                vakMetMannetje.setMannetje(null);

                verplaatsingen++;
            }
        }
    }

    /**
     * Geeft het vak waar het mannetje op staat terug
     *
     * @return Vak
     */
    private Vak geefVakMetMannetje()
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

    /**
     * Geef het aanliggend vak, rekening houdend met de richting
     *
     * @param posX
     * @param posY
     * @param richting
     *
     * @return Vak
     */
    private Vak geefAanliggendVak(int posX, int posY, int richting)
    {
        int min = 1, max = 4;

        if (richting < min || richting > max)
        {
            throw new SpelException(lang.get("player.wrongDirection", ":min", min, ":max", max));
        }

        switch (richting)
        {
            case 1:
                posX -= 1;
                break; // omhoog
            case 2:
                posX += 1;
                break; // omlaag
            case 3:
                posY -= 1;
                break; // links
            case 4:
                posY += 1;
                break; // rechts
        }

        return vakken[posX][posY];
    }

    /**
     * Controleer of het spelbord voltooid is
     *
     * @return boolean
     */
    public boolean isEindeSpelbord()
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
        this.voltooid = true;
        return this.voltooid;
    }

    /**
     * Stel de vakken van het spelbord in op hun beginwaarde.
     */
    public void resetSpelbord()
    {
        this.geefVakken();
        this.verplaatsingen = 0;
    }

    /**
     * Plaats een item op een vak.
     *
     * @param coordinaat String
     * @param keuze String
     */
    public void voerVakIn(String coordinaat, String keuze)
    {
        coordinaat = coordinaat.trim();
        String[] xy = coordinaat.split(",");

        if (xy.length != 2)
        {
            throw new SpelbordException(lang.get("player.wrongCoordinates"));
        }

        int x = Integer.valueOf(xy[0]);
        int y = Integer.valueOf(xy[1]);

        if (x < 0 || x > (vakken.length - 1) || y < 0 || y > (vakken[1].length - 1))
        {
            throw new SpelbordException(lang.get("player.wrongCoordinates"));
        }

        Vak vak = vakken[x][y];

        switch (keuze.toUpperCase())
        {
            case "_":                                     // Toegankelijk vak - Leeg vak
                vak.setKist(null);
                vak.setMannetje(null);
                vak.setToegankelijk(true);
                vak.setDoel(false);
                break;
            case "M":                                     // Muur
                vak.setKist(null);
                vak.setMannetje(null);
                vak.setToegankelijk(false);
                vak.setDoel(false);
                break;
            case "D":                                     // Toegankelijk vak - Met Doel
                vak.setKist(null);
                vak.setMannetje(null);
                vak.setToegankelijk(true);
                vak.setDoel(true);
                break;
            case "K":                                     // Toegankelijk vak - Met Kist
                vak.setKist(new Kist());
                vak.setMannetje(null);
                vak.setToegankelijk(true);
                vak.setDoel(false);
                break;
            case "Y":                                     // Toegankelijk vak - Met Mannetje
                vak.setKist(null);
                vak.setMannetje(new Mannetje());
                vak.setToegankelijk(true);
                vak.setDoel(false);
                break;
            default:                                      // niet in lijst
                throw new SpelbordException(lang.get("err.invalidType"));

        }
    }

    /**
     * Sla de wijzigingen in het huidige spelbord op.
     *
     * @param spelId int
     */
    public void voegToe(int spelId)
    {
        try
        {
            controleerSpelbord();

            this.spelbordId = spelbordMapper.voegSpelbordToe(this.naam, spelId);

            if (this.spelbordId == 0)
            {
                throw new SpelbordException(lang.get("game.notSaved"));
            } 
            else
            {
                vakMapper.voegVakkenToe(vakken, this.spelbordId);
            }
        } 
        catch (SpelbordException e)
        {
            // Enkel de voltooide spelborden zullen opgeslaan worden.
        }
    }

    /**
     * Sla het spel op
     */
    public void slaOp()
    {
        controleerSpelbord();
        vakMapper.wijzigVakken(vakken, this.spelbordId);
    }

    /**
     * Controleer of het spelbord aan de eisen voldoet. Een spelbord moet
     * evenveel doelen als kisten bevatten en juist één mannetje hebben.
     */
    public void controleerSpelbord()
    {
        int aantalDoelen = 0, aantalKisten = 0, aantalMannetjes = 0;

        for (Vak[] vakArray : vakken)
        {
            for (Vak vak : vakArray)
            {
                if (vak.isDoel())
                {
                    aantalDoelen++;
                } else if (vak.bevatKist())
                {
                    aantalKisten++;
                } else if (vak.bevatMannetje())
                {
                    aantalMannetjes++;
                }
            }
        }

        if (aantalMannetjes != 1)
        {
            throw new SpelbordException(String.format("%s %s", (aantalMannetjes == 0) ? lang.get("game.board.mustHavePlayer") : lang.get("playerLimit"), lang.choice("game.board.has", aantalMannetjes, "count", aantalMannetjes)));
        }

        if (aantalDoelen == 0 || aantalDoelen != aantalKisten)
        {
            throw new SpelbordException(lang.get("game.board.boxEquality"));
        }

    }

}
