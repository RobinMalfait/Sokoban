
package gui;

import domein.DomeinController;
import java.util.Scanner;
import languages.LanguageManager;

public class RegistreerApplicatie {

    public void start(DomeinController dc)
    {   
        Scanner input = new Scanner(System.in);
        LanguageManager lang = dc.getLanguageManager();
        
        System.out.printf("Geef je taal, keuze uit (%s): ", lang.getKeuzes());
        lang.setLanguage(input.next());
        
        if(dc.meldAan("DemianD", "demian123"))
        {
            System.out.println(lang.get("user.logged.in"));
        }
        else {
            System.out.println(lang.get("credentials.wrong"));
        } 
       
    }
}
