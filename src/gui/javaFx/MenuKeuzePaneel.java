package gui.javaFx;

import javafx.application.Platform;
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
    public MenuKeuzePaneel(Stage stage, LanguageManager languageManager)
    {
        stage.setTitle("Sokoban!");
        
        this.show(stage, "#MenuKeuzePaneel");
        
        Button signIn = (Button) this.findByIdInPane(stage, "signIn");
        signIn.setText(languageManager.get("sign.in").toUpperCase());
        
        Button signUp = (Button) this.findByIdInPane(stage, "signUp");
        signUp.setText(languageManager.get("sign.up").toUpperCase());
        
        Button stop = (Button) this.findByIdInPane(stage, "stop");
        stop.setText(languageManager.get("app.quit").toUpperCase());
        
        signIn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                MeldAanPaneel meldAanPaneel = new MeldAanPaneel(stage, languageManager);
            }
        });
        
        stop.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Platform.exit();
                System.exit(0);
            }
        });
        
    }
}
