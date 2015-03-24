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
        String gebruikersnaam = "", wachtwoord;
        
        //controle inloggegevens
        do 
        {
            try 
            {
                //inloggegevens
                System.out.print(lang.get("user.username") + ": ");
                gebruikersnaam = input.next().trim();

                if(gebruikersnaam.equals(lang.get("sign.quit")))
                    stop = true;
                else
                {
                    // Gebruikersnaam is niet gelijk aan "stop", dus we gaan verder.
                    System.out.print(lang.get("user.password") + ": ");
                    wachtwoord = input.next().trim();
                    
                    dc.meldAan(gebruikersnaam, wachtwoord);
                    invoerFout = false;                        
                }              
            }
            catch(WachtwoordException e)
            {
                System.out.printf("%n%s%n%s%n%n", e.getMessage(), lang.get("sign.retry"));
            }
        }
        while(invoerFout && !stop);
        
        if(!stop) {
            // De gebruiker is succesvol ingelogd.
            System.out.printf("%n%s %s, %s%n%n",lang.get("game.welcome"), gebruikersnaam,lang.get("sign.succes"));
            
            toonMenu(dc, input, lang); 
        } 
    }
    
    public static void toonMenu(DomeinController dc, Scanner input, LanguageManager lang)
    {
        // De mogelijke keuzes weergeven
        System.out.printf("%s%n1: %s%n2: %s%n%n", lang.get("sign.choise"), lang.get("sign.play"), lang.get("sign.quit"));
        if(dc.isAdmin())
        {
            System.out.printf("3: %s%n4: %s%n%n", lang.get("sign.game.conf"), lang.get("sign.game.modify"));
        }
        
        // Keuze maken
        System.out.print(lang.get("sign.choise"));
        int keuze = input.nextInt();
        
        switch(keuze)
        {
            case 1:
                new SpeelSpelApplicatie().start(dc, input, lang);
                break;
            case 2:
                System.out.println(lang.get("sign.quitted"));
                break;                
            case 3:
                new AdminApplicatie().start(dc, input, lang);
                break;
            case 4:
                new AdminApplicatie().start(dc, input, lang);
                break;           
        }
    }
}
