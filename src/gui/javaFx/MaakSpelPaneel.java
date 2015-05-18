package gui.javaFx;

import exceptions.SpelException;
import static gui.javaFx.BaseGui.DC;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author robin
 */
public class MaakSpelPaneel extends BaseGui
{
    private Label errorLabel;

    /**
     * Run het MaakSpelbordPaneel
     */
    public void run()
    {
        this.init();
        this.reset();
    }

    /**
     * Initialiseer het paneel
     */
    private void init()
    {
        stage.setTitle("Maak nieuw spel");

        this.show("#MaakSpelPaneel");
                 
        this.errorLabel = (Label) this.findByIdInPane("lblError");
        
        Label lblSpelnaam = (Label) this.findByIdInPane("lblSpelnaam");
        lblSpelnaam.setText(lang.get("game.new") + ":");
        
        this.findByIdInPane("back").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                (new SubMenuPaneel()).run();
            }
        });
        
               
        this.findByIdInPane("newGameBtn").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent event)
            {                
                TextField txtSpelNaam = (TextField) MaakSpelPaneel.this.findByIdInPane("txtSpelnaam");
                String spelNaam = txtSpelNaam.getText();
                
                if (spelNaam == null || spelNaam.equals("")) 
                {
                    setError("Vul een naam voor het nieuwe spel in!");
                } 
                else 
                {
                    cleanError();
                    maakNieuwSpel(spelNaam);
                }
            }
        });
    }  

    /**
     * Stel de error in
     * 
     * @param text 
     */
    private void setError(String text)
    {
        this.errorLabel.setText(text);
        this.errorLabel.setVisible(true);
    }

    /**
     * Maak de error leeg
     */
    private void cleanError()
    {
        this.setError(""); // Clear the text
    }

    /**
     * Maak een nieuw spel aan
     * 
     * @param name 
     */
    private void maakNieuwSpel(String spelNaam)
    {
        try {
            DC.voegSpelToe(spelNaam);
            (new MaakSpelbordPaneel()).run();
        }
        catch(SpelException e)
        {
            setError(e.getMessage());
        }
    }

    /**
     * Reset het paneel
     */
    protected void reset()
    {
        setError("");
        
        TextField txtSpelNaam = (TextField) MaakSpelPaneel.this.findByIdInPane("txtSpelnaam");
        txtSpelNaam.setText("");
    }
}
