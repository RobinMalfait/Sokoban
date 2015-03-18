package gui;

import domein.DomeinController;
import exceptions.GebruikersnaamException;
import exceptions.WachtwoordException;
import java.util.Scanner;
import languages.LanguageManager;

public class RegistreerApplicatie
{

    public void start(DomeinController dc, Scanner input, LanguageManager lang)
    {
        boolean invoerFout = true;

        String naam, voornaam, gebruikersnaam, wachtwoord, wachtwoordBevestiging;

        //ingeven van gegevens
        System.out.println("Vul volgende gegevens in om u te registreren:");
        
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
                System.out.printf("%n%s%nProbeer opnieuw%n%n", e.getMessage());
            }
        } while (invoerFout);
        
        // Geregistreerd
        System.out.printf("%nU bent succesvol geregisteerd!");
        new MeldAanApplicatie().toonMenu(dc, input, lang);

    }
}
