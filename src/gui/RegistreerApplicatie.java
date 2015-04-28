package gui;

import domein.DomeinController;
import exceptions.GebruikersnaamException;
import exceptions.WachtwoordException;
import java.util.Scanner;
import languages.LanguageManager;

public class RegistreerApplicatie extends BaseApplicatie
{
    public RegistreerApplicatie(DomeinController dc, Scanner input, LanguageManager lang)
    {
        super(dc, input, lang);
    }

    public void start()
    {
        boolean invoerFout = true;

        String naam, voornaam, gebruikersnaam, wachtwoord, wachtwoordBevestiging;

        //ingeven van gegevens
        System.out.println(lang.get("register.fill.in"));
        
        do
        {
            try
            {
                System.out.print(lang.get("user.firstname") + ": ");
                voornaam = input.nextLine().trim();

                System.out.print(lang.get("user.name") + ": ");
                naam = input.nextLine().trim();

                System.out.print(lang.get("user.username") + ": ");
                gebruikersnaam = input.nextLine().trim();

                System.out.print(lang.get("user.password") + ": ");
                wachtwoord = input.nextLine().trim();

                System.out.print(lang.get("user.password.repeat") + ": ");
                wachtwoordBevestiging = input.nextLine().trim();
                
                dc.registreer(naam, voornaam, gebruikersnaam, wachtwoord, wachtwoordBevestiging);
                invoerFout = false;
            } 
            catch (WachtwoordException | GebruikersnaamException e)
            {
                System.out.printf("%n%s%n%s%n%n", e.getMessage(), lang.get("register.retry"));
            }
        } while (invoerFout);
        
        // Geregistreerd
        System.out.printf("%n%s",lang.get("register.succes"));
        new MeldAanApplicatie(dc, input, lang).toonMenu();

    }
}
