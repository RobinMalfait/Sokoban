package gui.javaFx;

import exceptions.WachtwoordException;
import static gui.javaFx.BaseGui.DC;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author robin
 */
public class MeldAanPaneel extends BaseGui
{
    /**
     * Run de MeldAanPaneel
     */
    public void run()
    {
        this.init();
        this.reset();
    }
    
    /**
     * Initialiseer het paneel
     */
    private void init()
    {        
        stage.setTitle(lang.get("sign.in"));
        
        this.show("#MeldAanPaneel");
        
        this.findByIdInPane("back").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                (new MenuKeuzePaneel()).run();
            }
        });
        
        Label lblUsername = (Label) this.findByIdInPane("lblUsername");
        lblUsername.setText(lang.get("user.username") + ":");
        
        Label lblPassword = (Label) this.findByIdInPane("lblPassword");
        lblPassword.setText(lang.get("user.password") + ":");
        
        TextField username = (TextField) this.findByIdInPane("txtUsername");
        TextField password = (TextField) this.findByIdInPane("txtPassword");
        
        Button signIn = (Button) this.findByIdInPane("signIn");    
        signIn.setText(lang.get("sign.in").toUpperCase());
        
        username.requestFocus(); // focus username
        
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
                    login(username, password);
                }
            }
        });
        
        signIn.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                login(username, password);
                
            }
        });
    }
    
    /**
     * Log een gebruiker in op basis van zijn username en password
     * 
     * @param username
     * @param password 
     */
    private void login(TextField username, TextField password)
    {
        try {
            DC.meldAan(username.getText(), password.getText());

            (new SubMenuPaneel()).run();

        } catch (WachtwoordException e) {
            Label lblError = (Label) MeldAanPaneel.this.findByIdInPane("lblError");
            lblError.setText(e.getMessage());
            lblError.setVisible(true);

            username.requestFocus();
        }
    }

    /**
     * Reset het paneel
     */
    protected void reset()
    {
        TextField username = (TextField) this.findByIdInPane("txtUsername");
        username.setText("");
        TextField password = (TextField) this.findByIdInPane("txtPassword");
        password.setText("");
        
        Label lblError = (Label) MeldAanPaneel.this.findByIdInPane("lblError");
        lblError.setText("");
        lblError.setVisible(false);
    }
}
