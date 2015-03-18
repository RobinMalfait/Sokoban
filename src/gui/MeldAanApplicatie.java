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

                if(gebruikersnaam.equals("stop"))
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
                System.out.printf("%n%s%nProbeer opnieuw, of typ: stop%n%n", e.getMessage());
            }
        }
        while(invoerFout && !stop);
        
        if(!stop) {
            // De gebruiker is succesvol ingelogd.
            System.out.printf("%nWelkom %s, u hebt zich succesvol ingelogd.%n%n", gebruikersnaam);
            
            toonMenu(dc, input, lang); 
        } 
    }
    
    public static void toonMenu(DomeinController dc, Scanner input, LanguageManager lang)
    {
        // De mogelijke keuzes weergeven
        System.out.printf("%s%n1: %s%n2: %s%n%n", "Wat wenst u te doen?", "Een spel spelen", "Stoppen");
        if(dc.isAdmin())
        {
            System.out.printf("3: %s%n4: %s%n%n", "Configureer nieuw spel", "Wijzig een spel");
        }
        
        // Keuze maken
        System.out.print("Wat wenst u te doen? ");
        int keuze = input.nextInt();
        
        switch(keuze)
        {
            case 1:
                new SpeelSpelApplicatie().start(dc, input, lang);
                break;
            case 2:
                System.out.println("Gestopt");
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
