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
                voornaam = geefStringIn(lang.get("user.firstname"));
                naam = geefStringIn(lang.get("user.name"));
                gebruikersnaam = geefStringIn(lang.get("user.username"));
                wachtwoord = geefStringIn(lang.get("user.password"));
                wachtwoordBevestiging = geefStringIn(lang.get("user.password.repeat"));
                
                dc.registreer(naam, voornaam, gebruikersnaam, wachtwoord, wachtwoordBevestiging);
                invoerFout = false;
            } 
            catch (WachtwoordException | GebruikersnaamException e)
            {
                System.out.printf("%n%s%n%s%n%n", e.getMessage(), lang.get("register.retry"));
            }
        } while (invoerFout);
        
        // Geregistreerd
        System.out.printf("%n%s%n",lang.get("register.succes"));
        new MeldAanApplicatie(dc, input, lang).toonMenu();

    }
}
