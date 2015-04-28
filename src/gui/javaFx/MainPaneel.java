package gui.javaFx;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author robin
 */
public class MainPaneel extends BaseGui
{

    public MainPaneel(Stage stage)
    {
        stage.setTitle("Welcome!");
        
        this.show(stage, "#MainPaneel");
        
        this.findByIdInPane(stage, "playButton").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                KiesTaalPaneel kiesTaalPaneel = new KiesTaalPaneel(stage);
            }
        });
    }

    @Override
    protected void reset(Stage stage)
    {
        
    }

}
