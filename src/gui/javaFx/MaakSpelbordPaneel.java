package gui.javaFx;

import static gui.javaFx.BaseGui.DC;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        
    private String activeField = "M"; // Muur standaard
    
    private String[][] items; // Keep state
    
    private Label errorLabel;

    public MaakSpelbordPaneel(Stage stage)
    {
        this.board = (GridPane) this.findByIdInPane(stage, "grid");
        this.items = new String[10][10];
        
        initializeBoard(); 
        
        DC.voegSpelToe("Test Game Robin");
        
        this.init(stage);
    }

    private void initializeBoard()
    {
        for (int x = 0; x < items.length; x++)
        {
            for (int y = 0; y < items[x].length; y++)
            {
                items[x][y] = "_";
            }
        }
    }

    private void init(Stage stage)
    {
        this.lang = DC.getLanguageManager();

        stage.setTitle("");

        this.show(stage, "#MaakSpelbordPaneel");
                
        this.errorLabel = (Label) this.findByIdInPane(stage, "error");
        ((Label) this.findByIdInPane(stage, "gameboard_name_label")).setText(this.lang.get("game.board.name"));

        this.findByIdInPane(stage, "back").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                SubMenuPaneel subMenuPaneel = new SubMenuPaneel(stage);
            }
        });
        
        this.registerSidebarClickEvents(stage);
        
        this.drawBoard();
        
        this.board.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                updateField((int) event.getSceneX(), (int) event.getSceneY());
            }
        });
        
        this.findByIdInPane(stage, "save").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent event)
            {
                TextField gameboardName = (TextField) MaakSpelbordPaneel.this.findByIdInPane(stage, "gameboard_name");
                String name = gameboardName.getText();
                
                if (name == null || name.equals("")) {
                    setError(lang.get("err.noGameboadName"));
                } else {
                    cleanError();
                    
                    saveGameboard(name);
                }
            }
        });
    }

    private void registerSidebarClickEvents(Stage stage)
    {
        /* Leeg veld */
        this.findByIdInPane(stage, "_").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                setActiveField(stage, "_");
            }
        });
        
        /* Muur */
        this.findByIdInPane(stage, "M").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                setActiveField(stage, "M");
            }
        });
        
        /* Doel */
        this.findByIdInPane(stage, "D").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                setActiveField(stage, "D");
            }
        });
        
        /* Kist */
        this.findByIdInPane(stage, "K").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                setActiveField(stage, "K");
            }
        });
        
        /* Mannetje */
        this.findByIdInPane(stage, "Y").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                setActiveField(stage, "Y");
            }
        });
    }
    
    private void setActiveField(Stage stage, String field)
    {
        this.findByIdInPane(stage, "active").getStyleClass().remove(this.activeField);
               
        this.activeField = field;
                
        this.findByIdInPane(stage, "active").getStyleClass().add(this.activeField);
    }
    
    private void drawBoard()
    {
        int x = 0;
        int y = 0;

        for (String[] row : this.items)
        {
            for (String type : row)
            {
                Pane item = new Pane();
                item.getStyleClass().add(type);
                item.setPrefSize(50, 50);

                this.board.add(item, x, y);

                x++;
            }

            x = 0;
            y++;
        }
    }

    private void updateField(Integer x, Integer y)
    {
        int xOffset = 14;
        int yOffset = 56;
        
        x -= xOffset;
        y -= yOffset;
        
        x = (x - x % 50) / 50;
        y = (y - y % 50) / 50;
        
        this.items[y][x] = this.activeField;
       
        this.drawBoard();
    }

    private void setError(String text)
    {
        this.errorLabel.setText(text);
    }

    private void cleanError()
    {
        this.setError(""); // Clear the text
    }

    private void saveGameboard(String name)
    {
        DC.voegSpelbordToe(name);
        
        for(int row = 0; row < this.items.length; row++) {
            for (int cell = 0; cell < this.items[row].length; cell++) {
                DC.voerVakIn(String.format("%d,%d", row, cell), this.items[row][cell]);
            }
        }
        
        DC.slaHuidigSpelOp();
    }

    @Override
    protected void reset(Stage stage)
    {
        DC.resetSpelbord();
        this.cleanError();
        TextField gameboardName = (TextField) MaakSpelbordPaneel.this.findByIdInPane(stage, "gameboard_name");
        gameboardName.setText("");
    }
}
