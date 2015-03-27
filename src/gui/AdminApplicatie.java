/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import exceptions.SpelException;
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

        System.out.printf("%s%n1: %s%n",
                "Wat wenst u te doen?",
                "Een nieuwe spel configureren");

        int keuze = 0;

        try
        {
            System.out.printf("%n%s: ", lang.get("list.choice"));
            keuze = input.nextInt();
            input.nextLine(); // buffer leegmaken
        } 
        catch (InputMismatchException e)
        {
            System.out.println("Er werd een geheel getal verwacht.");
        }

        // Keuze bepalen
        switch (keuze)
        {
            case 1:
                maakNieuwSpel(dc, input, lang);
                break;
        }
    }

    public static void maakNieuwSpel(DomeinController dc, Scanner input, LanguageManager lang)
    {
        System.out.printf("%n%s%n", lang.get("horizontal.line"));

        // Verbetering zou zijn om een naam met spaties toe te laten. Daarvoor input.nextLine() gebruiken..
        System.out.print("Geef een naam voor het nieuwe spel: ");
        String naam = input.next();

        dc.voegSpelToe(naam); // Een object van klasse Spel is aangemaakt, niet opgeslaan in de database.
        System.out.println("Een spel werd aangemaakt, u zult nu een spelbord toevoegen.");

        maakNieuwSpelbord(dc, input, lang);

        input.nextLine(); // buffer leegmaken
        start(dc, input, lang);
    }

    public static void maakNieuwSpelbord(DomeinController dc, Scanner input, LanguageManager lang)
    {
        boolean invoerFout = true;
        boolean doorgaan = true;

        String spelbordNaam;

        // 1. Naam voor het spelbord
        System.out.printf("Geef een naam voor het spelbord:");
        spelbordNaam = input.next();

        dc.voegSpelbordToe(spelbordNaam);
        System.out.println();
        System.out.println();

        toonSpelbord(dc);

        System.out.println();

        String coordinaat = "";
        String keuze = "";
        do
        {
            System.out.printf("%nVoer een coördinaat in of typ 'stop': ");
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

            dc.voerVakIn(coordinaat, keuze);
            toonSpelbord(dc);
        } while (doorgaan);

        dc.slaHuidigSpelOp();
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

}
