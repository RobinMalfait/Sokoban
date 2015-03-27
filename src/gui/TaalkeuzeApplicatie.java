package gui;

import domein.DomeinController;
import exceptions.TaalException;
import java.util.InputMismatchException;
import java.util.Scanner;
import languages.LanguageManager;

public class TaalkeuzeApplicatie
{

    public void start(DomeinController dc, Scanner input)
    {
        int keuze = 0;
        boolean invoerFout = true;
        LanguageManager lang = dc.getLang();
        
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
                System.err.println(e.getMessage());
            }
        } while (invoerFout);

        new ConsoleApplicatie().start(dc, input, lang);
    }
    
}