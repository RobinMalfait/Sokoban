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
        map("user.name",                "last name");
        map("user.firstname",           "first name");
        map("user.password",            "password");
        map("user.password.repeat",     "password confirmation");
        map("user.admin",               "administrator");
        map("user.added",               "new user added");
        map("user.registration",        "Registration of a new user");
        map("user.logged.in",           "logged in");
        map("user.logged.out",          "logged out");
        map("user.username.exists",     "This username is already taken.");
        map("credentials.wrong",        "wrong credentials");
        
        //sign in/up
        map("sign.in",                  "sign in");
        map("sign.up",                  "sign up");
        map("sign.succes",              "You are successfully logged in!");
        map("sign.retry",               "Retry or typ: Stop.");
        map("sign.choise",              "What are you willing to do?");
        map("sign.play",                "Play a game");
        map("sign.quit",                "Quit.");
        map("sign.game.conf",           "Make a game");
        map("sign.game.modify",         "Modify an existing game");
        map("sign.quitted",             "Quitted");
        
        //register
        map("register.fill.in",         "Fill in the next data to sign in:");
        map("register.retry",           "Retry");
        map("register.succes",          "You are successfully signed up.");
        
        //game
        map("game.welcome",             "welcome");
        map("game.play",                "Play a game");
        map("game.choose.list",         "Choose a game from the list below");
        map("game.choose",              "Choose a game");
        map("game.completed",           "You've completed the game");
        map("game.notFound",            "The game with id :id was not found.");
        map("game.exists",              "There is already a game with this name.");
        map("game.notSaved",            "The game has not been saved.");
        map("game.modify",              "Modify a game");
        map("game.create",              "Create new game");
        map("game.notCompleted",        "The game has no finished gameboards");
        map("game.new",                 "Name for the new game");
        map("game.contentsBoards",      "The game has :count completed board.|The game has :count completed boards.");//"Het spel telt :count voltooid spelbord.|Het spel telt :count voltooide spelborden.");
        map("game.saved",               "The game has been saved with the completed gameboards.");//"Het spel werd opgeslaan met de voltooide spelborden!");
        map("game.name",                "Name game"); //"Naam spel");
        map("game.dialog.save",         "Would you like to save the game?"); //"Wenst u het spel op te slaan?");
        map("game.save",                "Save game"); //"Sla spel op
        
        //gameboard
        map("game.board",               "Gameboard");
        map("game.board.loading",       "The gameboard is loading");
        map("game.board.retry",         "Retry");
        map("game.board.completed",     "Congratulations! You've completed the gameboard");
        map("game.board.next",          "next gameboard");
        map("game.board.moves",         "moves");
        map("game.board.notFound",      "The gameboard with id :id was not found in this game.");
        map("game.board.exists",        "There is already a gameboard with this name in this game.");
        map("game.board.has",           "The gameboard has :count dude|The gameboard has :count dudes"); // 1 mannetje | meerdere mannetjes
        map("game.board.mustHavePlayer","The gameboard must have one dude.");
        map("game.board.playerLimit",   "The gameboard may only have one dude.");
        map("game.board.boxEquality",   "The number of boxes and goals is not equal.");
        map("game.board.noElements",    "The gameboard must contain at least one box and one goal.");
        map("game.board.choose.list",   "Choose a gameboard from the list below");
        map("game.board.edit.active",   "Active");
        map("game.board.save",          "Are you sure you want to save the gameboard?");
        map("game.board.cancel",        "You are going to cancel all changes. Are you sure?");
        map("game.board.cancelled",     "The gameboard has been brought back to its original state!");
        map("game.board.saved",         "The gameboard has been saved!");  
        map("game.board.name",          "Name gameboard"); //"Naam spelbord");
        
        
        //player
        map("player.move",              "Move the dude in a direction");
        map("player.up",                "up");
        map("player.down",              "down");
        map("player.left",              "left");
        map("player.right",             "right");
        map("player.wrongDirection",    "The direction should be between :min and :max.");
        map("player.wrongCoordinates",  "The coordinates are out of the range of the game board");
        
        //list
        map("list.choice",              "my choice");
        map("list.choose",              "What would you like to do?");
        
        //application
        map("app.quit",                 "quit");
        map("app.quited",               "quited");
        
        //error
        map("err.login",                "The username or password is incorrect");
        map("err.passwordrepeat",       "The password and the password confirmation do not match");
        map("err.usernameDR",           "The username must be at least 8 characters");
        map("err.passwordDR",           "The password must be at least 8 characters and must contain a captal and a number.");
        map("err.integer",              "There was an integer expected.");
        map("err.nonvalid",             "Non valid choice.");
        map("err.input",                "The choice should be between :min and :max.");
        map("err.NaN",                  "You didn't enter a number.");
        map("err.invalidType",          "Invalid type");
        map("err.noCurrentBoard",       "Couldn't select a board for current game.");
        map("err.noGameName",           "Enter a name for the new game!");
        map("err.noGameboardName",      "Enter a name for the gameboard!"); //"Vul een naam voor het spelbord in!");
        
        //keywords
        map("yes",                      "Yes");
        map("no",                       "No");
        map("cancel",                   "Cancel");
        map("save",                     "Save");
        map("delete",                   "Delete");
    
    }
}
