package gui;

import domein.DomeinController;
import exceptions.WachtwoordException;
import java.util.Scanner;
import languages.LanguageManager;

/**
 *
 * @author robin
 */
public class MeldAanApplicatie extends BaseApplicatie
{

    public MeldAanApplicatie(DomeinController dc, Scanner input, LanguageManager lang)
    {
        super(dc, input, lang);
    }

    public void start()
    {
        boolean invoerFout = true;
        boolean stop = false;

        String gebruikersnaam = "";
        String wachtwoord = "";

        do
        {
            gebruikersnaam = geefStringIn(lang.get("user.username") + " (type 'stop' om te stoppen)");
            
            if (gebruikersnaam.toLowerCase().equals("stop"))
            {
                stop = true;
            } 
            else
            {
                // Gebruikersnaam is niet gelijk aan "stop"
                wachtwoord = geefStringIn(lang.get("user.password"));
                
                try
                {
                    dc.meldAan(gebruikersnaam, wachtwoord);
                    invoerFout = false;
                } 
                catch (WachtwoordException e)
                {
                    System.out.printf("%n%s%n%n", e.getMessage());
                }
            }
        } while (invoerFout && !stop);

        if (!stop)
        {
            // De gebruiker is succesvol ingelogd.
            System.out.printf("%n%s %s%s, %s%n%n", lang.get("game.welcome"), (dc.isAdmin() ? "administrator " : ""), gebruikersnaam, lang.get("sign.succes"));
            toonMenu();
        }
    }

    public void toonMenu()
    {
        boolean doorgaan = true;
        do
        {
            // De mogelijke keuzes weergeven
            int maxKeuzenummer = 2;
            System.out.printf("%s%n1: %s%n2: %s%n", lang.get("sign.choise"), lang.get("sign.play"), lang.get("sign.quit"));
            if (dc.isAdmin())
            {
                maxKeuzenummer = 4;
                System.out.printf("3: %s%n4: %s%n", lang.get("sign.game.conf"), lang.get("sign.game.modify"));
            }

            // Keuze maken
            System.out.printf("%n%s%n", lang.get("sign.choise"));
            int keuze = invoerMetControle(1, maxKeuzenummer);
            
            switch (keuze)
            {
                case 1:
                    new SpeelSpelApplicatie(dc, input, lang).start();
                    break;
                case 2:
                    doorgaan = false;
                    break;
                case 3:
                    new AdminApplicatie(dc, input, lang).maakNieuwSpel();
                    break;
                case 4:
                    new AdminApplicatie(dc, input, lang).wijzigSpel();
                    break;
            }
        } while (doorgaan);
    }
}
