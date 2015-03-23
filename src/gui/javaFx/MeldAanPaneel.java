package gui.javaFx;

import exceptions.WachtwoordException;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import languages.LanguageManager;

/**
 *
 * @author robin
 */
public class MeldAanPaneel extends BaseGui
{
    public MeldAanPaneel(Stage stage)
    {
        
        LanguageManager lang = DC.getLanguageManager();
        
        stage.setTitle(lang.get("sign.in"));
        
        this.show(stage, "#MeldAanPaneel");
        
        Label lblUsername = (Label) this.findByIdInPane(stage, "lblUsername");
        lblUsername.setText(lang.get("user.username") + ":");
        
        Label lblPassword = (Label) this.findByIdInPane(stage, "lblPassword");
        lblPassword.setText(lang.get("user.password") + ":");
        
        TextField username = (TextField) this.findByIdInPane(stage, "txtUsername");
        TextField password = (TextField) this.findByIdInPane(stage, "txtPassword");
        
        Button signIn = (Button) this.findByIdInPane(stage, "signIn");    
        signIn.setText(lang.get("sign.in"));
        
        username.requestFocus(); // focus username
        
        signIn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                try {
                    DC.meldAan(username.getText(), password.getText());
                    
                    KiesSpelPaneel kiesSpelPaneel = new KiesSpelPaneel(stage);
                    
                } catch (WachtwoordException e) {
                    Label lblError = (Label) MeldAanPaneel.this.findByIdInPane(stage, "lblError");
                    lblError.setText(e.getMessage());
                    lblError.setVisible(true);
                    
                    username.requestFocus();
                }
                
            }
        });
        
        this.findByIdInPane(stage, "stop").setOnMouseClicked(new EventHandler<MouseEvent>()
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
