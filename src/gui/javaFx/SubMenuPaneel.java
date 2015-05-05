package gui.javaFx;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author robin
 */
public class SubMenuPaneel extends BaseGui
{    
    public void run()
    {
        this.init();
    }
    
    private void init()
    {        
        stage.setTitle("Sokoban!");
        
        this.show( "#SubMenuPaneel");
        
        this.findByIdInPane( "back").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                (new MenuKeuzePaneel()).run();
            }
        });
        
        Button playGame = (Button) this.findByIdInPane("playGame");      
        playGame.setText(lang.get("game.play").toUpperCase());
        
        Button createGame = (Button) this.findByIdInPane("createGame");
        createGame.setText(lang.get("game.create").toUpperCase());
        
        Button modifyGame = (Button) this.findByIdInPane("modifyGame");
        modifyGame.setText(lang.get("game.modify").toUpperCase());
        
        if (DC.isAdmin()) {
            createGame.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event)
                {
                    (new MaakSpelbordPaneel()).run();
                }
            });
            
            modifyGame.setVisible(true);
            createGame.setVisible(true);
            
        } else {
            modifyGame.setVisible(false);
            createGame.setVisible(false);
        }
  
        playGame.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event)
            {
                (new KiesSpelPaneel()).run();
            }
        });
    }
}
