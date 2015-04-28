/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.util.InputMismatchException;
import java.util.Scanner;
import languages.LanguageManager;

/**
 *
 * @author Demian
 */
public class BaseApplicatie
{

    protected DomeinController dc;
    protected Scanner input;
    protected LanguageManager lang;

    public BaseApplicatie(DomeinController dc, Scanner input, LanguageManager lang)
    {
        this.dc = dc;
        this.input = input;
        this.lang = lang;
    }

    protected int invoerMetControle(int ondergrens, int bovengrens, Scanner input, LanguageManager lang)
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
            } catch (IllegalArgumentException e)
            {
                System.err.println(e.getMessage());
                input.nextLine();
            } catch (InputMismatchException e)
            {
                System.err.println(lang.get("err.NaN"));
                input.nextLine();
            }

        } while (fouteInvoer);

        return keuze;
    }

}
