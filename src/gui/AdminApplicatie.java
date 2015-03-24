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
        
        System.out.printf("%s%n1: %s", 
                    "Wat wenst u te doen?", 
                    "Een nieuwe spel configureren");
        
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
        }
    }
    
    public static void maakNieuwSpel(DomeinController dc, Scanner input, LanguageManager lang)
    {
        System.out.printf("%n%s%n", lang.get("horizontal.line"));
        
        // Verbetering zou zijn om een naam met spaties toe te laten. Daarvoor input.nextLine() gebruiken..
        System.out.print("Geef een naam voor het nieuwe spel: ");
        String naam = input.next();
        
        dc.voegSpelToe(naam); // Een object van klasse Spel is aangemaakt, niet opgeslaan in de database.
        
        maakNieuwSpelbord(dc, input, lang);
        
        input.nextLine(); // buffer leegmaken
        start(dc, input, lang);
    }
    
    public static void maakNieuwSpelbord(DomeinController dc, Scanner input, LanguageManager lang)
    {
        
        boolean invoerFout = true;
        String spelbordNaam;
        
        // 1. Naam voor het spelbord
        System.out.printf("%n%nGeef een naam voor het spelbord:");
        spelbordNaam = input.next();
        
        dc.voegSpelbordToe(spelbordNaam);
        
        dc.toonSpelbord();
       
        
        
        
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
