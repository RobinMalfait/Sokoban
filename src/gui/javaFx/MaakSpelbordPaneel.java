package gui.javaFx;

import static gui.javaFx.BaseGui.DC;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author robin
 */
public class MaakSpelbordPaneel extends BaseGui
{
    private GridPane board;
        
    private String activeField = "M"; // Muur standaard
    
    private String[][] items; // Keep state
    
    private Label errorLabel;

    public void run()
    {
        this.board = (GridPane) this.findByIdInPane("grid");
        this.items = new String[10][10];
        
        initializeBoard(); 
                
        DC.voegSpelToe("Test Game Robin");
        
        this.init();
        this.reset();
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

    private void init()
    {
        stage.setTitle("");

        this.show("#MaakSpelbordPaneel");
                
        this.errorLabel = (Label) this.findByIdInPane("error");
        ((Label) this.findByIdInPane("gameboard_name_label")).setText(lang.get("game.board.name"));

        this.findByIdInPane("back").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                (new SubMenuPaneel()).run();
            }
        });
        
        this.registerSidebarClickEvents();
        
        this.drawBoard();
        
        this.board.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                updateField((int) event.getSceneX(), (int) event.getSceneY());
            }
        });
        
        this.findByIdInPane("save").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {

            @Override
            public void handle(MouseEvent event)
            {
                TextField gameboardName = (TextField) MaakSpelbordPaneel.this.findByIdInPane("gameboard_name");
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

    private void registerSidebarClickEvents()
    {
        /* Leeg veld */
        this.findByIdInPane("_").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                setActiveField("_");
            }
        });
        
        /* Muur */
        this.findByIdInPane("M").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                setActiveField("M");
            }
        });
        
        /* Doel */
        this.findByIdInPane("D").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                setActiveField("D");
            }
        });
        
        /* Kist */
        this.findByIdInPane("K").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                setActiveField("K");
            }
        });
        
        /* Mannetje */
        this.findByIdInPane("Y").setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event)
            {
                setActiveField("Y");
            }
        });
    }
    
    private void setActiveField(String field)
    {
        this.findByIdInPane("active").getStyleClass().remove(this.activeField);
               
        this.activeField = field;
                
        this.findByIdInPane("active").getStyleClass().add(this.activeField);
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

    protected void reset()
    {
        DC.resetSpelbord();
        ((Label) this.findByIdInPane("error")).setText("");
        TextField gameboardName = (TextField) MaakSpelbordPaneel.this.findByIdInPane("gameboard_name");
        gameboardName.setText("");
    }
}
