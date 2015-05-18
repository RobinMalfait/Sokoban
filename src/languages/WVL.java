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
        //user
        map("user.username",            "Gebrukersnoame");
        map("user.name",                "Achternoame");
        map("user.firstname",           "Vwornoame");
        map("user.password",            "Wagtwoord");
        map("user.password.repeat",     "Wagtwordherhoalinge");
        map("user.admin",               "Beheerdre");
        map("user.added",               "Niewe speelr toegevoegd");
        map("user.registration",        "Registroatie van nen nieuwe speelr");
        map("user.logged.in",           "Igelogd");
        map("user.logged.out",          "Utgelogd");
        map("user.username.exists",     "Ted ol etwien dien gebrukersnoame.");
        map("credentials.wrong",        "Misse gehevens");
        
        //sign in/up
        map("sign.in",                  "Inlogn");
        map("sign.up",                  "Inskrivn");
        map("sign.succes",              "Ge zie me successe igelogd");
        map("sign.retry",               "Kèreki wére of tiept: Stop");
        map("sign.choise",              "Wuk wildje doene?");
        map("sign.play",                "E spelleke speeln");
        map("sign.quit",                "Stoppn");
        map("sign.game.conf",           "E spel moakn");
        map("sign.game.modify",         "Past nen bestoand spel an");
        map("sign.quitted",             "Gestoakt");
        
        //register
        map("register.fill.in",         "Vult de onderstoande veldn in vo jen in te skrivn");
        map("register.retry",           "Kèreki wére.");
        map("register.succes",          "Ge zit succesvol ingeskreevn");
        
        //game
        map("game.welcome",             "Welgekoomn");
        map("game.play",                "E spel speeln");
        map("game.choose.list",         "Kiest een spelleke van onderstoande liste");
        map("game.choose",              "Kiest een spelleke");
        map("game.completed",           "Get spel utgespeeld");
        map("game.notFound",            "Tspel me nummer :id is nie weregevondn.");
        map("game.exists",              "Tis ol e spel me die noame.");
        map("game.notSaved",            "Tspel is nie upgesleegn.");
        map("game.modify",              "E spel verandern");
        map("game.create",              "E spel moakn");
        map("game.notCompleted",        "Der zitn gin voltooide spelbordn int spel");
        map("game.new",                 "Noame vot nieuwe spel");
        
        //gameboard
        map("game.board",               "Spelbord");
        map("game.board.loading",       "Tspelbord is ant loadn");
        map("game.board.retry",         "Kèreki wére!");
        map("game.board.completed",     "Felicitajes! Jet et spelbord utgespeeld");
        map("game.board.next",          "noaste spelbord");
        map("game.board.moves",         "verplatsingn");
        map("game.board.notFound",      "Tspelbord met nummer :id is nie weregevondn in da spel ier.");
        map("game.board.exists",        "Tis ol e spelbord me die noamde in da spel ier.");
        map("game.board.has",           "Der stoat nu :count ventje upt spelbord|Der stoan nu :count ventjes upt spelbord"); // 1 mannetje | meerdere mannetjes
        map("game.board.mustHavePlayer","Der moet jin ventje upt spelbord stoan");
        map("game.board.playerLimit",   "Der meugt mo jin ventje upt spelbord stoan");
        map("game.board.boxEquality",   "Taantal kistn en doeln is nie gelik.");
        map("game.board.noElements",    "Der moe minstens jin kiste en jin doel upt spelbord stoan.");
        
        //player
        map("player.move",              "Beweegt jen peetje in een rigtinge");
        map("player.up",                "Noa boovn");
        map("player.down",              "Noa beneedn");
        map("player.left",              "Noa links");
        map("player.right",             "Noa reks");
        map("player.wrongDirection",    "De richtinge moe tusn :min e :max lign.");
        map("player.wrongCoordinates",  "De coördinoatn lign butn tspelbord.");
        
        //list
        map("list.choice",              "Men keuze");
        map("list.choose",              "Wuk wildje doene?");
        
        //application
        map("app.quit",                 "Stoppn");
        map("app.quited",               "Gestopt");
        
        //error
        map("err.login",                "Jen gebrukersnoame of jen wagtwoord i mis");
        map("err.passwordrepeat",       "Jen wagtwoord kom nie overjin me jen wagtwoord bevestiginge");
        map("err.usernameDR",           "Jen gebrukersnoame moe mistns 8 teekns lank zin");
        map("err.passwordDR",           "Jen wagtwoord moe mistns 8 teekns lank zin en e oofdletter en ciffer bevatn");
        map("err.integer",              "Ze verwagtn een geheel getal.");
        map("err.nonvalid",             "Gin geldige keuze.");
        map("err.input",                "Jen keuze moe tusn :min en :max lign.");
        map("err.NaN",                  "Da wos gin ciffer.");
        map("err.invalidType",          "Ongeldig tipe");
        map("err.noCurrentBoard",       "Kost gin spelbord kiezn int huidige spel");
    
    }
}
