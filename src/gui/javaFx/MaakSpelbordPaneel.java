package gui.javaFx;

import exceptions.SpelException;
import exceptions.SpelbordException;
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

    /**
     * Run het MaakSpelbordPaneel
     */
    public void run()
    {
        this.init();
        this.reset();
    }

    /**
     * Initialiseer het bord
     */
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

    /**
     * Initialiseer het paneel
     */
    private void init()
    {
        this.board = (GridPane) this.findByIdInPane("grid");
        this.items = new String[10][10];
        
        initializeBoard(); 
        
        stage.setTitle("");

        this.show("#MaakSpelbordPaneel");
        
        this.registerSidebarClickEvents();
        
        this.drawBoard();
                
        this.errorLabel = (Label) this.findByIdInPane("lblError");
        ((Label) this.findByIdInPane("lblSpelbordNaam")).setText(DC.geefNaamHuidigSpel());

        this.findByIdInPane("back").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                (new SubMenuPaneel()).run();
            }
        });
        
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
                System.out.println(name);
                
                if (name == null || name.equals("")) {
                    setError(lang.get("err.noGameboadName"));
                } else {
                    cleanError();
                    
                    saveGameboard(name);
                }
            }
        });
    }

    /**
     * Registreer alle click events van de sidebar
     * Keuze van veld om dan te kunnen tekenen.
     */
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
    
    /**
     * Zet een veldje op actief.
     * 
     * @param field 
     */
    private void setActiveField(String field)
    {
        this.findByIdInPane("active").getStyleClass().remove(this.activeField);
               
        this.activeField = field;
                
        this.findByIdInPane("active").getStyleClass().add(this.activeField);
    }
    
    /**
     * Teken het bord
     */
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

    /**
     * Update een veld met coordinaten (x, y)
     * @param x
     * @param y 
     */
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

    /**
     * Stel de error in
     * 
     * @param text 
     */
    private void setError(String text)
    {
        this.errorLabel.setText(text);
    }

    /**
     * Maak de error leeg
     */
    private void cleanError()
    {
        this.setError(""); // Clear the text
    }

    /**
     * Sla het spelbord op
     * 
     * @param name 
     */
    private void saveGameboard(String name)
    {
        try 
        {
            DC.voegSpelbordToe(name);
            for(int row = 0; row < this.items.length; row++) {
                for (int cell = 0; cell < this.items[row].length; cell++) {
                    DC.voerVakIn(String.format("%d,%d", row, cell), this.items[row][cell]);
                }
            }  
            DC.slaHuidigSpelOp();
        }
        catch(SpelException | SpelbordException e)
        {
            setError(e.getMessage());
        }  
    }

    /**
     * Reset het paneel
     */
    protected void reset()
    {
        
        initializeBoard();     
        setError("");
        
        ((Label) this.findByIdInPane("lblSpelbordNaam")).setText("");
        ((TextField) this.findByIdInPane("gameboard_name")).setText("");
    }
}
