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
        System.out.printf("Welkom %s%n", dc.geefHuidigeSpeler()[0]);
        System.out.printf("%nKies een spel uit de lijst hieronder:%n");

        // Overlopen van de Spellen
        for (String[] spelString : dc.geefSpellenString())
        {
            System.out.printf("%4s: %-20s%n", spelString[0], spelString[1]);
        }

        // Keuze van de Spellen
        System.out.printf("Kies een spel: ");
        int spelId = input.nextInt();
        dc.speelSpel(spelId);

        // Het laden van het eerste Spelbord van het spel
        System.out.printf("%nHet spelbord wordt geladen:%n");

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
                System.out.printf("%n%s:%n 1: %s%n 2: %s%n 3: %s%n 4: %s%n 5: %s%n%n%s: ",
                        "Verplaats het mannetje in een richting",
                        "Omhoog",
                        "Omlaag",
                        "Links",
                        "Rechts",
                        "Stoppen",
                        "Mijn keuze");

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
            
            System.out.printf("%n Gefeliciteerd! Het spelbord is voltooid!.%n%n");
            dc.bepaalVolgendSpelbord();
            
        } while (!dc.isEindeSpel());

        System.out.printf("%nSpel voltooid.%n");
    }

    public void snelStarten(DomeinController dc, Scanner input, LanguageManager lang)
    {

        dc.meldAan("SpeelSpelTest1", "SpeelSpelTest1");

        this.start(dc, input, lang);
    }
}
