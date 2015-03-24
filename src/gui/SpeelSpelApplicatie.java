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
                System.out.println(se.getMessage());
            }
        }
        while(invoerFout);
        
        // Het laden van het eerste Spelbord van het spel
        System.out.printf("%n%s%n", lang.get("game.board.loading"));

        do
        {
            invoerFout = true;
            
            System.out.printf("%s: %d%n",
                        "Aantal verplaatsingen",
                        dc.geefAantalVerplaatsingen()
            );
            
            for (String[] vakArray : dc.toonSpelbord())
            {
                for (String vak : vakArray)
                {
                    System.out.print(vak + " ");
                }
                System.out.println();
            }

            int keuze = 0;

            do
            {
                System.out.printf("%n%s:%n 1: %s%n 2: %s%n 3: %s%n 4: %s%n 5: %s%n 6: %s%n",
                    lang.get("player.move"),
                    lang.get("player.up"),
                    lang.get("player.down"),
                    lang.get("player.left"),
                    lang.get("player.right"),
                    "reset spelbord",
                    lang.get("app.quit")
                );

                
                
                do 
                {
                    System.out.printf("%n%s: ", lang.get("list.choice"));
                    try 
                    {
                        
                        input.nextLine();
                        keuze = input.nextInt();  
                        
                        if (keuze < 1 || keuze > 6)
                            throw new IllegalArgumentException("De keuze moet tussen 1 en 6 liggen");
                    }
                    catch(IllegalArgumentException | SpelException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("U gaf geen nummer in. Probeer opnieuw");
                    }
                    
                    invoerFout = false;
                    
                } while(invoerFout);
                
                if (keuze == 6)
                    break;       
                else if (keuze == 5)
                    dc.resetSpelbord();
                else             
                    dc.verplaatsSpeler(keuze);

                System.out.printf("%n%s: %d%n",
                        "Aantal verplaatsingen",
                        dc.geefAantalVerplaatsingen()
                );
                
                for (String[] vakArray : dc.toonSpelbord())
                {
                    for (String vak : vakArray)
                    {
                        System.out.print(vak + " ");
                    }
                    System.out.println();
                }

            } while (!dc.isEindeSpelbord());

            // Als men hier komt is het Spelbord voltooid , oftewel wil de gebruiker stoppen.
            if (keuze == 6)
                break;

            System.out.printf("%n%s%n%n", lang.get("game.board.completed"));
            
            dc.bepaalVolgendSpelbord();
            
            System.out.printf("%s%n 1: %s%n 2: %s%n%s: ",
                    lang.get("list.choose"),
                    lang.get("game.board.next"),
                    lang.get("app.quit"));

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
}
