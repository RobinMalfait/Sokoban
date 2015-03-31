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
        //user
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
        
        //sign in/up
        map("sign.in",                  "sign in");
        map("sign.up",                  "sign up");
        map("sign.succes",              "You are successfully logged in!");
        map("sign.retry",               "Retry or typ: Stop.");
        map("sign.choise",              "What are you willing to do?");
        map("sign.play",                "Play a game");
        map("sign.quit",                "Quit.");
        map("sign.game.conf",           "Configure a new game");
        map("sign.game.modify",         "Modify an existing game");
        map("sign.quitted",             "Quitted");
        
        //register
        map("register.fill.in",         "Fill in the next data to sign in:");
        map("register.retry",           "Retry");
        map("register.succes",          "You are successfully signed up.");
        
        //game
        map("game.welcome",             "welcome");
        map("game.play",                "You're going to play a new game");
        map("game.choose.list",         "Choose a game from the list below");
        map("game.choose",              "Choose a game");
        map("game.completed",           "You've completed the game");
        map("game.notFound",            "The game with id :id was not found.");
        map("game.alreadyExists",       "The game with name :name already exists.");
        map("game.notSaved",            "The game has not been saved.");
        
        //gameboard
        map("game.board.loading",       "The gameboard is loading");
        map("game.board.retry",         "Retry");
        map("game.board.completed",     "Congratulations! You've completed the gameboard");
        map("game.board.next",          "next gameboard");
        map("game.board.moves",         "moves");
        map("game.board.notFound",      "The gameboard with id :id in the game :game was not found.");
        
        //player
        map("player.move",              "Move the dude in a direction");
        map("player.up",                "up");
        map("player.down",              "down");
        map("player.left",              "left");
        map("player.right",             "right");
        
        //list
        map("list.choice",              "my choice");
        map("list.choose",              "What would you like to do?");
        
        //application
        map("app.quit",                 "quit");
        map("app.quited",               "quited");
        
        //error
        map("err.login",                "The username or password is incorrect");
        map("err.passwordrepeat",       "The password and the password confirmation do not match");
        map("err.usernameDR",           "The username must be at least 8 characters long");
        map("err.passwordDR",           "The password does not cope with the requirements");
        map("err.integer",              "There was an integer expected.");
        map("err.nonvalid",             "Non valid choice.");
        map("err.input",                "The choice should be between :min and :max.");
        map("err.NaN",                  "You didn't enter a number.");
    }
}
