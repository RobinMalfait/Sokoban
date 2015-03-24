/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import exceptions.SpelException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import languages.LanguageManager;

/**
 *
 * @author Demian
 */
public class SpeelSpelApplicatie
{

    public void start(DomeinController dc, Scanner input, LanguageManager lang)
    {
        boolean invoerFout = true;
        int keuze = 0;
        
        // Welkombericht
        System.out.printf("%s %s%n", 
                lang.get("game.welcome"),
                dc.geefHuidigeSpeler()[0]);
        
        System.out.printf("%n%s%n", 
                lang.get("game.choose.list"));

        // Overlopen van de Spellen
        for (String[] spelString : dc.geefSpellenString())
        {
            System.out.printf("%4s: %-20s%n", spelString[0], spelString[1]);
        }

        // Keuze van de Spellen
        System.out.print(lang.get("game.choose") + ": ");
        
        do 
        {
            try 
            {
                int spelId = input.nextInt();
                dc.kiesSpel(spelId);
                invoerFout = false;
            }
            catch(SpelException se)
            {
                System.err.println(se.getMessage());
            }
            catch(InputMismatchException e)
            {
                System.err.println(lang.get("err.NaN"));
            }
        }
        while(invoerFout);
        
        // Het laden van het eerste Spelbord van het spel
        System.out.printf("%n%s%n", lang.get("game.board.loading"));

        do
        {
            invoerFout = true;
            
            this.laadSpelbord(dc, input, lang);

            //Verplaats de speler (met invoercontrole)
            do
            {
                System.out.printf("%n%s:%n 1: %s%n 2: %s%n 3: %s%n 4: %s%n 5: %s%n 6: %s%n",
                    lang.get("player.move"),
                    lang.get("player.up"),
                    lang.get("player.down"),
                    lang.get("player.left"),
                    lang.get("player.right"),
                    lang.get("game.board.retry"),
                    lang.get("app.quit")
                );
                
                keuze = this.invoerControle(1, 6, input, lang);
                
                if (keuze == 6)         //stoppen
                    break;       
                else if (keuze == 5)    //resetSpelbord
                    dc.resetSpelbord();
                else             
                    dc.verplaatsSpeler(keuze);
                
                this.laadSpelbord(dc, input, lang);

            } while (!dc.isEindeSpelbord());

            // Als men hier komt is het Spelbord voltooid , oftewel wil de gebruiker stoppen.
            if (keuze == 6)
                break;

            System.out.printf("%n%s%n%n", lang.get("game.board.completed"));
            
            dc.bepaalVolgendSpelbord();
            
            //Mogelijkeheid tot stoppen (met invoercontrole)
            System.out.printf("%s%n 1: %s%n 2: %s%n",
                    lang.get("list.choose"),
                    lang.get("game.board.next"),
                    lang.get("app.quit")
            );
            
            keuze = this.invoerControle(1, 2, input, lang);
            
            if(keuze == 2) //stoppen
                break;

        } while (!dc.isEindeSpel());

        if(dc.isEindeSpel())
            System.out.println(lang.get("game.completed"));
        else
            System.out.println(lang.get("app.quited"));
    }

    public void snelStarten(DomeinController dc, Scanner input, LanguageManager lang)
    {

        dc.meldAan("SpeelSpelTest1", "SpeelSpelTest1");

        this.start(dc, input, lang);
    }
    
    private void laadSpelbord(DomeinController dc, Scanner input, LanguageManager lang)
    {
        System.out.println();

        for (String[] vakArray : dc.toonSpelbord())
        {
            for (String vak : vakArray)
            {
                System.out.print(vak + " ");
            }
            System.out.println();
        }
        
        System.out.printf("%s: %d%n",
                lang.get("game.board.moves"),
                dc.geefAantalVerplaatsingen()
        );
    }
    
    private int invoerControle(int ondergrens, int bovengrens, Scanner input, LanguageManager lang)
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
            catch (IllegalArgumentException | SpelException e)
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
}
