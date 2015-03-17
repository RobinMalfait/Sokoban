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
        map("registration.new", "Registratie voor een nieuwe speler:");
        map("user.username",            "gebruikersnaam");
        map("user.name",                "naam");
        map("user.firstname",           "voornaam");
        map("user.password",            "wachtwoord");
        map("user.password.repeat",     "wachtwoord herhaling");
        map("user.admin",               "administrator");
        map("user.logged.in",           "ingelogd");
        map("user.logged.out",          "uitgelogd");
        map("credentials.wrong",        "foute gegevens");
        map("sign.in",                  "aanmelden");
        map("sign.up",                  "registreren");
        map("new.player", "Nieuwe speler toegevoegd:");
        map("game.welcome", "welkom");
        map("game.play", "U zult een nieuw spel spelen.");
        map("game.choose.list", "%kies een spel uit onderstaande lijst");
        map("game.choose", "Kies een spel");
        map("game.playboard.load", "Het spelbord wordt geladen");
        map("dude.move", "Verplaats het mannetje in een richting");
        map("dude.up", "omhoog");
        map("dude.down", "omlaag");
        map("dude.left", "links");
        map("dude.right", "rechts");
        map("dude.stop", "stop");
        map("dude.my.choise", "mijn keuze");
        map("game.complete", "gefeliciteerd! je hebt het spel beïndigd.");
            map("game.done","spel compleet");
    
    }
}
