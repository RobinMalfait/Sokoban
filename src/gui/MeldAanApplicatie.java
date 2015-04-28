package gui;

import domein.DomeinController;
import exceptions.WachtwoordException;
import java.util.Scanner;
import languages.LanguageManager;

/**
 *
 * @author robin
 */
public class MeldAanApplicatie extends BaseApplicatie
{
    public MeldAanApplicatie(DomeinController dc, Scanner input, LanguageManager lang)
    {
        super(dc, input, lang);
    }
    
    public void start()
    {   
        boolean invoerFout = true;
        boolean stop = false;
        
        String gebruikersnaam = "";
        String wachtwoord = "";

        do 
        {
            System.out.print(lang.get("user.username") + ": ");
            gebruikersnaam = input.next(); input.nextLine();

            if(gebruikersnaam.equals(lang.get("sign.quit")))
            {
                stop = true;
            }
            else
            {
                // Gebruikersnaam is niet gelijk aan "stop"
                System.out.print(lang.get("user.password") + ": ");
                wachtwoord = input.next(); input.nextLine();
                
                try 
                {   
                    dc.meldAan(gebruikersnaam, wachtwoord);
                    invoerFout = false;                        
                }       
                catch(WachtwoordException e)
                {
                    System.out.printf("%n%s%n%s%n%n", e.getMessage(), lang.get("sign.retry"));
                }                
            }
        }
        while(invoerFout && !stop);
        
        if(!stop) {
            // De gebruiker is succesvol ingelogd.
            System.out.printf("%n%s %s %s, %s%n%n",lang.get("game.welcome"), (dc.isAdmin() ? "administrator" : ""), gebruikersnaam,lang.get("sign.succes"));
            
            toonMenu(); 
        } 
    }
    
    public void toonMenu()
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
                new SpeelSpelApplicatie(dc, input, lang).start();
                break;
            case 2:
                System.out.println(lang.get("sign.quitted"));
                break;                
            case 3:
                new AdminApplicatie(dc, input, lang).start();
                break;
            case 4:
                new AdminApplicatie(dc, input, lang).start();
                break;           
        }
    }
}
