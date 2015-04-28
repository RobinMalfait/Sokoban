package gui.javaFx;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import languages.LanguageManager;

/**
 *
 * @author robin
 */
public class MenuKeuzePaneel extends BaseGui
{
    private LanguageManager lang;
    
    public void run()
    {
        this.init();
                
        Button signIn = (Button) this.findByIdInPane("signIn");
        signIn.setText(this.lang.get("sign.in").toUpperCase());
        
        Button signUp = (Button) this.findByIdInPane("signUp");
        signUp.setText(this.lang.get("sign.up").toUpperCase());
        
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
        this.lang = DC.getLanguageManager();
        
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
