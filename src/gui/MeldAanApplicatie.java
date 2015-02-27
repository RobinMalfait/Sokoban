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
    public void start(DomeinController domeinController)
    {   
        Scanner input = new Scanner(System.in);
        LanguageManager lang = domeinController.getLanguageManager();
        String gebruikersnaam, wachtwoord;
        
        //taalkeuze
        System.out.printf("Geef je taal, keuze uit (%s): ", lang.getKeuzes());
        lang.setLanguage(input.next());
        
        //inloggegevens
        System.out.print(lang.get("user.username") + "*: ");
        gebruikersnaam = input.nextLine().trim();

        System.out.print(lang.get("user.password") + "*: ");
        wachtwoord = input.nextLine().trim();
        
        //controle inloggegevens
        if(domeinController.meldAan(gebruikersnaam, wachtwoord))
        {
            System.out.println(lang.get("user.logged.in"));
        }
        else {
            System.out.println(lang.get("credentials.wrong"));
        } 
       
    }
}
