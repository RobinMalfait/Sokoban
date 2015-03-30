package gui;

import domein.DomeinController;
import exceptions.TaalException;
import java.util.InputMismatchException;
import java.util.Scanner;
import languages.LanguageManager;

public class ConsoleApplicatie
{

    public void start(DomeinController dc, Scanner input, LanguageManager lang)
    {
        
        int keuze;
        
        System.out.printf("%n%s%n1: %s%n2: %s%n3: %s%n4: %s%n5: %s%n%n",
                lang.get("list.choose"),
                lang.get("sign.in"),
                lang.get("sign.up"),
                "Test speel spel (voorlopig)",
                "Admin",
                lang.get("app.quit"));

        keuze = invoerMetControle(1, 5, input, lang);
        
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
                (new AdminApplicatie()).snelStarten(dc, input, lang);
                break;
            case 5:
                System.out.println(lang.get("app.quited"));
                break;
            default:
                System.err.println(lang.get("err.nonvalid"));
        }
    }
    
    private int invoerMetControle(int ondergrens, int bovengrens, Scanner input, LanguageManager lang)
    {
        int keuze = 0;
        boolean fouteInvoer = true;
        do
        {
            try
            {                
                System.out.printf("%s: ", lang.get("list.choice"));
                keuze = input.nextInt();

                if (keuze < ondergrens || keuze > bovengrens)
                {
                    throw new IllegalArgumentException(lang.get("err.input", 
                        "min", ondergrens, 
                        "max", bovengrens));
                }
                
                fouteInvoer = false;
            }
            catch (IllegalArgumentException e)
            {
                System.err.println(e.getMessage());
                input.nextLine();
            }
            catch (InputMismatchException e)
            {
                System.err.println(lang.get("err.NaN"));
                input.nextLine();
            }

        } while (fouteInvoer);
                
        return keuze;
    }
}
