package languages;

/**
 *
 * @author robin
 */
public class EN extends Language
{
    /**
     * Maak een nieuw EN-object aan
     */
    public EN()
    {
        map("registration.new",         "Registration for the new player:");
        map("user.username",            "username");
        map("user.name",                "name");
        map("user.firstname",           "first name");
        map("user.password",            "password");
        map("user.password.repeat",     "repeat password");
        map("user.admin",               "administrator");
        map("user.logged.in",           "logged in");
        map("user.logged.out",          "logged out");
        map("credentials.wrong",        "wrong credentials");
        map("sign.in",                  "sign in");
        map("sign.up",                  "sign up");
        map("new.player",               "New player added:");
        map("game.welcome",             "welcome");
        map("game.play",                "You will be playing a new game.");
        map("game.choose.list",         "Choose a game from the list underneath");
        map("game.choose",              "Choose a game");
        map("game.playboard.load",      "The playboard is loading");
        map("dude.move",                "move the dude in a direction");
        map("dude.up",                  "up");
        map("dude.down",                "down");
        map("dude.left",                "left");
        map("dude.right",               "right");
        map("dude.stop",                "stop");
        map("dude.my.choise",           "my choise");
        map("game.complete",            "Congradulations! you completed the game");
        map("game.done",                "Game complete");

    }
}
