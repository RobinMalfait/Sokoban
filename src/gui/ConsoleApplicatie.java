package gui;

import domein.DomeinController;
import exceptions.TaalException;
import java.util.InputMismatchException;
import java.util.Scanner;
import languages.LanguageManager;

public class ConsoleApplicatie extends BaseApplicatie
{

    public ConsoleApplicatie(DomeinController dc, Scanner input, LanguageManager lang)
    {
        super(dc, input, lang);
    }

    public void start()
    {
        int keuze;
        boolean doorgaan = true;

        do
        {
            System.out.printf("%n%s%n1: %s%n2: %s%n3: %s%n%n",
                    lang.get("list.choose"),
                    lang.get("sign.in"),
                    lang.get("sign.up"),
                    lang.get("app.quit"));

            keuze = invoerMetControle(1, 3, input, lang);

            System.out.println(); // Een extra enter voor de volgende output
            input.nextLine(); // Buffer leegmaken

            switch (keuze)
            {
                case 1:
                    (new MeldAanApplicatie(dc, input, lang)).start();
                    break;
                case 2:
                    (new RegistreerApplicatie(dc, input, lang)).start();
                    break;
                case 3:
                    System.out.println(lang.get("app.quited"));
                    doorgaan = false;
                    break;
                default:
                    System.err.println(lang.get("err.nonvalid"));
            }
        } while (doorgaan);
    }
}
