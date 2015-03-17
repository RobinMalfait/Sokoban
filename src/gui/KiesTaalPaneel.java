package gui;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import languages.LanguageManager;

/**
 *
 * @author robin
 */
public class KiesTaalPaneel extends BaseGui
{
    private final LanguageManager languageManager;
    private final Stage stage;
    
    public KiesTaalPaneel(Stage stage, LanguageManager languageManager)
    {
        this.languageManager = languageManager;
        this.stage = stage;
        
        stage.setTitle("Kies uw taal!");
        
        this.show(stage, "#KiesTaalPaneel");
        
        stage.getScene().lookup("#langNL").setOnMouseClicked((MouseEvent event) ->
        {
            this.gaVerder("NL");
        });
        
        stage.getScene().lookup("#langEN").setOnMouseClicked((MouseEvent event) ->
        {
            this.gaVerder("EN");
        });
        
        stage.getScene().lookup("#langFR").setOnMouseClicked((MouseEvent event) ->
        {
            this.gaVerder("FR");
        });
    }
    
    public void gaVerder(String language)
    {
        this.languageManager.setLanguage(language);
        
        new MenuKeuzePaneel(this.stage, this.languageManager);
    }
}
