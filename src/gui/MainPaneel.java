package gui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import languages.LanguageManager;

/**
 *
 * @author robin
 */
public class MainPaneel extends BaseGui
{

    public MainPaneel(Stage stage, LanguageManager languageManager)
    {
        stage.setTitle("Welcome!");
        
        this.show(stage, "#MainPaneel");
        
        this.findByIdInPane(stage, "playButton").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                KiesTaalPaneel kiesTaalPaneel = new KiesTaalPaneel(stage, languageManager);
            }
        });
    }

}
