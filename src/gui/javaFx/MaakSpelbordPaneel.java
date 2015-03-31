package gui.javaFx;

import static gui.javaFx.BaseGui.DC;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import languages.LanguageManager;

/**
 *
 * @author robin
 */
public class MaakSpelbordPaneel extends BaseGui
{

    private LanguageManager lang;

    private final GridPane board;

    public MaakSpelbordPaneel(Stage stage)
    {
        this.init(stage);
        
        this.board = (GridPane) this.findByIdInPane(stage, "grid");
    }

    private void init(Stage stage)
    {
        this.lang = DC.getLanguageManager();

        stage.setTitle("");

        this.show(stage, "#MaakSpelbordPaneel");

        this.findByIdInPane(stage, "back").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                KiesSpelPaneel kiesSpelPaneel = new KiesSpelPaneel(stage);
            }
        });
    }

}
