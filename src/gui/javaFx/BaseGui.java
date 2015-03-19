package gui.javaFx;

import domein.DomeinController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author robin
 */
public abstract class BaseGui
{
    protected int width = 800;
    protected int height = 600;
    
    protected static DomeinController DC;
    
    protected void show(Stage stage, String paneId)
    {
        Pane pane = (Pane) stage.getScene().lookup(paneId);

        pane.setVisible(true);
        pane.toFront();
    }

    protected void loadScene(Stage stage, String fxmlFile) throws IOException
    {
        FXMLLoader root = new FXMLLoader(getClass().getResource(fxmlFile));
        Scene scene = new Scene(root.load(), this.width, this.height);

        stage.setScene(scene);
        stage.show();
    }
    
    protected Node findByIdInPane(Stage stage, String idString)
    {
        return stage.getScene().lookup("#" + getClass().getSimpleName() + "_" + idString);
    }
}
