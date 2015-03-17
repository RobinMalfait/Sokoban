package gui;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import languages.LanguageManager;

/**
 *
 * @author robin
 */
public class MenuKeuzePaneel extends BaseGui
{
    public MenuKeuzePaneel(Stage stage, LanguageManager languageManager)
    {
        stage.setTitle("Sokoban!");
        
        this.show(stage, "#MenuKeuzePaneel");
        
        Button signIn = (Button) stage.getScene().lookup("#signIn");
        signIn.setText(languageManager.get("sign.in").toUpperCase());
        
        signIn.setOnMouseClicked((MouseEvent event) ->
        {
            new MeldAanPaneel(stage, languageManager);
        });
        
        Button signUp = (Button) stage.getScene().lookup("#signUp");
        signUp.setText(languageManager.get("sign.up").toUpperCase());
        
        Button stop = (Button) stage.getScene().lookup("#stop");
        stop.setText(languageManager.get("stop").toUpperCase());
        
    }
}
