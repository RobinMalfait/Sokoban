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
        
        System.out.printf("%s%n1: %s%n2: %s%n3: %s%n", "Wat wilt u doen?", "Een nieuw spel maken", "Een spelbord toevoegen aan een bestaand spel", "Stoppen");
        
        int keuze = 0;
        
        try 
        {
            System.out.printf("%n%s: ", lang.get("list.choice"));
            keuze = input.nextInt();
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
                maakNieuwSpelbord(dc, input, lang);
                break;                
        }
    }
    
    public static void maakNieuwSpel(DomeinController dc, Scanner input, LanguageManager lang)
    {
        System.out.println(lang.get("horizontal.line"));
        
        // Verbetering zou zijn om een naam met spaties toe te laten. Daarvoor input.nextLine() gebruiken..
        System.out.print("Geef een naam voor het nieuwe spel: ");
        String naam = input.next();
        
        dc.voegSpelToe(naam);
        System.out.println("Het spel is succesvol toegevoegd.");
        
        System.out.println(lang.get("horizontal.line"));
        start(dc, input, lang);
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
}
