package languages;

/**
 *
 * @author robin
 */
public class WVL extends Language
{
    /**
     * Maak een nieuw EN-object aan
     */
    public WVL()
    {
        map("user.username",            "Gebrukersnoame");
        map("user.name",                "Noame");
        map("user.firstname",           "Vwor noame");
        map("user.password",            "Wagtwoord");
        map("user.password.repeat",     "Wagtwoord bevestign");
        map("user.admin",               "Beheerdre");
        map("user.added",               "Niewe speeldr toegevoegd");
        map("user.registration",        "Registroatie van nen nieuwe speeldr");
        map("user.logged.in",           "Igelogd");
        map("user.logged.out",          "Utgelogd");
        map("credentials.wrong",        "Misse gehevens");
        map("sign.in",                  "Inlogn");
        map("sign.up",                  "Inskrivn");
        
        map("sign.succes",              "You are successfully logged in!");
        map("sign.retry",               "Retry or typ: Stop.");
        map("sign.choise",              "What are you willing to do?");
        map("sign.play",                "Play a game");
        map("sign.quit",                "Quit.");
        map("sign.game.conf",           "Configurate a new game");
        map("sign.game.modify",         "Modify an existing game");
        map("sign.quitted",             "Quitted");
        
        map("register.fill.in",         "Fill in the next data to sign in:");
        map("register.retry",           "Retry");
        map("register.succes",          "You are successfully signed up.");
        
        map("game.welcome",             "Welgekoomn");
        map("game.play",                "Ge goa een nieuw spelleke speeln");
        map("game.choose.list",         "Kiest een spelleke van onderstoande liste");
        map("game.choose",              "Kiest een spelleke");
        map("game.board.loading",       "'t Spelbord is ant loadn");
        map("game.board.completed",     "Felicitajes! Jet et spelbord utgespeeld");
        map("game.completed",           "Get spel utgespeeld");
        
        map("player.move",              "Beweegt jen peetje in een rigtinge");
        map("player.up",                "Noa boovn");
        map("player.down",              "Noa beneedn");
        map("player.left",              "Noa links");
        map("player.right",             "Noa reks");
        
        map("list.choice",              "Men keuze");
        map("list.choose",              "Wuk wildje doene?");
        
        map("app.quit",                 "Stoppn");
        map("app.quited",               "Gestopt");

        map("err.login",                "Jen gebrukersnoame of jen wagtwoord i mis");
        map("err.passwordrepeat",       "Jen wagtwoord kom nie overjin me jen wagtwoord bevestiginge");
        map("err.usernameDR",           "Jen gebrukersnoame moe mistns 8 tjikns lank zin");
        map("err.passwordDR",           "Jen wagtwoord voldoe nie an de eisn");
        map("err.integer",              "There was an integer expected.");
        map("err.nonvalid",             "Non valid choise.");
    }
}
