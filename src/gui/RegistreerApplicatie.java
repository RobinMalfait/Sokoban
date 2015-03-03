package gui;

import domein.DomeinController;
import java.util.Scanner;
import languages.LanguageManager;

public class RegistreerApplicatie
{

    public void start(DomeinController dc, Scanner input, LanguageManager lang)
    {
        String naam, voornaam, gebruikersnaam, wachtwoord, wachtwoordBevestiging;
        boolean admin;
        String[] huidigeSpeler;

        //ingeven van gegevens
        System.out.print("Registratie van een nieuwe gebruiker: ");
        System.out.println();

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
        
        System.out.print(lang.get("user.admin") + " (true or false): ");
        admin = input.nextBoolean();

        //controle ingevoerde gegevens
        if (gebruikersnaam.isEmpty())
        {
            System.out.print(lang.get("mandatory.fields.username"));
        } 
        
         //|| wachtwoord.isEmpty() || wachtwoordBevestiging.isEmpty()
        
        else if ( ! wachtwoord.equals(wachtwoordBevestiging))
        {
            System.out.print(lang.get("compared.passwords"));
            
        }
        else
        {
            input.nextLine();

            dc.registreer(naam, voornaam, gebruikersnaam, wachtwoord, wachtwoordBevestiging, admin);
            huidigeSpeler = dc.geefHuidigeSpeler();
            
            System.out.printf("%nNieuwe speler toegevoegd: %nvoornaam: %s%nnaam: %s%ngebruikersnaam: %s%n", huidigeSpeler[0], huidigeSpeler[1], huidigeSpeler[2]);
        }
        

    }
}
