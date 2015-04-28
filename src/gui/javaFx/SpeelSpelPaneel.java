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

    private final GridPane board;
    private final Label boardComplete;
    private final Pane overlay;

    public SpeelSpelPaneel(Stage stage)
    {
        this.init(stage);

        Button overlayButton = (Button) this.findByIdInPane(stage, "overlay_next");
        Button retry = (Button) this.findByIdInPane(stage, "retry");

        this.board = (GridPane) this.findByIdInPane(stage, "grid");
        this.boardComplete = (Label) this.findByIdInPane(stage, "lblSpelbordComplete");
        this.overlay = (Pane) this.findByIdInPane(stage, "overlay");

        retry.setTooltip(new Tooltip(lang.get("game.board.retry").toUpperCase()));

        overlayButton.setText(lang.get("game.board.next").toUpperCase());

        overlayButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                SpeelSpelPaneel.this.drawBoard(stage);
            }
        });

        retry.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                DC.resetSpelbord();
                SpeelSpelPaneel.this.drawBoard(stage);
            }
        });

        this.registerEvents(stage);

        this.drawBoard(stage);
    }

    private void drawBoard(Stage stage)
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

    private void init(Stage stage)
    {
        this.lang = DC.getLanguageManager();

        stage.setTitle("");

        this.show(stage, "#SpeelSpelPaneel");

        this.findByIdInPane(stage, "back").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                KiesSpelPaneel kiesSpelPaneel = new KiesSpelPaneel(stage);
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
                        SpeelSpelPaneel.this.drawBoard(stage);
                    } else if (event.getCode().equals(KeyCode.DOWN))
                    {
                        DC.verplaatsSpeler(2);
                        SpeelSpelPaneel.this.drawBoard(stage);

                    } else if (event.getCode().equals(KeyCode.LEFT))
                    {
                        DC.verplaatsSpeler(3);
                        SpeelSpelPaneel.this.drawBoard(stage);

                    } else if (event.getCode().equals(KeyCode.RIGHT))
                    {
                        DC.verplaatsSpeler(4);
                        SpeelSpelPaneel.this.drawBoard(stage);
                    } else if (event.getCode().equals(KeyCode.ENTER))
                    {
                        SpeelSpelPaneel.this.drawBoard(stage);
                    }
                }
            }
        });

        this.findByIdInPane(stage, "up").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                if (!DC.isEindeSpel())
                {
                    DC.verplaatsSpeler(1);
                    SpeelSpelPaneel.this.drawBoard(stage);
                }
            }
        });

        this.findByIdInPane(stage, "down").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                if (!DC.isEindeSpel())
                {
                    DC.verplaatsSpeler(2);
                    SpeelSpelPaneel.this.drawBoard(stage);
                }
            }
        });

        this.findByIdInPane(stage, "left").setOnMouseClicked(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent event)
            {
                if (!DC.isEindeSpel())
                {
                    DC.verplaatsSpeler(3);
                    SpeelSpelPaneel.this.drawBoard(stage);
                }
            }
        });

        this.findByIdInPane(stage, "right").setOnMouseClicked(new EventHandler<MouseEvent>()
        {

            @Override
            public void handle(MouseEvent event)
            {
                if (!DC.isEindeSpel())
                {
                    DC.verplaatsSpeler(4);
                    SpeelSpelPaneel.this.drawBoard(stage);
                }
            }
        });
    }

    @Override
    protected void reset(Stage stage)
    {
        DC.resetSpelbord();
    }

}
