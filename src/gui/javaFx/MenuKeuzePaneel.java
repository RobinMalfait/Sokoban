package gui.javaFx;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author robin
 */
public class MenuKeuzePaneel extends BaseGui
{    
    public void run()
    {
        this.init();
                
        Button signIn = (Button) this.findByIdInPane("signIn");
        signIn.setText(lang.get("sign.in").toUpperCase());
        
        Button signUp = (Button) this.findByIdInPane("signUp");
        signUp.setText(lang.get("sign.up").toUpperCase());
        
        signIn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                (new MeldAanPaneel()).run();
            }
        });
        
        signUp.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                (new RegistreerPaneel()).run();
            }
        });
    }
    
    private void init()
    {
        stage.setTitle("Sokoban!");
        
        this.show("#MenuKeuzePaneel");
        
        this.findByIdInPane("back").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                (new KiesTaalPaneel()).run();
            }
        });
    }
}
