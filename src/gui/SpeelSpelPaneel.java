package gui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import languages.LanguageManager;

/**
 *
 * @author robin
 */
public class SpeelSpelPaneel extends BaseGui
{

    public SpeelSpelPaneel(Stage stage, LanguageManager languageManager)
    {
        stage.setTitle("Welcome!");
        
        this.show(stage, "#SpeelSpelPaneel");
        
        stage.getScene().lookup("#PlayButton").setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event)
            {
                new KiesTaalPaneel(stage, languageManager);
            }
            
        });
    }

}
