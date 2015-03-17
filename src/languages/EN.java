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
        map("user.username",            "username");
        map("user.name",                "name");
        map("user.firstname",           "first name");
        map("user.password",            "password");
        map("user.password.repeat",     "password confirmation");
        map("user.admin",               "administrator");
        map("user.added",               "new user added");
        map("user.registration",        "Registration of a new user");
        map("user.logged.in",           "logged in");
        map("user.logged.out",          "logged out");
        map("credentials.wrong",        "wrong credentials");
        map("sign.in",                  "sign in");
        map("sign.up",                  "sign up");
        
        map("game.welcome",             "welcome");
        map("game.play",                "You're going to play a new game");
        map("game.choose.list",         "Choose a game from the list below");
        map("game.choose",              "Choose a game");
        map("game.board.loading",       "The gameboard is loading");
        map("game.board.completed",     "Congratulations! You've completed the gameboard");
        map("game.completed",           "You've completed the game");
        
        map("player.move",              "Move the dude in a direction");
        map("player.up",                "up");
        map("player.down",              "down");
        map("player.left",              "left");
        map("player.right",             "right");
        
        map("list.choice",              "my choice");
        map("list.choose",              "What would you like to do?");
        
        map("app.quit",                 "quit");
        map("app.quited",               "quited");

        map("err.login",                "The username or password is incorrect");
        map("err.passwordrepeat",       "The password and the password confirmation do not match");
        map("err.usernameDR",           "The username must be at least 8 characters long");
        map("err.passwordDR",           "The password does not cope with the requirements");
    }
}
