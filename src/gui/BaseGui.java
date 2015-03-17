package gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author robin
 */
public abstract class BaseGui
{
    protected int width;
    protected int height;

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
}
