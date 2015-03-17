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
        System.out.printf(lang.get("game.welcome"));
        System.out.printf("%s%n", dc.geefHuidigeSpeler()[0]);
        System.out.printf("game.play");
        
        System.out.printf(lang.get("game.choose.list"));
        
        for(String[] spelString: dc.geefSpellenString())
        {
            System.out.printf("%10s%20s%n", spelString[0], spelString[1]);
        }
        
        System.out.printf(lang.get("game.choose"));
        int spelId = input.nextInt();
        
        System.out.printf(lang.get("game.playboard.load"));
        dc.speelSpel(spelId);
    }    
}
