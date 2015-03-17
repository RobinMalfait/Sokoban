/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.util.InputMismatchException;
import java.util.Scanner;
import languages.LanguageManager;

/**
 *
 * @author Demian
 */
public class AdminApplicatie
{
    public void start(DomeinController dc, Scanner input, LanguageManager lang)
    {
        System.out.printf("%s%n1: %s%n2: %s%n3: %s%n", "Wat wilt u doen?", "Een nieuw spel maken", "Een spelbord toevoegen aan een bestaand spel", "Stoppen");
        
        int keuze = 0;
        
        try 
        {
            input.nextLine();
            System.out.print(lang.get("list.choice") + ": ");
            keuze = input.nextInt();
        }
        catch(InputMismatchException e)
        {
            System.out.println("Er werd een geheel getal verwacht.");
        }  
        
        switch(keuze)
        {
            case 1:
        }
    }
    
    public static void maakNieuwSpel(DomeinController dc, Scanner input, LanguageManager lang)
    {
        String naam;
        
        System.out.printf("%s%n%n", "-- Toevoegen van een nieuw spel --");
        System.out.print("Geef een naam voor het spel: ");
        
        naam = input.next();
        
        
        
        
    }
}
