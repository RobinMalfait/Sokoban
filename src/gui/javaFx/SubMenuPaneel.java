package gui.javaFx;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import languages.LanguageManager;

/**
 *
 * @author robin
 */
public class SubMenuPaneel extends BaseGui
{
    
    private LanguageManager lang;
    
    public SubMenuPaneel(Stage stage)
    {
        this.init(stage);
        
        Button playGame = (Button) this.findByIdInPane(stage, "playGame");      
        playGame.setText(lang.get("game.play").toUpperCase());
        
        Button createGame = (Button) this.findByIdInPane(stage, "createGame");
        createGame.setText(lang.get("game.conf").toUpperCase());
        
        Button modifyGame = (Button) this.findByIdInPane(stage, "modifyGame");
        modifyGame.setText(lang.get("game.modify"));
        
        if (DC.isAdmin()) {
            createGame.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event)
                {
                    MaakSpelbordPaneel maakSpelbordPaneel = new MaakSpelbordPaneel(stage);
                }
            });
        } else {
            modifyGame.setVisible(false);
            createGame.setVisible(false);
        }
  
        playGame.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event)
            {
                KiesSpelPaneel kiesSpelPaneel = new KiesSpelPaneel(stage);
            }
        });
        
    }
    
    private void init(Stage stage)
    {
        this.lang = DC.getLanguageManager();
        
        stage.setTitle("Sokoban!");
        
        this.show(stage, "#SubMenuPaneel");
        
        this.findByIdInPane(stage, "back").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                MenuKeuzePaneel menuKeuzePaneel = new MenuKeuzePaneel(stage);
            }
        });
    }
}
