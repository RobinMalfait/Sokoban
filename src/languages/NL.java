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
        map("user.username",            "Gebruikersnaam");
        map("user.name",                "Naam");
        map("user.firstname",           "Voornaam");
        map("user.password",            "Wachtwoord");
        map("user.password.repeat",     "Wachtwoordherhaling");
        map("user.admin",               "administrator");
        map("user.added",               "Nieuwe speler toegevoegd");
        map("user.registration",        "Registratie van een nieuwe speler");
        map("user.logged.in",           "ingelogd");
        map("user.logged.out",          "uitgelogd");
        map("credentials.wrong",        "foute gegevens");
        map("sign.in",                  "aanmelden");
        map("sign.up",                  "registreren");
              
        map("game.welcome",             "welkom");
        map("game.play",                "U zult een nieuw spel spelen");
        map("game.choose.list",         "Kies een spel uit onderstaande lijst");
        map("game.choose",              "Kies een spel");
        map("game.board.loading",       "Het spelbord wordt geladen");
        map("game.board.completed",     "Gefeliciteerd! Je hebt het spelbord voltooid");
        map("game.board.next",          "Volgende spelbord");
        map("game.completed",           "Je hebt het spel voltooid");
        
        map("player.move",              "Verplaats het mannetje in een richting");
        map("player.up",                "omhoog");
        map("player.down",              "omlaag");
        map("player.left",              "links");
        map("player.right",             "rechts");
        
        map("list.choice",              "Mijn keuze");
        map("list.choose",              "Wat wenst u te doen?");
        
        map("app.quit",                 "stoppen");
        map("app.quited",               "gestopt");
        
        map("err.login",                "De gebruikersnaam of het wachtwoord is incorrect.");
        map("err.passwordrepeat",       "Het wachtwoord en de wachtwoordherhaling komen niet overeen");
        map("err.usernameDR",           "De gebruikersnaam moet minstens 8 karakters lang zijn");
        map("err.passwordDR",           "Het wachtwoord voldoet niet aan de eisen");
    }
}
