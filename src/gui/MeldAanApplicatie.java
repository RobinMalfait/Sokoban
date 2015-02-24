package gui;

import domein.DomeinController;
import java.sql.SQLException;

/**
 *
 * @author robin
 */
public class MeldAanApplicatie 
{
    public void start(DomeinController domeinController)
    {       
        
        if(domeinController.meldAan("DemianD", "demian123"))
        {
            System.out.println("Ingelogd");
        }
        else {
            System.out.println("Foute gegegevens");
        }
        
    }
}
