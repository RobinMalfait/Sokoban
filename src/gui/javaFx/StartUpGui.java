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

    public StartUpGui(DomeinController dc)
    {
        BaseGui.DC = dc;
        
        this.width = 800;
        this.height = 600;
    }
  
    public void start(Stage stage) throws IOException
    {
        this.loadScene(stage, "Sokoban.fxml");
        
        BaseGui.stage = stage;
        BaseGui.lang = DC.getLanguageManager();
        
        (new MainPaneel()).run();
    }   
    
}
