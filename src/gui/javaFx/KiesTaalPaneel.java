package gui.javaFx;

import javafx.event.EventHandler;
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
        
        this.findByIdInPane(stage, "langNL").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                KiesTaalPaneel.this.gaVerder("NL");
            }
        });
        
        this.findByIdInPane(stage, "langEN").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                KiesTaalPaneel.this.gaVerder("EN");
            }
        });
        
        this.findByIdInPane(stage, "langFR").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                KiesTaalPaneel.this.gaVerder("FR");
            }
        });
    }
    
    public void gaVerder(String language)
    {
        this.languageManager.setLanguage(language);
        
        MenuKeuzePaneel menuKeuzePaneel = new MenuKeuzePaneel(this.stage, this.languageManager);
    }
}
