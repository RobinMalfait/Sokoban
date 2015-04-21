package languages;

/**
 *
 * @author robin
 */
public class NL extends Language
{
    /**
     * Maak een nieuw NL-object aan
     */
    public NL()
    {
        //user
        map("user.username",            "Gebruikersnaam");
        map("user.name",                "Familienaam");
        map("user.firstname",           "Voornaam");
        map("user.password",            "Wachtwoord");
        map("user.password.repeat",     "Wachtwoordherhaling");
        map("user.admin",               "administrator");
        map("user.added",               "Nieuwe speler toegevoegd");
        map("user.registration",        "Registratie van een nieuwe speler");
        map("user.logged.in",           "ingelogd");
        map("user.logged.out",          "uitgelogd");
        map("user.username.exists",     "Deze gebruikersnaam is al in gebruik.");
        map("credentials.wrong",        "foute gegevens");
        
        //sign in/up
        map("sign.in",                  "aanmelden");
        map("sign.up",                  "registreren");
        map("sign.succes",              "Je bent succesvol ingelogd");
        map("sign.retry",               "Opnieuw proberen of typ: Stop.");
        map("sign.choise",              "Wat wil je doen?");
        map("sign.play",                "Speel een spel");
        map("sign.quit",                "Stop");
        map("sign.game.conf",           "Maak een spel");
        map("sign.game.modify",         "Pas een bestaand spel aan");
        map("sign.quitted",             "Gestopt");
        
        //register
        map("register.fill.in",         "Vul onderstaande data in om je te registeren:");
        map("register.retry",           "Opnieuw proberen");
        map("register.succes",          "Je bent succesvol geregistreerd.");
        
        //game
        map("game.welcome",             "Welkom");
        map("game.play",                "Speel een spel");
        map("game.choose.list",         "Kies een spel uit onderstaande lijst");
        map("game.choose",              "Kies een spel");
        map("game.completed",           "Je hebt het spel voltooid");
        map("game.notFound",            "Het spel met id :id werd niet gevonden.");
        map("game.exists",              "Er bestaat al een spel met deze naam.");
        map("game.notSaved",            "Het spel werd niet opgeslaan.");
        map("game.modify",              "Wijzig een spel");
        map("game.create",              "Maak een spel");
        
        //gameboard
        map("game.board.loading",       "Het spelbord wordt geladen");
        map("game.board.retry",         "Opnieuw");
        map("game.board.completed",     "Gefeliciteerd! Je hebt het spelbord voltooid");
        map("game.board.next",          "volgende spelbord");
        map("game.board.moves",         "verplaatsingen");
        map("game.board.notFound",      "Het spelbord met id :id werd niet gevonden.");
        map("game.board.exists",        "Er bestaat al een spelbord met deze naam in dit spel.");
        
        //player
        map("player.move",              "Verplaats het mannetje in een richting");
        map("player.up",                "omhoog");
        map("player.down",              "omlaag");
        map("player.left",              "links");
        map("player.right",             "rechts");
        map("player.wrongDirection",    "De richting moet tussen :min en :max liggen.");
        map("player.wrongCoordinates",  "De coördinaten liggen buiten het bereik van het spelbord.");
        
        //list
        map("list.choice",              "Mijn keuze");
        map("list.choose",              "Wat wenst u te doen?");
        
        //application
        map("app.quit",                 "stoppen");
        map("app.quited",               "gestopt");
        
        //error
        map("err.login",                "De gebruikersnaam of het wachtwoord is incorrect.");
        map("err.passwordrepeat",       "Het wachtwoord en de wachtwoordherhaling komen niet overeen");
        map("err.usernameDR",           "De gebruikersnaam moet minstens 8 karakters lang zijn");
        map("err.passwordDR",           "Het wachtwoord moet 8 karakters lang zijn en moet een hoofdletter en een cijfer bevatten.");
        map("err.integer",              "Er werd een geheel getal verwacht.");
        map("err.nonvalid",             "Niet geldige keuze.");
        map("err.input",                "De keuze moet tussen :min en :max liggen.");
        map("err.NaN",                  "U gaf geen nummer in.");
        map("err.invalidType",          "Ongeldig type");
    }
}
