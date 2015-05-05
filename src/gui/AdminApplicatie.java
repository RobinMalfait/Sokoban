package gui;

import domein.DomeinController;
import exceptions.SpelException;
import exceptions.SpelbordException;
import java.util.InputMismatchException;
import java.util.Scanner;
import languages.LanguageManager;

/**
 *
 * @author Demian
 */
public class AdminApplicatie extends BaseApplicatie
{
    public AdminApplicatie(DomeinController dc, Scanner input, LanguageManager lang)
    {
        super(dc, input, lang);
    }
    public boolean isAdmin()
    {
        if(!dc.isAdmin())
        {
            System.out.println("U bent geen admin");
            return false;
        }
        return true;
    }
    public void maakNieuwSpel()
    {
        if(!isAdmin())
            return;
        
        String naam;
        boolean doorgaan = true;

        System.out.printf("%n%s%n", lang.get("horizontal.line"));
        System.out.printf("Configuratie van een nieuw spel: %n%n");

        // 1. Unieke naam vragen voor het aanmaken van een spel.
        do
        {
            naam = geefStringIn("Geef een naam voor het nieuwe spel");
            try
            {
                dc.voegSpelToe(naam);
                System.out.printf("Het spel %s werd aangemaakt.%n", dc.geefNaamHuidigSpel());
                doorgaan = false;
            } 
            catch (SpelException e)
            {
                System.out.println(e.getMessage() + " Probeer een andere naam.");
            }
        } while (doorgaan);

        // 2. Toevoegen van een spelbord.
        doorgaan = true;
        String keuze = "";
        int aantalSpelborden = 1;
        do
        {
            keuze = geefStringIn("%nGeef een naam voor spelbord " + aantalSpelborden + " in, of type 'stop' om te stoppen");

            if (keuze.equals("stop"))
            {
                doorgaan = false;
                break;
            }
            maakNieuwSpelbord(keuze);
            aantalSpelborden++;
        } 
        while (doorgaan);

        // 3. De gebruiker wenst geen nieuwe spelborden toe te voegen. Nu het spel opslaan in de databank.
        try
        {
            dc.controleerSpel();
            System.out.printf("%n%nWenst u het spel met de spelborden op te slaan? Typ 'ja' om op te slaan.");
            keuze = input.next();
            input.nextLine(); // Buffer leegmaken     

            if (keuze.equals("ja"))
            {
                dc.slaHuidigSpelOp();
                System.out.printf("Het spel %s met %d spelborden werd aangemaakt.", dc.geefNaamHuidigSpel(), dc.geefLijstSpelborden().length);
            } 
            else
            {
                System.err.println("Het spel met zijn spelborden werd niet opgeslagen.");
            }
        } 
        catch (Exception e)
        {
            System.err.println("Geen spel is opgeslaan, omdat er geen voltooide spelborden aanwezig waren.");
        }

        // 4. Doorsturen naar het adminmenu.

    }

    public void maakNieuwSpelbord(String spelbordNaam)
    {
        boolean fouteInvoer = true;
        boolean doorgaan = true;

        // 1. Unieke naam voor Spelbord in een spel
        do
        {
            try
            {
                dc.voegSpelbordToe(spelbordNaam);
                System.out.printf("Een leegspelbord met naam %s werd toegevoegd aan het spel %s.%n%n",
                        dc.geefNaamHuidigSpelbord(),
                        dc.geefNaamHuidigSpel());

                doorgaan = false;
            } 
            catch (SpelbordException e)
            {
                System.out.println(e.getMessage() + " Probeer een andere naam.");
            }
        } while (doorgaan);

        // 2. Een leeg spelbord kennen we, nu de vakken laten wijzigen. 
        String coordinaat = "";
        String keuze = "";

        doorgaan = true;
        do
        {
            toonSpelbord();
            coordinaat = geefStringIn("%nVoer een coördinaat (x,y) in, of typ 'stop' om te stoppen");

            if (coordinaat.equals("stop"))
            {
                try
                {
                    dc.controleerSpel();
                    doorgaan = false;
                    break;
                } 
                catch (Exception e)
                {
                    // Het spel kent nog geen afgewerkt spelbord.
                    System.out.printf("Het systeem kent nog geen 1 volledig afgewerkt spelbord. Weet u zeker dat uw wenst te stoppen? (Typ 'stop' om te stoppen): ");
                    keuze = input.next();
                    input.nextLine();

                    if (keuze.equals("stop"))
                    {
                        doorgaan = false;
                        break;
                    } else
                    {
                        System.out.printf("Voer een coördinaat: ");
                        coordinaat = input.next();
                        input.nextLine();
                    }
                }
            }

            keuze = geefStringIn("Voer een type in : M (Muur), D (Doel), Y (Mannetje), K (Kist), _ (Leeg)");

            try
            {
                dc.voerVakIn(coordinaat, keuze);
            } 
            catch (SpelbordException e)
            {
                System.err.println(e.getMessage() + " Probeer opnieuw.");
                System.out.println();
            }
        } while (doorgaan);

    }  

    public void wijzigSpel()
    {
        System.out.printf("%n%s%n", lang.get("horizontal.line"));
        System.out.printf("Een spel wijzigen%n%n");

        // Kies een spel uit de lijst.
        this.kiesSpel();

        // Kies een spelbord uit de lijst.
        this.kiesSpelbord();

        String coordinaat = "";
        String keuze = "";

        boolean doorgaan = true;

        do
        {
            toonSpelbord();

            System.out.printf("%nVoer een coördinaat (x,y) in of typ 'stop': ");
            coordinaat = input.next();
            input.nextLine();
            
            if (coordinaat.toLowerCase().equals("stop"))
            {
                // De gebruiker wenst te stoppen.
                try
                {
                    // Indien het spelbord gecontroleerd is, en aan alle voorwaarden voordoet, dan slaan we het op.
                    dc.controleerSpelbord();
                    dc.slaHuidigSpelbordOp();

                    doorgaan = false;
                    System.out.println("Het spelbord is opgeslaan");
                    break;
                } catch (SpelbordException e)
                {
                    System.out.printf("Het systeem kent nog geen 1 volledig afgewerkt spelbord (%s). Weet u zeker dat u wenst te stoppen? (Typ 'stop' om te stoppen): ", e.getMessage());
                    System.out.printf("Let op: dit spelbord zal niet opgeslaan worden indien u stopt!");

                    keuze = input.next();
                    input.nextLine();

                    if (keuze.toLowerCase().equals("stop"))
                    {
                        doorgaan = false;
                        break;
                    } else
                    {
                        doorgaan = true;
                        System.out.printf("Voer een coördinaat in (x,y): ");
                        coordinaat = input.next();
                        input.nextLine();
                    }
                }
            }

            System.out.printf("Voer een type in : M (Muur), D (Doel), Y(Mannetje), K (Kist), _ (Leeg): ");
            keuze = input.next();
            input.nextLine();

            try
            {
                dc.voerVakIn(coordinaat, keuze);
            } catch (SpelbordException e)
            {
                System.err.println(e.getMessage() + " Probeer opnieuw.");
                System.out.println();
            }
        } while (doorgaan);
    }



    public void kiesSpel()
    {
        System.out.printf("%-5s%s%n", "Id", "Spelnaam");

        for (String[] spel : dc.geefLijstSpellen())
        {
            System.out.printf("%-5s%s%n", spel[0], spel[1]);
        }

        int id = 0;
        boolean fouteInvoer = true;

        do
        {
            try
            {
                System.out.print("Kies een spel door het spelId op te geven of type 'stop' om te stoppen: ");
                id = input.nextInt();
                input.nextLine();

                dc.kiesSpel(id);
                fouteInvoer = false;
            } catch (InputMismatchException | SpelException e)
            {
                System.out.println(e.getMessage());
            }
        } while (fouteInvoer);
    }

    public void kiesSpelbord()
    {
        System.out.printf("%n%-5s%s%n", "Id", "Spelbordnaam");
        for (String[] spelbord : dc.geefLijstSpelborden())
        {
            System.out.printf("%-5s%s%n", spelbord[0], spelbord[1]);
        }

        int id = 0;
        boolean fouteInvoer = true;

        do
        {
            try
            {
                System.out.print("Kies een spelbord door het spelbordId op te geven: ");
                id = input.nextInt();
                input.nextLine();

                dc.kiesSpelbord(id);
                fouteInvoer = false;
            } catch (InputMismatchException | SpelException e)
            {
                System.err.println(e.getMessage());
            }
        } while (fouteInvoer);
    }
    @Override
    public void toonSpelbord()
    {
        int x = 0;

        System.out.print("  ");
        for (int i = 0; i < 10; i++)
        {
            System.out.print(i + " ");
        }

        System.out.println();
        for (String[] vakArray : dc.toonSpelbord())
        {
            System.out.print(x + " ");
            for (String vak : vakArray)
            {
                System.out.print(vak + " ");
            }
            System.out.println();
            x++;
        }
    }
}
