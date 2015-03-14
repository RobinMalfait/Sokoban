package gui;

import domein.DomeinController;
import exceptions.TaalException;
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
        
        try 
        {
            System.out.printf("Geef je taal, keuze uit (%s): ", lang.getKeuzes());
            lang.setLanguage(input.next());
        }
        catch (TaalException e)
        {
            System.out.println(e.getMessage());
        }
        
        System.out.printf("%s%n1: %s%n2: %s%n3: %s%n4: %s%n%s: ",
                "Wat wenst u te doen?",
                lang.get("sign.in"),
                lang.get("sign.up"),
                "test speel spel",
                "stoppen",
                "Mijn keuze");
        
        keuze = input.nextInt();
        System.out.println();
        
        // Buffer leegmaken
        input.nextLine();
        
        switch(keuze) {
            case 1: 
                (new MeldAanApplicatie()).start(dc, input, lang); 
                break;
            case 2: 
                (new RegistreerApplicatie()).start(dc, input, lang); 
                break;
            case 3:
                (new SpeelSpelApplicatie()).snelStarten(dc, input, lang);
                break;
            case 4:
                System.out.println("Gestopt.");
                break;
            default: 
                System.err.println("Geen geldige keuze.");
        }            
    }
}
