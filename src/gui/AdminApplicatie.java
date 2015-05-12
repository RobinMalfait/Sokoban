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
            
            keuze = geefStringIn("%n%nWenst u het spel met de voltooide spelborden op te slaan? Typ 'ja' om op te slaan"); 

            if (keuze.equals("ja"))
            {
                dc.slaHuidigSpelOp();
                System.out.printf("Het spel %s met de spelborden (%d) werd opgeslaan.", dc.geefNaamHuidigSpel(), dc.geefAantalSpelborden());
            } 
            else
            {
                System.out.print("Het spel met zijn spelborden werd niet opgeslagen.");
            }
        } 
        catch (Exception e)
        {
            // Het spel kent geen 1 voltooid spelbord. Daarom gaan we ook het spel-object terug verwijderen.
            dc.verwijderHuidigSpel();
            System.out.printf(e.getMessage());
        }
        System.out.printf("%n%s%n%n", lang.get("horizontal.line"));
    }
    
    public void wijzigSpel()
    {
        if(!isAdmin())
            return;        
        
        String keuze;
        
        System.out.printf("%n%s%n", lang.get("horizontal.line"));
        System.out.printf("Wijzigen van een bestaand spel: %n%n");
        
        if(kiesSpel())
        {
            while(kiesSpelbord())
            {
                // Geeft 1 terug indien verwijderd. 
                if(wijzigHuidigSpelbord(1) != 1) {
                    keuze = geefStringIn("%nWilt u de wijzigingen van dit spelbord opslaan? Type 'ja' om op te slaan");

                    if(keuze.equals("ja"))
                    {
                        try 
                        {
                            dc.slaHuidigSpelbordOp();
                        }
                        catch(SpelbordException e)
                        {
                            System.out.println("Het spelbord kon niet opgeslaan worden: " + e.getMessage());
                        }
                    }
                    else
                    {   
                        dc.resetSpelbord();
                    }
                }
                else {
                    // Een spelbord is verwijderd, een spel is niet meer nuttig als er geen spelborden zijn
                    // Daarom zullen we ook het Spel verwijderen indien.
                    if(dc.geefAantalSpelborden() == 0) 
                    {
                       dc.verwijderHuidigSpel();
                       break;
                    }                        
                }
            }          
            System.out.printf("%nEinde wijziging spel.%n");
            System.out.printf("%s%n%n", lang.get("horizontal.line"));            
        }
        else {
            System.out.printf("%nEr werd geen spel gekozen, en dus is er geen spel gewijzigd.%n");
            System.out.printf("%s%n%n", lang.get("horizontal.line"));
        }
    }

    public int wijzigHuidigSpelbord(int type)
    {
        boolean fouteInvoer = true;
        boolean doorgaan = true;

        String coordinaat = "";
        String keuze = "";

        do
        {
            System.out.println();
            toonSpelbord();
            
            // Indien type van deze methode een 1 kent, bieden we een extra keuze, namelijk verwijder
            if(type == 1)
                coordinaat = geefStringIn("%nVoer een coördinaat (x,y) in, of typ 'stop' om te stoppen, 'verwijder' om te verwijderen");
            else
                coordinaat = geefStringIn("%nVoer een coördinaat (x,y) in, of typ 'stop' om te stoppen");
            
            if(type == 1 && coordinaat.equals("verwijder"))
            {
                dc.verwijderHuidigSpelbord();
                return 1;
            }
            
            if (!coordinaat.equals("stop"))
            {
                keuze = geefStringIn("Voer een type in : M (Muur), D (Doel), Y (Mannetje), K (Kist), _ (Leeg)");

                try
                {
                    dc.voerVakIn(coordinaat, keuze);
                } 
                catch (SpelbordException se)
                {
                    System.out.printf("%n%s Probeer opnieuw %n", se.getMessage());
                }
                catch (NumberFormatException ne)
                {
                    System.out.printf(lang.get("player.wrongCoordinates"));
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
        return 0;
    }  



    public boolean kiesSpel()
    {
        // Toon de spellen met hun id
        System.out.printf("%-5s%s%n", "Id", "Spelnaam");
        for (String[] spel : dc.geefLijstSpellen())
        {
            System.out.printf("%-5s%s%n", spel[0], spel[1]);
        }

        // Keuze van een spel
        String id = "";
        boolean doorgaan = true;

        System.out.println();
        do
        {
            try
            {
                id = geefStringIn("Kies een spel door het spelId op te geven, of type 'stop' om te stoppen");
                if(id.equals("stop")) {
                    return false;
                }
                else {
                    dc.kiesSpel(Integer.parseInt(id));      
                    doorgaan = false;
                }
            } 
            catch (NumberFormatException e)
            {
                System.out.println(lang.get("err.NaN"));
            }
            catch(SpelException e)
            {
                System.out.println(e.getMessage());
            }
        } while (doorgaan);
        
        return true;
    }

    public boolean kiesSpelbord()
    {
        System.out.printf("%n%-5s%s%n", "Id", "Spelbordnaam");
        for (String[] spelbord : dc.geefLijstSpelborden())
        {
            System.out.printf("%-5s%s%n", spelbord[0], spelbord[1]);
        }

        String id;
        boolean doorgaan = true;

        System.out.println();
        do
        {
            try
            {
                id = geefStringIn("Kies een spelbord door het spelbordId op te geven, of type 'stop' om te stoppen");
                if(id.equals("stop"))
                {
                    return false;
                }
                else
                {
                    dc.kiesSpelbord(Integer.parseInt(id));
                    doorgaan = false;
                }               
            } 
            catch (NumberFormatException e)
            {
                System.out.println(lang.get("err.NaN"));
            }
            catch(SpelException e)
            {
                System.out.println(e.getMessage());
            }
        } while (doorgaan);
        
        return true;
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
                System.out.printf("%s Probeer een andere naam.%n%n", e.getMessage());
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
                wijzigHuidigSpelbord(0);
            }
            catch(SpelbordException e)
            {
                System.out.printf("De naam die je ingaf voor het spelbord bestaat al, probeer een andere naam.%n");
            }
        } 
        while (doorgaan);       
    }
    
}
