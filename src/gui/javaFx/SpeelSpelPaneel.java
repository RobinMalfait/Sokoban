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

/**
 *
 * @author robin
 */
public class SpeelSpelPaneel extends BaseGui
{
    private GridPane board;
    private Label complete;
    private Pane overlay;

    
    /**
     * Run het SpeelSpelPaneel
     */
    public void run()
    {
        this.init();
    }
    
    /**
     * Initialiseer het paneel
     */
    private void init()
    {
        stage.setTitle(lang.get("sign.play"));

        this.show("#SpeelSpelPaneel");

        this.findByIdInPane("back").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                (new KiesSpelPaneel()).run();
            }
        });
        
        Button overlayButton = (Button) this.findByIdInPane("overlay_next");
        Button retry = (Button) this.findByIdInPane("retry");

        this.board = (GridPane) this.findByIdInPane("grid");
        this.complete = (Label) this.findByIdInPane("lblComplete");
        this.overlay = (Pane) this.findByIdInPane("overlay");

        retry.setTooltip(new Tooltip(lang.get("game.board.retry").toUpperCase()));

        overlayButton.setText(lang.get("game.board.next").toUpperCase());

        overlayButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                drawBoard();
            }
        });

        retry.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                DC.resetSpelbord();
                drawBoard();
            }
        });

        this.registerEvents();

        this.drawBoard();
    }
    
    /**
     * Teken het bord
     */
    private void drawBoard()
    {
        this.overlay.setVisible(false);
        this.complete.setVisible(false);

        int x = 0;
        int y = 0;

        for (String[] row : DC.toonSpelbord())
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

        if (DC.isEindeSpelbord())
        {
            if (DC.isEindeSpel())
            {
                ((Pane) this.findByIdInPane("win")).setVisible(true);
            } else
            {
                this.overlay.setVisible(true);
                
                this.complete.setText(
                    String.format("%d/%d", DC.geefAantalVoltooideSpelborden(), DC.geefAantalSpelborden())
                );
                
                this.complete.setVisible(true);

                DC.bepaalVolgendSpelbord();
            }
        }
    }

    /**
     * Registreer events
     */
    private void registerEvents()
    {
        stage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                if ( ! DC.isEindeSpel())
                {
                    if (event.getCode().equals(KeyCode.UP))
                    {
                        DC.verplaatsSpelerOmhoog();
                        drawBoard();
                    } else if (event.getCode().equals(KeyCode.DOWN))
                    {
                        DC.verplaatsSpelerOmlaag();
                        drawBoard();

                    } else if (event.getCode().equals(KeyCode.LEFT))
                    {
                        DC.verplaatsSpelerLinks();
                        drawBoard();

                    } else if (event.getCode().equals(KeyCode.RIGHT))
                    {
                        DC.verplaatsSpelerRechts();
                        drawBoard();
                    } else if (event.getCode().equals(KeyCode.ENTER)) // Volgend spel
                    {
                        drawBoard();
                    }
                }
            }
        });

        this.findByIdInPane("up").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                if ( ! DC.isEindeSpel())
                {
                    DC.verplaatsSpelerOmhoog();
                    drawBoard();
                }
            }
        });

        this.findByIdInPane("down").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                if ( ! DC.isEindeSpel())
                {
                    DC.verplaatsSpelerOmlaag();
                    drawBoard();
                }
            }
        });

        this.findByIdInPane("left").setOnMouseClicked(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent event)
            {
                if ( ! DC.isEindeSpel())
                {
                    DC.verplaatsSpelerLinks();
                    drawBoard();
                }
            }
        });

        this.findByIdInPane("right").setOnMouseClicked(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent event)
            {
                if (!DC.isEindeSpel())
                {
                    DC.verplaatsSpelerRechts();
                    drawBoard();
                }
            }
        });
    }

    /**
     * Reset het paneel
     */
    protected void reset()
    {
        DC.resetSpelbord();
        ((Pane) this.findByIdInPane("win")).setVisible(false);
    }

}
