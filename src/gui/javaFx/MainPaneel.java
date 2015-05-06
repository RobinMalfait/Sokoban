package gui.javaFx;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author robin
 */
public class MainPaneel extends BaseGui
{
    /**
     * Run de MainPaneel
     */
    public void run()
    {
        stage.setTitle("Welcome!");
        
        this.show("#MainPaneel");
        
        this.findByIdInPane("playButton").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                (new KiesTaalPaneel()).run();
            }
        });
    }

}
