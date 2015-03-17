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
        System.out.println(lang.get("registration.new") + ":");

        System.out.print(lang.get("user.firstname") + ": ");
        voornaam = input.nextLine().trim();

        System.out.print(lang.get("user.name") + ": ");
        naam = input.nextLine().trim();

        System.out.print(lang.get("user.username") + "*: ");
        gebruikersnaam = input.nextLine().trim();

        System.out.print(lang.get("user.password") + "*: ");
        wachtwoord = input.nextLine().trim();

        System.out.print(lang.get("user.password.repeat") + "*: ");
        wachtwoordBevestiging = input.next().trim();

        //controle ingevoerde gegevens
        if (gebruikersnaam.isEmpty())
        {
            System.out.println(lang.get("fields.mandatory.username"));
        }

         //|| wachtwoord.isEmpty() || wachtwoordBevestiging.isEmpty()
        if (!wachtwoord.equals(wachtwoordBevestiging))
        {
            System.out.println(lang.get("passwords.compared"));
        }

        dc.registreer(naam, voornaam, gebruikersnaam, wachtwoord, wachtwoordBevestiging);
        huidigeSpeler = dc.geefHuidigeSpeler();

        System.out.printf("%n%s: %n  %s: %s%n  %s: %s%n  %s: %s%n",
                lang.get("user.added"),
                lang.get("user.firstname"),
                huidigeSpeler[0],
                lang.get("user.name"),
                huidigeSpeler[1],
                lang.get("user.username"),
                huidigeSpeler[2]);
    }
}
