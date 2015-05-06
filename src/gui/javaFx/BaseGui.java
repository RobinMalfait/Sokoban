package gui.javaFx;

import domein.DomeinController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import languages.LanguageManager;

/**
 *
 * @author robin
 */
public abstract class BaseGui
{
    protected int width = 800;
    protected int height = 600;
    
    protected static DomeinController DC;
    protected static Stage stage;
    protected static LanguageManager lang;
        
    /**
     * Toon een paneel op basis van zijn id
     * 
     * @param id 
     */
    protected void show(String id)
    {
        Pane pane = (Pane) stage.getScene().lookup(id);

        pane.setVisible(true);
        pane.toFront();
    }

    /**
     * Laad een fxml file.
     * 
     * @param fxmlFile
     * @throws IOException 
     */
    protected void loadScene(String fxmlFile) throws IOException
    {
        FXMLLoader root = new FXMLLoader(getClass().getResource(fxmlFile));
        Scene scene = new Scene(root.load(), this.width, this.height);

        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Zoek een node op het paneel op basis van zijn id
     * 
     * @param id
     * @return 
     */
    protected Node findByIdInPane(String id)
    {
        return stage.getScene().lookup("#" + getClass().getSimpleName() + "_" + id);
    }
}
