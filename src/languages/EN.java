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
        map("registration.new",         "Registration of a new user");
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
        map("user.added",               "new user added");
        map("game.welcome",             "welcome");
        map("game.play",                "You're going to play a new game");
        map("game.choose.list",         "Choose a game from the list below");
        map("game.choose",              "Choose a game");
        map("game.board.loading",       "The gameboard is loading");
        map("player.move",              "Move the dude in a direction");
        map("player.up",                "up");
        map("player.down",              "down");
        map("player.left",              "left");
        map("player.right",             "right");
        map("game.board.completed",     "Congratulations! You've completed the gameboard");
        map("game.completed",           "You've completed the game");
        map("list.choice",              "my choice");
        map("list.choose",              "What would you like to do?");
        map("app.quit",                 "quit");
        map("app.quited",               "quited");

    }
}
