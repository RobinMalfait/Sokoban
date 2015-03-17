package gui;

import domein.DomeinController;
import exceptions.WachtwoordException;
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
        boolean invoerFout = true;
        boolean stop = false;
        String gebruikersnaam, wachtwoord;
        
        //controle inloggegevens
        do 
        {
            try 
            {
                //inloggegevens
                System.out.print(lang.get("user.username") + "*: ");
                gebruikersnaam = input.next().trim();

                if(gebruikersnaam.equals("stop"))
                    stop = true;
                else
                {
                    // Gebruikersnaam is niet gelijk aan "stop", dus we gaan verder.
                    System.out.print(lang.get("user.password") + "*: ");
                    wachtwoord = input.next().trim();
                    
                    dc.meldAan(gebruikersnaam, wachtwoord);
                    invoerFout = false;                        
                }              
            }
            catch(WachtwoordException e)
            {
                System.out.printf("%n%s%nProbeer opnieuw, op typ: stop%n%n", e.getMessage());
            }
        }
        while(invoerFout && !stop);
        
        if(!stop) {
            System.out.printf("%n%s%n%n",lang.get("user.logged.in"));
            new SpeelSpelApplicatie().start(dc, input, lang);           
        }
 
    }
}
