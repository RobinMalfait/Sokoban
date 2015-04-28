package gui.javaFx;

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

    public StartUpGui(DomeinController dc)
    {
        BaseGui.DC = dc;
        
        this.width = 800;
        this.height = 600;
    }
  
    public void start(Stage stage) throws IOException
    {
        this.loadScene(stage, "Sokoban.fxml");
        
        MainPaneel speelSpelPaneel = this.showIndexPage(stage);
    }

    private MainPaneel showIndexPage(Stage stage)
    {
        return new MainPaneel(stage);
    }

    @Override
    protected void reset(Stage stage)
    {
        
    }
    
    
}
