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
public class MenuKeuzePaneel extends BaseGui
{
    
    private LanguageManager lang;
    
    public MenuKeuzePaneel(Stage stage)
    {
        this.init(stage);
                
        Button signIn = (Button) this.findByIdInPane(stage, "signIn");
        signIn.setText(this.lang.get("sign.in").toUpperCase());
        
        Button signUp = (Button) this.findByIdInPane(stage, "signUp");
        signUp.setText(this.lang.get("sign.up").toUpperCase());
        
        Button stop = (Button) this.findByIdInPane(stage, "stop");
        stop.setText(this.lang.get("app.quit").toUpperCase());
        
        signIn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                MeldAanPaneel meldAanPaneel = new MeldAanPaneel(stage);
            }
        });
        
        signUp.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                RegistreerPaneel registreerPaneel = new RegistreerPaneel(stage);
            }
        });
    }
    
    private void init(Stage stage)
    {
        this.lang = DC.getLanguageManager();
        
        stage.setTitle("Sokoban!");
        
        this.show(stage, "#MenuKeuzePaneel");
        
        this.findByIdInPane(stage, "back").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                KiesTaalPaneel kiesTaalPaneel = new KiesTaalPaneel(stage);
            }
        });
    }
}
