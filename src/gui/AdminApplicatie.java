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
        
        System.out.printf("%s%n1: %s%n2: %s%n3: %s%n4: %s%n5: %s%n6: %s%n7: %s%n8: %s%n", 
                    "Wat wenst u te doen?", 
                    "Een spel maken", 
                    "Een spel wijzigen", 
                    "Een spel verwijderen", 
                    "Een spelbord toevoegen", 
                    "Een spelbord wijzigen", 
                    "Een spelbord verwijderen", 
                    "Terug naar vorig menu",
                    "Stoppen");
        
        int keuze = 0;
        
        try 
        {
            System.out.printf("%n%s: ", lang.get("list.choice"));
            keuze = input.nextInt();
            input.nextLine(); // buffer leegmaken
        }
        catch(InputMismatchException e)
        {
            System.out.println("Er werd een geheel getal verwacht.");
        }  
        
        // Keuze bepalen
        switch(keuze)
        {
            case 1:
                maakNieuwSpel(dc, input, lang);
                break;
            case 2:
                wijzigSpel(dc, input, lang);
                break;                
        }
    }
    
    public static void maakNieuwSpel(DomeinController dc, Scanner input, LanguageManager lang)
    {
        System.out.printf("%n%s%n", lang.get("horizontal.line"));
        
        // Verbetering zou zijn om een naam met spaties toe te laten. Daarvoor input.nextLine() gebruiken..
        System.out.print("Geef een naam voor het nieuwe spel: ");
        String naam = input.next();
        
        dc.voegSpelToe(naam);
        System.out.print("Het spel is succesvol toegevoegd.");
        
        System.out.printf("%n%s%n%n", lang.get("horizontal.line"));
        
        input.nextLine(); // buffer leegmaken
        start(dc, input, lang);
    }
    
    public static void wijzigSpel(DomeinController dc, Scanner input, LanguageManager lang)
    {
        System.out.printf("%n%s%n", lang.get("horizontal.line"));
        
        System.out.printf("%s%n", toonSpellen(dc, lang));
        
        System.out.print("Geef het nummer van het spel dat je wilt wijzigen: ");
        int spelnummer = input.nextInt();
    }    
    
    public static void maakNieuwSpelbord(DomeinController dc, Scanner input, LanguageManager lang)
    {
        boolean invoerFout = true;
        int spelnummer = 0;
        String spelbordNaam;
        
        // 1. Kies een bijhorend spel, zoniet stoppen
        System.out.printf("%s%n%n", lang.get("horizontal.line"));

        for (String[] spelString : dc.geefSpellenString())
        {
            System.out.printf("%4s: %-20s%n", spelString[0], spelString[1]);
        }
        
        System.out.printf("%nHierboven vind je een lijst met alle bestaande spellen.%n");
        
        do {
            try 
            {
                input.nextLine(); 
                
                System.out.print("Kies het spel waar je een spelbord aan wil toevoegen:");
                spelnummer = input.nextInt();

                dc.kiesSpel(spelnummer);
                invoerFout = false;                
            }
            catch(InputMismatchException e)
            {
                System.out.println("Er werd een geheel getal verwacht.");
            }         
            catch(SpelException se)
            {
                System.out.println(se.getMessage());
            }
        }
        while(invoerFout);

        // 2. Naam voor het spelbord
        System.out.printf("%n%nGeef een naam voor het spelbord:");
        spelbordNaam = input.next();
        
        // 3. Alle 100 vakken van het spelbord overlopen.
        System.out.printf("%n%nNu gaan we alle vakken van het nieuwe spelbord overlopen:%n");
        int[][] vakken = new int[10][10];
        
        for(int x = 0; x < 10; x++)
        {
            for(int y = 0; y < 10; y++)
            {
                System.out.printf("(%d,%d) : ", x+1, y+1);
                vakken[x][y] = 1;
            }
        }
        
        // 4. Toevoegen
        dc.voegSpelbordToe(spelbordNaam, vakken);
        
    }
    
    public static String toonSpellen(DomeinController dc, LanguageManager lang)
    {
        String output = "Overzicht van de spellen:";
 
        for (String[] spelString : dc.geefSpellenString())
        {
            output += String.format("%n%4s: %-20s", spelString[0], spelString[1]);
        }
        
        return output;
    }
}
