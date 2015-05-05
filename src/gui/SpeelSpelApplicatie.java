    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import exceptions.SpelException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import languages.LanguageManager;

/**
 *
 * @author Demian
 */
public class SpeelSpelApplicatie extends BaseApplicatie
{
    public SpeelSpelApplicatie(DomeinController dc, Scanner input, LanguageManager lang)
    {
        super(dc, input, lang);
    }

    public void start()
    {
        boolean invoerFout = true;
        int keuze = 0;
        
        // Overlopen van de spellen
        System.out.printf("%n%s:%n", lang.get("game.choose.list"));
        for (String[] spelString : dc.geefLijstSpellen())
        {
            System.out.printf("%4s: %-20s%n", spelString[0], spelString[1]);
        }

        // Keuze van de Spellen
        do 
        {
            try 
            {
                String spelId = geefStringIn("%nKies een spel, of type 'stop' om te stoppen");
                
                if(spelId.equals("stop")) 
                    return;
                
                dc.kiesSpel(Integer.valueOf(spelId));
                invoerFout = false;
            }
            catch(SpelException se)
            {
                System.err.println(se.getMessage());
            }
            catch(NumberFormatException e)
            {
                System.err.println(lang.get("err.NaN"));
            }
        }
        while(invoerFout);
        
        // Het laden van het eerste Spelbord van het spel
        System.out.printf("%n%s%n", lang.get("game.board.loading"));
        invoerFout = true;
        do
        {
            this.toonSpelbord();

            do
            {
                System.out.printf("%n%s:%n 1: %s%n 2: %s%n 3: %s%n 4: %s%n 5: %s%n 6: %s%n",
                    lang.get("player.move"),
                    lang.get("player.up"),
                    lang.get("player.down"),
                    lang.get("player.left"),
                    lang.get("player.right"),
                    lang.get("game.board.retry"),
                    lang.get("app.quit")
                );
                
                keuze = invoerMetControle(1, 6);
                
                if (keuze == 6) {
                    break;       
                }
                else if (keuze == 5) {
                    dc.resetSpelbord();
                }
                else {          
                    dc.verplaatsSpeler(keuze);
                }
                this.toonSpelbord();
            } while (!dc.isEindeSpelbord());

            // De gebruiker wenst te stoppen
            if (keuze == 6) {
                System.out.println();
                break;
            }
            
            // Het spelbord is voltooid.
            System.out.printf("%nGefeliciteerd, je voltooide spelbord %d/%d%n%n", dc.geefAantalVoltooideSpelborden(), dc.geefAantalSpelborden());
            
            dc.bepaalVolgendSpelbord();

            if(dc.isEindeSpel())
                break;
            
            //Mogelijkeheid tot stoppen (met invoercontrole)
            System.out.printf("%s%n 1: %s%n 2: %s%n%n",
                    lang.get("list.choose"),
                    lang.get("game.board.next"),
                    lang.get("app.quit")
            );
            
            keuze = this.invoerMetControle(1, 2);
            
            if(keuze == 2) //stoppen
                break;

        } while (!dc.isEindeSpel());

        if(dc.isEindeSpel())
            System.out.printf("%s%n%n", lang.get("game.completed"));
    }
    
    @Override
    public void toonSpelbord()
    {
        super.toonSpelbord();
        
        System.out.printf("%s: %d%n",
                lang.get("game.board.moves"),
                dc.geefAantalVerplaatsingen()
        );
    }
    
}
