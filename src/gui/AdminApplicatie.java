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
public class AdminApplicatie
{

    public static void start(DomeinController dc, Scanner input, LanguageManager lang)
    {

        if (!dc.isAdmin())
        {
            System.out.println("U bent geen admin.");
            (new ConsoleApplicatie()).start(dc, input, lang);
        }
        else
        {

            int keuze;

            System.out.printf("%n%s%n1: %s%n2: %s%n3: %s%n%n",
                    lang.get("list.choose"),
                    "Een nieuw spel aanmaken",
                    "Hoofdmenu",
                    lang.get("app.quit"));

            keuze = invoerMetControle(1, 3, input, lang);

            System.out.println(); // Een extra enter voor de volgende output
            input.nextLine(); // Buffer leegmaken

            switch (keuze)
            {
                case 1:
                    maakNieuwSpel(dc, input, lang);
                    break;
                case 2:
                    (new ConsoleApplicatie()).start(dc, input, lang);
                    break;
                case 3:
                    System.out.println(lang.get("app.quited"));
                    break;
                default:
                    System.err.println(lang.get("err.nonvalid"));
            }
        }
    }

    public static void maakNieuwSpel(DomeinController dc, Scanner input, LanguageManager lang)
    {
        String naam;
        boolean spelBestaat = true;
        
        System.out.printf("%n%s%n", lang.get("horizontal.line"));

        System.out.printf("Configuratie van een nieuw spel: %n%n");
        
        do
        {
            System.out.print("Geef een naam voor het nieuwe spel: ");
            naam = input.nextLine().trim();

            try
            {
                dc.voegSpelToe(naam);
                System.out.printf("Het spel %s werd aangemaakt, u zult nu een spelbord toevoegen.%n%n",
                        dc.geefNaamHuidigSpel());
                
                spelBestaat = false;
            }
            catch (SpelException e)
            {
                System.out.println(e.getMessage() + " Probeer een andere naam.");
            }
 
        } while (spelBestaat);
        
        
        // TE IMPLEMENTEREN: spelborden blijven toevoegen tot speler wil stoppen
        maakNieuwSpelbord(dc, input, lang);

        start(dc, input, lang);
        
    
    }

    public static void maakNieuwSpelbord(DomeinController dc, Scanner input, LanguageManager lang)
    {
        boolean fouteInvoer = true, doorgaan = true, spelbordBestaat = true;
        String spelbordNaam;

        // 1. Naam voor het spelbord
        do
        {
            System.out.print("Geef een naam voor het nieuwe spelbord: ");
            spelbordNaam = input.nextLine().trim();

            try
            {
                dc.voegSpelbordToe(spelbordNaam);
                System.out.printf("Het spelbord %s werd toegevoegd aan het spel %s.%n%n",
                        dc.geefNaamHuidigSpelbord(),
                        dc.geefNaamHuidigSpel());
                
                spelbordBestaat = false;
            }
            catch (SpelbordException e)
            {
                System.out.println(e.getMessage() + " Probeer een andere naam.");
            }
 
        } while (spelbordBestaat);

        toonSpelbord(dc);

        System.out.println();

        String coordinaat = "";
        String keuze = "";
        do
        {
            System.out.printf("Voer een coördinaat in of typ 'stop': ");
            coordinaat = input.next();
            input.nextLine();

            if (coordinaat.equals("stop"))
            {
                doorgaan = false;
                break;                
            }

            System.out.printf("Voer een type in : M (Muur), D (Doel), Y (Mannetje), K (Kist), _ (Leeg): ");
            keuze = input.next();
            input.nextLine();

            try
            {
                dc.voerVakIn(coordinaat, keuze);
                toonSpelbord(dc);
            }
            catch (SpelbordException e)
            {
                System.err.println(e.getMessage() + " Probeer opnieuw.");
                System.out.println();
            }
            
            
        } while (doorgaan);

        if (doorgaan)
        {
            dc.slaHuidigSpelOp();
            System.out.println("Het spelbord werd opgeslagen.");
        }
        else
            System.out.println("Gestopt.");
    }

    public static void toonSpelbord(DomeinController dc)
    {
        int x = 0;
    
        System.out.print("  ");
        for(int i = 0; i < 10; i++)
            System.out.print(i + " ");
        
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
    
    private static int invoerMetControle(int ondergrens, int bovengrens, Scanner input, LanguageManager lang)
    {
        int keuze = 0;
        boolean fouteInvoer = true;
        do
        {
            try
            {                
                System.out.printf("%s: ", lang.get("list.choice"));
                keuze = input.nextInt();

                if (keuze < ondergrens || keuze > bovengrens)
                {
                    throw new IllegalArgumentException(lang.get("err.input", 
                        "min", ondergrens, 
                        "max", bovengrens));
                }
                
                fouteInvoer = false;
            }
            catch (IllegalArgumentException e)
            {
                System.err.println(e.getMessage());
                input.nextLine();
            }
            catch (InputMismatchException e)
            {
                System.err.println(lang.get("err.NaN"));
                input.nextLine();
            }

        } while (fouteInvoer);
                
        return keuze;
    }
    
    public void snelStarten(DomeinController dc, Scanner input, LanguageManager lang)
    {

        dc.meldAan("AdminTest1", "AdminTest1");

        this.start(dc, input, lang);
    }

}
