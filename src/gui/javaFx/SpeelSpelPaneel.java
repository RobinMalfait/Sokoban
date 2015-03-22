package gui.javaFx;

import static gui.javaFx.BaseGui.DC;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
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
public class SpeelSpelPaneel extends BaseGui
{

    private final LanguageManager lang;
    
    public SpeelSpelPaneel(Stage stage, LanguageManager languageManager)
    {
        this.lang = languageManager;
        stage.setTitle("");

        this.show(stage, "#SpeelSpelPaneel");
        
        stage.getScene().setOnKeyPressed((KeyEvent event) ->
        {
            if ( ! DC.isEindeSpel()) {
                if (event.getCode().equals(KeyCode.UP)) {
                    DC.verplaatsSpeler(1);
                    SpeelSpelPaneel.this.drawBoard(stage);
                    
                } else if (event.getCode().equals(KeyCode.DOWN)) {
                    DC.verplaatsSpeler(2);
                    SpeelSpelPaneel.this.drawBoard(stage);
                    
                } else if (event.getCode().equals(KeyCode.LEFT)) {
                    DC.verplaatsSpeler(3);
                    SpeelSpelPaneel.this.drawBoard(stage);
                    
                } else if (event.getCode().equals(KeyCode.RIGHT)) {
                    DC.verplaatsSpeler(4);
                    SpeelSpelPaneel.this.drawBoard(stage);
                }
            }
        });
                
        this.findByIdInPane(stage, "up").setOnMouseClicked((MouseEvent event) ->
        {
            if ( ! DC.isEindeSpel()) {
                DC.verplaatsSpeler(1);
                SpeelSpelPaneel.this.drawBoard(stage);
            }
        });
        
        this.findByIdInPane(stage, "down").setOnMouseClicked((MouseEvent event) ->
        {
            if ( ! DC.isEindeSpel()) {
                DC.verplaatsSpeler(2);
                SpeelSpelPaneel.this.drawBoard(stage);
            }
        });
        
        this.findByIdInPane(stage, "left").setOnMouseClicked((MouseEvent event) ->
        {
            if ( ! DC.isEindeSpel()) {
                DC.verplaatsSpeler(3);
                SpeelSpelPaneel.this.drawBoard(stage);
            }
        });
        
        this.findByIdInPane(stage, "right").setOnMouseClicked((MouseEvent event) ->
        {
            if ( ! DC.isEindeSpel()) {
                DC.verplaatsSpeler(4);
                SpeelSpelPaneel.this.drawBoard(stage);
            }
        });

        this.drawBoard(stage);
    }

    private void drawBoard(Stage stage)
    {
        GridPane grid = (GridPane) this.findByIdInPane(stage, "grid");
        Label spelbordComplete = (Label) this.findByIdInPane(stage, "lblSpelbordComplete");
        
        spelbordComplete.setText("");
                
        int x = 0;
        int y = 0;

        for (String[] row : DC.toonSpelbord())
        {
            for (String type : row)
            {
                Pane item = new Pane();
                item.getStyleClass().add(type);
                item.setPrefSize(50, 50);

                grid.add(item, x, y);

                x++;
            }

            x = 0;
            y++;
        }
            
        if (DC.isEindeHuidigSpelbord()) {
            if (DC.isEindeSpel()) {
                
                grid.getChildren().clear();
                
                grid.getStyleClass().add("win");
            } else {
                spelbordComplete.setText(this.lang.get("game.complete"));
            
                DC.bepaalVolgendSpelbord();

                this.drawBoard(stage);
            }
        }
    }

}
