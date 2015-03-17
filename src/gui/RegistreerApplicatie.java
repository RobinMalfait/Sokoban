package gui;

import domein.DomeinController;
import java.util.Scanner;
import languages.LanguageManager;

public class RegistreerApplicatie
{

    public void start(DomeinController dc, Scanner input, LanguageManager lang)
    {
        String naam, voornaam, gebruikersnaam, wachtwoord, wachtwoordBevestiging;
        String[] huidigeSpeler;

        //ingeven van gegevens
        System.out.println(lang.get("registration.new"));

        System.out.print(lang.get("user.firstname") + ": ");
        voornaam = input.nextLine().trim();

        System.out.print(lang.get("user.name") + ": ");
        naam = input.nextLine().trim();

        System.out.print(lang.get("user.username") + "*: ");
        gebruikersnaam = input.nextLine().trim();

        System.out.print(lang.get("user.password") + "*: ");
        wachtwoord = input.nextLine().trim();

        System.out.print(lang.get("user.password.repeat") + "*: ");
        wachtwoordBevestiging = input.nextLine().trim();

        //controle ingevoerde gegevens
        if (gebruikersnaam.isEmpty())
        {
            System.out.print(lang.get("fields.mandatory.username"));
        }

         //|| wachtwoord.isEmpty() || wachtwoordBevestiging.isEmpty()
        if (!wachtwoord.equals(wachtwoordBevestiging))
        {
            System.out.print(lang.get("passwords.compared"));
        }

        dc.registreer(naam, voornaam, gebruikersnaam, wachtwoord, wachtwoordBevestiging);
        huidigeSpeler = dc.geefHuidigeSpeler();

        System.out.printf(lang.get("new.player"));
        System.out.printf(lang.get("user.firstname"));
        System.out.printf("%s%n",huidigeSpeler[0]);
        System.out.printf(lang.get("user.name"));
        System.out.printf("%s%n",huidigeSpeler[1]);
        System.out.printf(lang.get("user.username"));
        System.out.printf("%s%n",huidigeSpeler[2]);
    }
}
