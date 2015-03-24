import domein.DomeinController;
import gui.javaFx.StartUpGui;
import javafx.application.Application;
import javafx.stage.Stage;
import languages.EN;
import languages.FR;
import languages.LanguageManager;
import languages.NL;
import languages.WVL;

/**
 *
 * @author robin
 */
public class StartUp extends Application
{

    public static void main(String... args)
    {
        /*(new ConsoleApplicatie()).start(
            new DomeinController(), 
            new Scanner(System.in)
        );*/
        
        Application.launch(StartUp.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        LanguageManager lang = new LanguageManager();

        lang.addLanguage(new NL());
        lang.addLanguage(new FR());
        lang.addLanguage(new EN());
        lang.addLanguage(new WVL());
        
        (new StartUpGui(
            new DomeinController(lang)
        )).start(stage);
    }
}
