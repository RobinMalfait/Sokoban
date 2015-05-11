package gui.javaFx;

import exceptions.SpelException;
import exceptions.SpelbordException;
import static gui.javaFx.BaseGui.DC;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 *
 * @author robin
 */
public class WijzigSpelbordPaneel extends BaseGui
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
        this.resetSpelbord();
    }

    /**
     * Initialiseer het paneel
     */
    private void init()
    {
        this.board = (GridPane) this.findByIdInPane("grid");
        this.items = DC.toonSpelbord();

        stage.setTitle("");

        this.show("#WijzigSpelbordPaneel");

        this.registerSidebarClickEvents();

        this.drawBoard();

        this.errorLabel = (Label) this.findByIdInPane("lblError");

        this.findByIdInPane("back").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                (new KiesSpelbordPaneel()).run();
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
                saveGameboard();
            }
        });

        this.findByIdInPane("verwijder").setOnMouseClicked(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent event)
            {
                DC.verwijderHuidigSpelbord();
                setError("Het spelbord is verwijderd!");
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
    private void saveGameboard()
    {
        try
        {
            for (int row = 0; row < this.items.length; row++)
            {
                for (int cell = 0; cell < this.items[row].length; cell++)
                {
                    DC.voerVakIn(String.format("%d,%d", row, cell), this.items[row][cell]);
                }
            }

            Object[] options =
            {
                "Ja", "Nee", "Annuleren"
            };
            int keuze = JOptionPane.showOptionDialog(null, "Weet u zeker dat u het spelbord wil opslaan?", "Spelbord opslaan", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (keuze == 1)
            {
                // Men wil de wijzigingen verwerpen
                Object[] options2 =
                {
                    "Ja", "Nee"
                };
                keuze = JOptionPane.showOptionDialog(null, "U staat op het punt om alle wijzigingen ongedaan te maken. Weet u dit zeker?", "Ongedaanmaken spelbord", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);

                if (keuze == 0)
                {
                    DC.resetSpelbord();
                    setError("Het spelbord werd terug in zijn originele toestand gebracht!");
                }
            } else if (keuze == 0)
            {
                DC.controleerSpelbord();
                DC.slaHuidigSpelbordOp();
                setError("Het spelbord werd succesvol opgeslaan!");
            }
            this.items = DC.toonSpelbord();
            drawBoard();

        } catch (SpelException | SpelbordException e)
        {
            setError(e.getMessage());
        }
    }

    /**
     * Reset het spelbord in het paneel
     */
    protected void resetSpelbord()
    {
        drawBoard();
        setError("");
    }

}
