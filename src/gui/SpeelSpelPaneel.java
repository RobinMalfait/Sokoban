package gui;

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
        
        stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                if (event.getCode().equals(KeyCode.UP)) {
                    BaseGui.DC.verplaatsSpeler(1);
                    SpeelSpelPaneel.this.drawBoard(stage);
                    
                } else if (event.getCode().equals(KeyCode.DOWN)) {
                    BaseGui.DC.verplaatsSpeler(2);
                    SpeelSpelPaneel.this.drawBoard(stage);
                    
                } else if (event.getCode().equals(KeyCode.LEFT)) {
                    BaseGui.DC.verplaatsSpeler(3);
                    SpeelSpelPaneel.this.drawBoard(stage);
                    
                } else if (event.getCode().equals(KeyCode.RIGHT)) {
                    BaseGui.DC.verplaatsSpeler(4);
                    SpeelSpelPaneel.this.drawBoard(stage);
                }
            }
            
        });
                
        this.findByIdInPane(stage, "up").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                DC.verplaatsSpeler(1);
                SpeelSpelPaneel.this.drawBoard(stage);
            }
        });
        
        this.findByIdInPane(stage, "down").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                DC.verplaatsSpeler(2);
                SpeelSpelPaneel.this.drawBoard(stage);
            }
        });
        
        this.findByIdInPane(stage, "left").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                DC.verplaatsSpeler(3);
                SpeelSpelPaneel.this.drawBoard(stage);
            }
        });
        
        this.findByIdInPane(stage, "right").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
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
        
        if (DC.isSpelbordVoltooid()) {
            spelbordComplete.setText(this.lang.get("game.complete"));
            
            DC.bepaalVolgendSpelbord();
            
            this.drawBoard(stage);
        }
        
        if (DC.isEindeSpel()) {
            
        }
    }

}
