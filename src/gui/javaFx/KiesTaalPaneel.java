package gui.javaFx;

import static gui.javaFx.BaseGui.DC;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import languages.LanguageManager;

/**
 *
 * @author robin
 */
public class KiesTaalPaneel extends BaseGui
{
    private Stage stage;
    
    public KiesTaalPaneel(Stage stage)
    {
        this.init(stage);
        
        this.drawLanguagesBoard(stage);        
    }
    
    private void gaVerder(String language)
    {
        DC.setLanguage(language);
        
        MenuKeuzePaneel menuKeuzePaneel = new MenuKeuzePaneel(this.stage);
    }
    
    private void init(Stage stage)
    {
        this.stage = stage;
        
        stage.setTitle("Kies uw taal!");
        
        this.show(stage, "#KiesTaalPaneel");
        
        this.findByIdInPane(stage, "back").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                MainPaneel mainPaneel = new MainPaneel(stage);
            }
        });
    }
    
    private void drawLanguagesBoard(Stage stage)
    {
        GridPane grid = (GridPane) this.findByIdInPane(stage, "grid");
                
        LanguageManager lang = DC.getLanguageManager();
        
        int x = 0;
        int y = 0;
        
        grid.setAlignment(Pos.CENTER);
        
        for (String language : lang.getLanguages()) 
        {
            Button spelButton = new Button();
            spelButton.setText(language);
            spelButton.getStyleClass().add("btn");
            spelButton.getStyleClass().add("btn-space");
            spelButton.getStyleClass().add("btn-block");
            spelButton.setAlignment(Pos.CENTER);
            
            spelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event)
                {
                    KiesTaalPaneel.this.gaVerder(language);
                }
            });
           
            GridPane.setHalignment(spelButton, HPos.CENTER);
            
            grid.add(spelButton, x, y);
            
            x++;
            
            if (x > 2) // Aantal kolommen - 1
            {
                x = 0;
                y++;
            }
        }
    }
}
