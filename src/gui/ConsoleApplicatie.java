package gui;

import domein.DomeinController;
import exceptions.TaalException;
import java.util.InputMismatchException;
import java.util.Scanner;
import languages.EN;
import languages.FR;
import languages.LanguageManager;
import languages.NL;

public class ConsoleApplicatie
{

    public void start(DomeinController dc, Scanner input)
    {
        int keuze = 0;
        boolean invoerFout = true;
        
        LanguageManager lang = new LanguageManager();

        lang.addLanguage(new NL());
        lang.addLanguage(new FR());
        lang.addLanguage(new EN());

        do
        {
            try
            {
                System.out.printf("Geef je taal, keuze uit (%s): ", lang.getKeuzes());
                lang.setLanguage(input.next().toUpperCase());
                invoerFout = false;
            } 
            catch (TaalException e)
            {
                System.out.println(e.getMessage());
            }
        } while (invoerFout);
        
        System.out.printf("%n%s%n1: %s%n2: %s%n3: %s%n4: %s%n5: %s%n%n",
                lang.get("list.choose"),
                lang.get("sign.in"),
                lang.get("sign.up"),
                "Test speel spel (voorlopig)",
                "Admin",
                lang.get("app.quit"));

        invoerFout = true;
        do 
        {
            try 
            {
                input.nextLine();
                System.out.print(lang.get("list.choice") + ": ");
                keuze = input.nextInt();
                invoerFout = false;
            }
            catch(InputMismatchException e)
            {
                System.out.println("Er werd een geheel getal verwacht.");
            }
        } while(invoerFout);
        
        System.out.println(); // Een extra enter voor de volgende output
        input.nextLine(); // Buffer leegmaken

        switch (keuze)
        {
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
                (new AdminApplicatie()).start(dc, input, lang);
                break;                
            case 5:
                System.out.println(lang.get("app.quited"));
                break;
            default:
                System.err.println("Geen geldige keuze.");
        }
    }
}
