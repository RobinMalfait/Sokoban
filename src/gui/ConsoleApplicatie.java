
package gui;

import domein.DomeinController;
import java.util.Scanner;
import languages.EN;
import languages.FR;
import languages.LanguageManager;
import languages.NL;

public class ConsoleApplicatie {

    public void start(DomeinController dc, Scanner input)
    {
        int keuze;
        
        LanguageManager lang = new LanguageManager();

        lang.addLanguage(new NL());
        lang.addLanguage(new FR());
        lang.addLanguage(new EN());
        
        System.out.printf("Geef je taal, keuze uit (%s): ", lang.getKeuzes());
        lang.setLanguage(input.next());
        
        System.out.printf("Wat wenst u te doen?%n1: "+ lang.get("sign.in") +"%n2: "+ lang.get("sign.up") +"%nMijn keuze: ");
        keuze = input.nextInt();
        
        System.out.println();
        
        switch(keuze) {
            case 1: (new MeldAanApplicatie()).start(dc, input, lang); break;
            case 2: (new RegistreerApplicatie()).start(dc, input, lang); break;
        }            
    }
}
