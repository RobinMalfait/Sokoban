package gui.javaFx;

import exceptions.GebruikersnaamException;
import exceptions.WachtwoordException;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import languages.LanguageManager;

/**
 *
 * @author robin
 */
public class RegistreerPaneel extends BaseGui
{ 
    public void run()
    {
        this.init();
        
        this.reset();
    }
    
    private void init()
    {
        LanguageManager lang = DC.getLanguageManager();
        
        stage.setTitle(lang.get("sign.up"));
        
        this.show("#RegistreerPaneel");
        
        this.findByIdInPane("back").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                (new MenuKeuzePaneel()).run();
            }
        });
        
        Label lblFirstName = (Label) this.findByIdInPane("lblFirstName");
        lblFirstName.setText(lang.get("user.firstname") + ":");
        
        Label lblLastName = (Label) this.findByIdInPane("lblLastName");
        lblLastName.setText(lang.get("user.name") + ":");
        
        Label lblUsername = (Label) this.findByIdInPane("lblUsername");
        lblUsername.setText(lang.get("user.username") + ":");
        
        Label lblPassword = (Label) this.findByIdInPane("lblPassword");
        lblPassword.setText(lang.get("user.password") + ":");     
        
        Label lblPasswordRepeat = (Label) this.findByIdInPane("lblPasswordRepeat");
        lblPasswordRepeat.setText(lang.get("user.password.repeat") + ":");
        
        TextField firstName = (TextField) this.findByIdInPane("txtFirstName");
        TextField lastName = (TextField) this.findByIdInPane("txtLastName");
        TextField username = (TextField) this.findByIdInPane("txtUsername");
        TextField password = (TextField) this.findByIdInPane("txtPassword");
        TextField passwordRepeat = (TextField) this.findByIdInPane("txtPasswordRepeat");
        
        Button signUp = (Button) this.findByIdInPane("signUpBtn");
        signUp.setText(lang.get("sign.up").toUpperCase());
        
        firstName.requestFocus(); // focus first name
        
        firstName.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.ENTER) {
                    lastName.requestFocus();
                }
            }
        });
        
        lastName.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.ENTER) {
                    username.requestFocus();
                }
            }
        });
        
        username.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.ENTER) {
                    password.requestFocus();
                }
            }
        });
        
        password.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.ENTER) {
                    passwordRepeat.requestFocus();
                }
            }
        });
        
        passwordRepeat.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode() == KeyCode.ENTER) {
                    registreer(firstName, lastName, username, password, passwordRepeat);
                }
            }
        });
        
        
        signUp.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                registreer(firstName, lastName, username, password, passwordRepeat);
                
            }
        });
    }
    
    private void registreer(TextField firstName, TextField lastName, TextField username, TextField password, TextField passwordRepeat)
    {
        try {
            String sLastName = lastName.getText() == null ? "" : lastName.getText();
            String sFirstName = firstName.getText() == null ? "" : firstName.getText();
            String sUsername = username.getText() == null ? "" : username.getText();
            String sPassword = password.getText() == null ? "" : password.getText();
            String sPasswordRepeat = passwordRepeat.getText() == null ? "" : passwordRepeat.getText();

            DC.registreer(sLastName, sFirstName, sUsername, sPassword, sPasswordRepeat);

            (new SubMenuPaneel()).run();

        } catch (GebruikersnaamException | WachtwoordException e) {
            Label lblError = (Label) RegistreerPaneel.this.findByIdInPane("lblError");
            lblError.setText(e.getMessage());
            lblError.setVisible(true);

            firstName.requestFocus();
        }
    }

    protected void reset()
    {
        TextField firstName = (TextField) this.findByIdInPane("txtFirstName");
        TextField lastName = (TextField) this.findByIdInPane("txtLastName");
        TextField username = (TextField) this.findByIdInPane("txtUsername");
        TextField password = (TextField) this.findByIdInPane("txtPassword");
        TextField passwordRepeat = (TextField) this.findByIdInPane("txtPasswordRepeat");
        
        firstName.setText("");
        lastName.setText("");
        username.setText("");
        password.setText("");
        passwordRepeat.setText("");
        
        Label lblError = (Label) RegistreerPaneel.this.findByIdInPane("lblError");
        lblError.setText("");
        lblError.setVisible(false);
    }
}
