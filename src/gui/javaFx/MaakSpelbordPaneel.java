package gui.javaFx;

import exceptions.SpelException;
import exceptions.SpelbordException;
import static gui.javaFx.BaseGui.DC;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 *
 * @author robin
 */
public class MaakSpelbordPaneel extends BaseGui
{

    private GridPane board;
    private String activeField = "M"; // Muur standaard
    private String[][] items; // Keep state
    private Label errorLabel, lblAantalSpelborden;
    
    private boolean spelIsOpgeslaan = false;

    /**
     * Run het MaakSpelbordPaneel
     */
    public void run()
    {
        this.init();
        this.reset();
        
        ((Label) findByIdInPane("nameGame")).setText(DC.geefNaamHuidigSpel());
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
        this.lblAantalSpelborden = (Label) this.findByIdInPane("lblAantalSpelborden");
        
        Label lblActive = (Label) this.findByIdInPane("lblActive");
        lblActive.setText(lang.get("game.board.edit.active"));
        
        Label lblNameGame = (Label) this.findByIdInPane("lblNameGame");
        lblNameGame.setText(lang.get("game.name") + ":");
        
        Label nameGame = (Label) this.findByIdInPane("nameGame");
        nameGame.setText(DC.geefNaamHuidigSpel());
        
        Label lblNameGameboard = (Label) this.findByIdInPane("lblNameGameboard");
        lblNameGameboard.setText(lang.get("game.board.name") + ":");
        
        Button saveGameboard = (Button) this.findByIdInPane("saveGameboard");
        saveGameboard.setText(lang.get("save"));
        
        Button saveGame = (Button) this.findByIdInPane("saveGame");
        saveGame.setText(lang.get("game.save"));
        
       
        this.findByIdInPane("back").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                int keuze = 0;
                if(DC.geefAantalSpelborden() != 0)
                {
                    // Er zijn spelborden die volledig zijn, maar nu kijken of het spel is opgeslaan.
                    if(!spelIsOpgeslaan)
                    {
                        Object[] options = {lang.get("yes"), lang.get("no"), lang.get("cancel")};       
                        keuze = JOptionPane.showOptionDialog(null, lang.get("game.dialog.save"), "Stoppen", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        
                        if(keuze == 2)
                            return;
                        
                        if(keuze == 0)
                            saveGame();
                        
                        if(keuze == 1)
                            DC.verwijderHuidigSpel();
                        
                        (new MaakSpelPaneel()).run();
                     
                    }
                }
                else {
                    DC.verwijderHuidigSpel();
                }
                (new MaakSpelPaneel()).run();                
                
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

        this.findByIdInPane("saveGameboard").setOnMouseClicked(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent event)
            {
                TextField gameboardName = (TextField) MaakSpelbordPaneel.this.findByIdInPane("txfGameboard");
                String name = gameboardName.getText();

                if (name == null || name.equals(""))
                {
                    setError(lang.get("err.noGameboardName"));
                } else
                {
                    cleanError();

                    saveGameboard(name);
                }
            }
        });

        this.findByIdInPane("saveGame").setOnMouseClicked(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent event)
            {
                saveGame();
            }
        });        
    }

    /**
     * Registreer alle click events van de sidebar Keuze van veld om dan te
     * kunnen tekenen.
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
     *
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
            for (int row = 0; row < this.items.length; row++)
            {
                for (int cell = 0; cell < this.items[row].length; cell++)
                {
                    DC.voerVakIn(String.format("%d,%d", row, cell), this.items[row][cell]);
                }
            }
            DC.controleerSpelbord();
            resetSpelbord();
            
            updateLblAantalSpelborden();
            errorLabel.setText(lang.get("game.board.saved"));
           
        } 
        catch (SpelException | SpelbordException e)
        {
            DC.verwijderHuidigSpelbord(); // Telkens we op opslaan klikken, voegen we het spelbord toe. Indien er een error is, verwijderen we die terug.
            setError(e.getMessage());
        }
    }
    private void saveGame()
    {
        try
        {
            DC.slaHuidigSpelOp();
            spelIsOpgeslaan = true;
            resetSpelbord();
            setError(lang.get("game.saved"));
        }
        catch(SpelException e)
        {
            setError(e.getMessage());
        }
    }
    /**
     * Reset het volledig paneel
     */
    protected void reset()
    {
        initializeBoard();
        setError("");
        
        ((Label) this.findByIdInPane("lblAantalSpelborden")).setText(lang.choice("game.contentsBoards", 0, "count", 0));
        ((Label) this.findByIdInPane("nameGame")).setText("");
        ((TextField) this.findByIdInPane("txfGameboard")).setText("");
    }

    /**
     * Reset het spelbord in het paneel
     */
    protected void resetSpelbord()
    {
        initializeBoard();
        drawBoard();
        setError("");
        
        ((TextField) this.findByIdInPane("txfGameboard")).setText("");
        
        
    }
    protected void updateLblAantalSpelborden()
    {
        int aantalSpelborden = DC.geefAantalSpelborden();
        
        lblAantalSpelborden.setText(lang.choice("game.contentsBoards", aantalSpelborden, "count", aantalSpelborden));
    }
    
    

    
}
