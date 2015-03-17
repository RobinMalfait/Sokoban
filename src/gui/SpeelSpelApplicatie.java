/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.util.Arrays;
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
        // Welkombericht
        System.out.printf(lang.get("game.welcome"));
        System.out.printf("%s%n", dc.geefHuidigeSpeler()[0]);
        System.out.printf(lang.get("game.choose.list"));

        // Overlopen van de Spellen
        for (String[] spelString : dc.geefSpellenString())
        {
            System.out.printf("%4s: %-20s%n", spelString[0], spelString[1]);
        }

        // Keuze van de Spellen
        System.out.printf(lang.get("game.choose"));
        int spelId = input.nextInt();
        dc.speelSpel(spelId);

        // Het laden van het eerste Spelbord van het spel
        System.out.printf(lang.get("game.playboard.load"));

        do
        {
            for (String[] vakArray : dc.toonSpelbord())
            {
                for (String vak : vakArray)
                {
                    System.out.print(vak + " ");
                }
                System.out.println();
            }

            int keuze;

            do
            {
                System.out.printf("%n%s:%n 1: %s%n 2: %s%n 3: %s%n 4: %s%n 5: %s%n%n%s: ");
                System.out.printf(lang.get("dude.move"));
                System.out.printf(lang.get("dude.up"));
               System.out.printf(lang.get("dude.down"));
                System.out.printf(lang.get("dude.left"));
                 System.out.printf(lang.get("dude.right"));
                 System.out.printf(lang.get("dude.stop"));
                 System.out.printf(lang.get("dude.my.choise"));

                keuze = input.nextInt();

                if (keuze == 5)
                    break;

                dc.verplaatsSpeler(keuze);

                System.out.println();
                for (String[] vakArray : dc.toonSpelbord())
                {
                    for (String vak : vakArray)
                    {
                        System.out.print(vak + " ");
                    }
                    System.out.println();
                }
                
            } while (!dc.isSpelbordVoltooid());
            
            // Als men hier komt is het Spelbord voltooid , oftewel wil de gebruiker stoppen.
            if (keuze == 5)
                break;    
            
            System.out.printf(lang.get("game.complete"));
            dc.bepaalVolgendSpelbord();
            
        } while (!dc.isEindeSpel());

        System.out.printf(lang.get("game.done"));
    }

    public void snelStarten(DomeinController dc, Scanner input, LanguageManager lang)
    {

        dc.meldAan("SpeelSpelTest1", "SpeelSpelTest1");

        this.start(dc, input, lang);
    }
}
