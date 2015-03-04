/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
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
        
        dc.speelSpel();
        System.out.printf("%s%n", dc.toonSpelbord());
    }    
}
