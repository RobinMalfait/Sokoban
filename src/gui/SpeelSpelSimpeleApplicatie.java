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
public class SpeelSpelSimpeleApplicatie
{
    public void start(DomeinController dc, Scanner input, LanguageManager lang)
    {
        dc.meldAan("SpeelSpelTest1", "SpeelSpelTest1");
        
        System.out.printf("Welkom %s%n", dc.geefHuidigeSpeler()[0]);
        System.out.printf("U zult nu een spel spelen.%n%n");
        
        System.out.printf("Het spelbord wordt geladen:%n%n");
        dc.speelSpel(1);
        
        for(String[] vakArray : dc.toonSpelbord())
        {
            for(String vak : vakArray)
            {
                System.out.print(vak + " ");
            }
            System.out.println();
        }
    }    
}
