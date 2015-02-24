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
        LanguageManager lang = new LanguageManager();
        
        System.out.printf("Geef je taal, keuze uit (%s): ", lang.getKeuzes());
        lang.setLanguage(input.next());
        
        if(domeinController.meldAan("DemianD", "demian123"))
        {
            System.out.println(lang.get("user.logged.in"));
        }
        else {
            System.out.println(lang.get("credentials.wrong"));
        }
        
    }
}
