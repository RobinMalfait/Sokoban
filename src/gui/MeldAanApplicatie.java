package gui;

import domein.DomeinController;
import java.util.Scanner;
import languages.LanguageManager;

/**
 *
 * @author robin
 */
public class MeldAanApplicatie 
{
    public void start(DomeinController dc, Scanner input, LanguageManager lang)
    {   
        String gebruikersnaam, wachtwoord;
        
        //inloggegevens
        System.out.print(lang.get("user.username") + "*: ");
        gebruikersnaam = input.next().trim();

        System.out.print(lang.get("user.password") + "*: ");
        wachtwoord = input.next().trim();
        
        //controle inloggegevens
        dc.meldAan(gebruikersnaam, wachtwoord);
        
        System.out.printf("%n%s%n%n",lang.get("user.logged.in"));
        
        new SpeelSpelApplicatie().start(dc, input, lang); 
        
        
    }
}
