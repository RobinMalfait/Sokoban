package gui.javaFx;

import domein.DomeinController;
import java.io.IOException;
import javafx.stage.Stage;


/**
 *
 * @author robin
 */
public class StartUpGui extends BaseGui
{

    /**
     * Maak het StartUpGui-object ana
     * 
     * @param dc 
     */
    public StartUpGui(DomeinController dc)
    {
        BaseGui.DC = dc;
        BaseGui.lang = DC.getLanguageManager();
        
        this.width = 800;
        this.height = 600;
    }
  
    /**
     * Start de appliactie
     * 
     * @param stage
     * @throws IOException 
     */
    public void start(Stage stage) throws IOException
    {
        BaseGui.stage = stage;
        
        this.loadScene("Sokoban.fxml");
        
        (new MainPaneel()).run();
    }   
    
}
