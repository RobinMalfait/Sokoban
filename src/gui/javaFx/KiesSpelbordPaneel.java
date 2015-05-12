package gui.javaFx;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author robin
 */
public class KiesSpelbordPaneel extends BaseGui
{
    public KiesSpelbordPaneel()
    {

    }

    /**
     * Run het KiesSpelPaneel
     */
    public void run()
    {
        this.init();

        this.drawSpelBoard();
    }

    /**
     * Initialiseer het paneel
     */
    private void init()
    {
        setTitle();

        this.show("#KiesSpelbordPaneel");

        this.findByIdInPane("back").setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                (new KiesSpelPaneel(1)).run();
            }
        });
    }

    /**
     * Stel de titel in
     */
    private void setTitle()
    {
        String speler[] = DC.geefHuidigeSpeler();

        String name = speler[0] + " " + speler[1];

        stage.setTitle(lang.get("game.welcome").toUpperCase() + " " + name + "!");
    }

    /**
     * Teken het spelbord
     */
    private void drawSpelBoard()
    {
        ((Label) this.findByIdInPane("kiesSpel")).setText(lang.get("game.board.choose.list") + ":");

        GridPane grid = (GridPane) this.findByIdInPane("grid");

        String spelborden[][] = DC.geefLijstSpelborden();

        int x = 0;
        int y = 0;

        grid.setAlignment(Pos.CENTER);

        for (String spelbord[] : spelborden)
        {
            Button spelButton = new Button();
            spelButton.setText(spelbord[1]);
            spelButton.getStyleClass().add("btn");
            spelButton.getStyleClass().add("btn-space");
            spelButton.setAlignment(Pos.CENTER);

            spelButton.setOnMouseClicked(new EventHandler<MouseEvent>()
            {

                @Override
                public void handle(MouseEvent event)
                {

                    DC.kiesSpelbord(Integer.parseInt(spelbord[0]));
                    (new WijzigSpelbordPaneel()).run();
                }
            });

            GridPane.setHalignment(spelButton, HPos.CENTER);

            grid.add(spelButton, x, y);

            x++;

            if (x > 3) // Aantal kolommen - 1
            {
                x = 0;
                y++;
            }
        }
    }
}
