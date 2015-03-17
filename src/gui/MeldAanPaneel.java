package gui;

import javafx.stage.Stage;
import languages.LanguageManager;

/**
 *
 * @author robin
 */
public class MeldAanPaneel extends BaseGui
{
    public MeldAanPaneel(Stage stage, LanguageManager lang)
    {
        stage.setTitle(lang.get("sign.in"));
        
        this.show(stage, "#MeldAanPaneel");
    }
}
