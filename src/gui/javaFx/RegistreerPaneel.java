package gui.javaFx;

import exceptions.GebruikersnaamException;
import exceptions.WachtwoordException;
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
public class RegistreerPaneel extends BaseGui
{ 
    public RegistreerPaneel(Stage stage)
    {
        this.init(stage);
    }
    
    private void init(Stage stage)
    {
        LanguageManager lang = DC.getLanguageManager();
        
        stage.setTitle(lang.get("sign.up"));
        
        this.show(stage, "#RegistreerPaneel");
        
        this.findByIdInPane(stage, "back").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                MenuKeuzePaneel menuKeuzePaneel = new MenuKeuzePaneel(stage);
            }
        });
        
        Label lblFirstName = (Label) this.findByIdInPane(stage, "lblFirstName");
        lblFirstName.setText(lang.get("user.firstname") + ":");
        
        Label lblLastName = (Label) this.findByIdInPane(stage, "lblLastName");
        lblLastName.setText(lang.get("user.name") + ":");
        
        Label lblUsername = (Label) this.findByIdInPane(stage, "lblUsername");
        lblUsername.setText(lang.get("user.username") + ":");
        
        Label lblPassword = (Label) this.findByIdInPane(stage, "lblPassword");
        lblPassword.setText(lang.get("user.password") + ":");     
        
        Label lblPasswordRepeat = (Label) this.findByIdInPane(stage, "lblPasswordRepeat");
        lblPasswordRepeat.setText(lang.get("user.password.repeat") + ":");
        
        TextField firstName = (TextField) this.findByIdInPane(stage, "txtFirstName");
        TextField lastName = (TextField) this.findByIdInPane(stage, "txtLastName");
        TextField username = (TextField) this.findByIdInPane(stage, "txtUsername");
        TextField password = (TextField) this.findByIdInPane(stage, "txtPassword");
        TextField passwordRepeat = (TextField) this.findByIdInPane(stage, "txtPasswordRepeat");
        
        Button signUp = (Button) this.findByIdInPane(stage, "signUpBtn");
        signUp.setText(lang.get("sign.up").toUpperCase());
        
        firstName.requestFocus(); // focus first name
        
        signUp.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                try {
                    String sLastName = lastName.getText() == null ? "" : lastName.getText();
                    String sFirstName = firstName.getText() == null ? "" : firstName.getText();
                    String sUsername = username.getText() == null ? "" : username.getText();
                    String sPassword = password.getText() == null ? "" : password.getText();
                    String sPasswordRepeat = passwordRepeat.getText() == null ? "" : passwordRepeat.getText();
                            
                    DC.registreer(sLastName, sFirstName, sUsername, sPassword, sPasswordRepeat);
                    
                    KiesSpelPaneel kiesSpelPaneel = new KiesSpelPaneel(stage);
                    
                } catch (GebruikersnaamException | WachtwoordException e) {
                    Label lblError = (Label) RegistreerPaneel.this.findByIdInPane(stage, "lblError");
                    lblError.setText(e.getMessage());
                    lblError.setVisible(true);
                    
                    firstName.requestFocus();
                }
                
            }
        });
    }
}
