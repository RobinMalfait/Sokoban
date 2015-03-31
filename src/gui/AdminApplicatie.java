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
        // Controle of de persoon een administrator is.
        if (!dc.isAdmin())
        {
            System.out.println("U bent geen admin.");
            (new ConsoleApplicatie()).start(dc, input, lang);
        } 
        else
        {
            int keuze;

            System.out.printf("%n%s%n1: %s%n2: %s%n3: %s%n4: %s%n%n",
                    lang.get("list.choose"),
                    "Een nieuw spel aanmaken",
                    "Een spel wijzigen",
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
                    wijzigSpel(dc, input, lang);
                    break;                    
                case 3:
                    (new ConsoleApplicatie()).start(dc, input, lang);
                    break;
                case 4:
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
        boolean doorgaan = true;

        System.out.printf("%n%s%n", lang.get("horizontal.line"));

        System.out.printf("Configuratie van een nieuw spel: %n%n");

        // 1. Unieke naam vragen voor het aanmaken van een spel.
        do
        {
            System.out.print("Geef een naam voor het nieuwe spel: ");
            naam = input.nextLine().trim();

            try
            {
                dc.voegSpelToe(naam);
                System.out.printf("Het spel %s werd aangemaakt, u zult nu een spelbord toevoegen.%n",
                        dc.geefNaamHuidigSpel());

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
        
        do 
        {
            System.out.printf("%nWilt u (nog) een spelbord toevoegen? Typ 'stop' om te stoppen: ");
            keuze = input.next(); 
            input.nextLine(); // Buffer leegmaken            
            
            if(keuze.equals("stop"))
            {
                doorgaan = false;
                break;
            }
            maakNieuwSpelbord(dc, input, lang);
        } while(doorgaan);
        
        // 3. De gebruiker wenst geen nieuwe spelborden toe te voegen. Nu het spel opslan in de databank.
        if(dc.controleerSpel()) 
        {
            System.out.printf("%n%nWenst u het spel met de spelborden op te slaan? Typ 'ja' om op te slaan.");
            keuze = input.next(); 
            input.nextLine(); // Buffer leegmaken     
            
            if(keuze.equals("ja"))
            {
                dc.slaHuidigSpelOp();
                System.out.printf("Het spel %s met %d spelborden werd aangemaakt.", dc.geefNaamHuidigSpel(), dc.geefLijstSpelborden().length);
            }
            else
            {
                System.err.println("Het spel met zijn spelborden werd niet opgeslagen.");
            }
        }
        else 
        {
            System.err.println("Geen spel is opgeslaan, omdat er geen voltooide spelborden aanwezig waren.");
        }
       
        
        
        // 4. Doorsturen naar het adminmenu.
        start(dc, input, lang);
    }

    public static void maakNieuwSpelbord(DomeinController dc, Scanner input, LanguageManager lang)
    {
        boolean fouteInvoer = true;
        boolean doorgaan = true;

        String spelbordNaam;
        // 1. Naam voor het spelbord
        do
        {
            System.out.printf("%nGeef een naam voor het nieuwe spelbord: ");
            spelbordNaam = input.nextLine().trim();

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
            toonSpelbord(dc);
            
            System.out.printf("%nVoer een coördinaat in of typ 'stop': ");
            coordinaat = input.next();
            input.nextLine();

            if (coordinaat.equals("stop"))
            {
                if(!dc.controleerSpel())
                {
                    // Het spel kent nog geen afgewerkt spelbord.
                    System.out.printf("Het systeem kent nog geen 1 volledig afgewerkt spelbord. Weet u zeker dat uw wenst te stoppen? (Typ 'stop' om te stoppen): ");
                    keuze = input.next();
                    input.nextLine();
                    
                    if(keuze.equals("stop"))
                    {
                        doorgaan = false;
                        break;
                    }
                    else
                    {
                        System.out.printf("Voer een coördinaat: ");
                        coordinaat = input.next();
                        input.nextLine();                       
                    }
                }
                else 
                {
                    doorgaan = false;
                    break;                  
                }
            }

            System.out.printf("Voer een type in : M (Muur), D (Doel), Y (Mannetje), K (Kist), _ (Leeg): ");
            keuze = input.next();
            input.nextLine();

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

    public static void toonSpelbord(DomeinController dc)
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
            } catch (IllegalArgumentException e)
            {
                System.err.println(e.getMessage());
                input.nextLine();
            } catch (InputMismatchException e)
            {
                System.err.println(lang.get("err.NaN"));
                input.nextLine();
            }

        } while (fouteInvoer);

        return keuze;
    }
    
    public static void wijzigSpel(DomeinController dc, Scanner input, LanguageManager lang)
    {
        System.out.printf("%n%s%n", lang.get("horizontal.line"));
        System.out.printf("Een spel wijzigen%n%n");
        
        // Toon een lijst van spellen
        for(String[] spel: dc.geefLijstSpellen())
        {
            System.out.printf("%d: %s%n", spel[0], spel[1]);
        }
        
        int id = 0;
        boolean fouteInvoer = true;
        
        do
        {
            try
            {
                System.out.print("Kies een spel door het spel_id op te geven: ");
                id = input.nextInt();
                input.nextLine();
                
                dc.kiesSpel(id);
                fouteInvoer = false;
            }
            catch(InputMismatchException e)
            {
                System.out.println(e.getMessage());
            }
            catch(SpelException e)
            {
                System.out.println(e.getMessage());
            }
        }
        while(fouteInvoer);
        
         // Toon een lijst van spelborden
        for(String[] spelbord: dc.geefLijstSpelborden())
        {
            System.out.printf("%d: %s%n", spelbord[0], spelbord[1]);
        }  
        
        id = 0;
        fouteInvoer = true;
        do
        {
            try
            {
                System.out.print("Kies een spelbord door het spelbord_id op te geven: ");
                id = input.nextInt();
                input.nextLine();
                
                dc.kiesSpelbord(id);
                fouteInvoer = false;
            }
            catch(InputMismatchException e)
            {
                System.out.println(e.getMessage());
            }
            catch(SpelException e)
            {
                System.out.println(e.getMessage());
            }
        }
        while(fouteInvoer);
        
        
        
    }

    public void snelStarten(DomeinController dc, Scanner input, LanguageManager lang)
    {

        dc.meldAan("administrator", "Administrator1");

        this.start(dc, input, lang);
    }

}
