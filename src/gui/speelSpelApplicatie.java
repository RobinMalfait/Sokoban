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
public class speelSpelApplicatie
{
    public void start(DomeinController dc, Scanner input, LanguageManager lang)
    {
        
        System.out.printf("Welkom %s%n", dc.geefHuidigeSpeler()[0]);
        System.out.printf("U zult nu een spel spelen.%n");
        
        System.out.printf("%nKies een spel uit de lijst hieronder:%n");
        
        for(String[] spelString: dc.geefSpellenString())
        {
            System.out.printf("%10s%20s%n", spelString[0], spelString[1]);
        }
        
        System.out.printf("%nKies een spel: ");
        int spelId = input.nextInt();
        
        System.out.printf("Het spelbord wordt geladen:%n");
        dc.speelSpel(spelId);
    }    
}
