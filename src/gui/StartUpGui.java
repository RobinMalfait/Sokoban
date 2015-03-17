package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.stage.Stage;
import languages.LanguageManager;


/**
 *
 * @author robin
 */
public class StartUpGui extends BaseGui
{
    private final DomeinController domeinController;
    
    private final LanguageManager languageManager;

    public StartUpGui(DomeinController dc, LanguageManager lm)
    {
        this.domeinController = dc;
        this.languageManager = lm;
        
        this.width = 800;
        this.height = 600;
    }
  
    public void start(Stage stage) throws IOException
    {
        this.loadScene(stage, "Sokoban.fxml");
        
        SpeelSpelPaneel speelSpelPaneel = this.showIndexPage(stage);
    }

    private SpeelSpelPaneel showIndexPage(Stage stage)
    {
        return new SpeelSpelPaneel(stage, this.languageManager);
    }
    
    
}
