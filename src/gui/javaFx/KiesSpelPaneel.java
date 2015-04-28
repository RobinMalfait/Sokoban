package gui.javaFx;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author robin
 */
public class KiesSpelPaneel extends BaseGui
{    
    public void run()
    {
        this.init();
        
        this.drawSpelBoard();
    }
    
    private void init()
    {        
        String speler[] = DC.geefHuidigeSpeler();
        
        String name = speler[0] + " " + speler[1];
        
        stage.setTitle(lang.get("game.welcome").toUpperCase() + " " + name + "!");
        
        this.show("#KiesSpelPaneel");
        
        this.findByIdInPane("back").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                (new SubMenuPaneel()).run();
            }
        });
    }
    
    private void drawSpelBoard()
    {
        ((Label) this.findByIdInPane("kiesSpel")).setText(lang.get("game.choose.list") + ":");
        
        GridPane grid = (GridPane) this.findByIdInPane("grid");
                
        String spellen[][] = DC.geefLijstSpellen();
        
        int x = 0;
        int y = 0;
        
        grid.setAlignment(Pos.CENTER);
        
        for (String spel[] : spellen) 
        {
            Button spelButton = new Button();
            spelButton.setText(spel[1]);
            spelButton.getStyleClass().add("btn");
            spelButton.getStyleClass().add("btn-space");
            spelButton.setAlignment(Pos.CENTER);
            
            spelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event)
                {
                    DC.kiesSpel(Integer.parseInt(spel[0]));
                    
                    (new SpeelSpelPaneel()).run();
                }
            });
           
            GridPane.setHalignment(spelButton, HPos.CENTER);
            
            grid.add(spelButton, x, y);
            
            x++;
            
            if (x > 3) // Aantal kolommen - 1
            {
                x = 0;
                y++;
            }
        }
    }
}
