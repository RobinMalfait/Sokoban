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
        
        String naam, keuze;
        boolean doorgaan = true;

        System.out.printf("%n%s%n", lang.get("horizontal.line"));
        System.out.printf("Configuratie van een nieuw spel: %n%n");

        // 1. Unieke naam vragen voor het aanmaken van een spel.
        voegSpelToe();

        // 2. Toevoegen van een spelbord.
        voegSpelbordenToe();
        
        // 3. De gebruiker wenst geen nieuwe spelborden toe te voegen. Nu het spel opslaan in de databank.
        try
        {
            dc.controleerSpel();
            keuze = geefStringIn("%n%nWenst u het spel met de spelborden op te slaan? Typ 'ja' om op te slaan."); 

            if (keuze.equals("ja"))
            {
                dc.slaHuidigSpelOp();
                System.out.printf("Het spel met de spelborden werd opgeslaan.");
            } 
            else
            {
                System.out.println("Het spel met zijn spelborden werd niet opgeslagen.");
            }
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public void wijzigSpel()
    {
        
    }

    public void wijzigHuidigSpelbord()
    {
        boolean fouteInvoer = true;
        boolean doorgaan = true;

        String coordinaat = "";
        String keuze = "";

        do
        {
            System.out.println();
            toonSpelbord();
            coordinaat = geefStringIn("%nVoer een coördinaat (x,y) in, of typ 'stop' om te stoppen");

            if (!coordinaat.equals("stop"))
            {
                keuze = geefStringIn("Voer een type in : M (Muur), D (Doel), Y (Mannetje), K (Kist), _ (Leeg)");

                try
                {
                    dc.voerVakIn(coordinaat, keuze);
                } 
                catch (SpelbordException e)
                {
                    System.out.printf("%n%s Probeer opnieuw %n", e.getMessage());
                    System.out.println();
                }
            }
            else 
            {
                // De gebruiker wenst te stoppen met het bewerken van het spelbord
                try
                {
                    dc.controleerSpelbord();
                    doorgaan = false;
                    break;
                } 
                catch (Exception e)
                {
                    // Het spel kent nog geen afgewerkt spelbord.
                    System.out.printf("%nU gaf aan om te stoppen maar het spelbord is nog niet volledig afgewerkt:%n%s%n%n",  e.getMessage());
                    keuze = geefStringIn("Weet u zeker dat u wilt stoppen? (type 'stop' om te stoppen)");

                    if (keuze.equals("stop"))
                    {
                        doorgaan = false;
                        break;
                    } 
                }               
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
    
    // Voeg een spel toe, en plaats deze in de dc als huidigSpel.
    private void voegSpelToe()
    {
        String naam = "";
        boolean doorgaan = true;
        
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
    }
    
    private void voegSpelbordenToe()
    {
        boolean doorgaan = true;
        String naam = "";

        do
        {
            naam = geefStringIn("%nGeef een naam voor het spelbord in, of type 'stop' als je geen spelborden meer wilt toevoegen");
            try 
            {
                if (naam.equals("stop"))
                {
                    doorgaan = false;
                    break;
                }      
                
                dc.voegSpelbordToe(naam);
                System.out.println();
                wijzigHuidigSpelbord();
            }
            catch(SpelbordException e)
            {
                System.out.printf("De naam die je ingaf voor het spelbord bestaat al, probeer een andere naam.%n");
            }
        } 
        while (doorgaan);       
    }
}
