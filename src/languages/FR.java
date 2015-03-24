package languages;

/**
 *
 * @author robin
 */
public class FR extends Language
{
    /**
     * Maak een nieuw FR-object aan
     */
    public FR()
    {
        map("user.username",            "nom d'utilisateur");
        map("user.name",                "nom");
        map("user.firstname",           "prénom");
        map("user.password",            "mot de passe");
        map("user.password.repeat",     "repetition de mot de passe");
        map("user.admin",               "administrateur");
        map("user.added",               "Nouveaux joueur ajouté");        
        map("user.registration",        "Inscription d'un nouveaux joueur");
        map("user.logged.in",           "en ligne");
        map("user.logged.out",          "hors ligne");
        map("credentials.wrong",        "Mal informations d'identification");
        map("sign.in",                  "se connecter");
        map("sign.up",                  "s'inscrire");
        
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
        
        map("game.welcome",             "Bienvenue");
        map("game.play",                "Vous aller joué un nouveaux jeu");
        map("game.choose.list",         "Choisissez un jeu dans la liste ci-dessous");
        map("game.choose",              "Choisissez un jeu");
        map("game.board.loading",       "Le tableau est en train de charger");
        map("game.board.completed",     "Félicitations! Vous avez completé le tableau");
        map("game.completed",           "Vous avez completé le jeu");
        
        map("player.move",              "Déplacer le mec dans une direction");
        map("player.up",                "en haut");
        map("player.down",              "en bas");
        map("player.left",              "à gauche");
        map("player.right",             "à droite");
        
        map("list.choice",              "mon choix");
        map("list.choose",              "Qu'est-ce que vous voulez faire?");
        
        map("app.quit",                 "arrêter");
        map("app.quited",               "arrêté");
        
        map("err.login",                "Le nom d'utilisateur ou le mot de passe est incorrect");
        map("err.passwordrepeat",       "Le mot de passe et la repitition de mot de passe ne correspondent pas");
        map("err.usernameDR",           "Le nom d'utilisateur doit être au moins 8 caractères");
        map("err.passwordDR",           "Le mot de passe ne répond pas aux prescriptions");
        map("err.integer",              "There was an integer expected.");
        map("err.nonvalid",             "Non valid choise.");
    }
}
