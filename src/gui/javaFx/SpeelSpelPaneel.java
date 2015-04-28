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
public class SpeelSpelPaneel extends BaseGui
{

    private LanguageManager lang;

    private GridPane board;
    private Label boardComplete;
    private Pane overlay;

    public void run()
    {
        this.init();

        Button overlayButton = (Button) this.findByIdInPane("overlay_next");
        Button retry = (Button) this.findByIdInPane("retry");

        this.board = (GridPane) this.findByIdInPane("grid");
        this.boardComplete = (Label) this.findByIdInPane("lblSpelbordComplete");
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

        this.registerEvents(stage);

        this.drawBoard();
    }

    private void drawBoard()
    {
        this.overlay.setVisible(false);

        this.boardComplete.setText("");

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

                this.board.getChildren().clear();

                this.board.getStyleClass().add("win");
            } else
            {
                this.boardComplete.setText(this.lang.get("game.complete"));

                this.overlay.setVisible(true);

                DC.bepaalVolgendSpelbord();
            }
        }
    }

    private void init()
    {
        this.lang = DC.getLanguageManager();

        stage.setTitle("");

        this.show("#SpeelSpelPaneel");

        this.findByIdInPane("back").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                (new KiesSpelPaneel()).run();
            }
        });
    }

    private void registerEvents(Stage stage)
    {
        stage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                if (!DC.isEindeSpel())
                {
                    if (event.getCode().equals(KeyCode.UP))
                    {
                        DC.verplaatsSpeler(1);
                        drawBoard();
                    } else if (event.getCode().equals(KeyCode.DOWN))
                    {
                        DC.verplaatsSpeler(2);
                        drawBoard();

                    } else if (event.getCode().equals(KeyCode.LEFT))
                    {
                        DC.verplaatsSpeler(3);
                        drawBoard();

                    } else if (event.getCode().equals(KeyCode.RIGHT))
                    {
                        DC.verplaatsSpeler(4);
                        drawBoard();
                    } else if (event.getCode().equals(KeyCode.ENTER))
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
                if (!DC.isEindeSpel())
                {
                    DC.verplaatsSpeler(1);
                    drawBoard();
                }
            }
        });

        this.findByIdInPane("down").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                if (!DC.isEindeSpel())
                {
                    DC.verplaatsSpeler(2);
                    drawBoard();
                }
            }
        });

        this.findByIdInPane("left").setOnMouseClicked(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent event)
            {
                if (!DC.isEindeSpel())
                {
                    DC.verplaatsSpeler(3);
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
                    DC.verplaatsSpeler(4);
                    drawBoard();
                }
            }
        });
    }

    protected void reset()
    {
        DC.resetSpelbord();
    }

}
