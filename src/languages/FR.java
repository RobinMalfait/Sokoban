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
        
        map("sign.succes",              "Vous êtes connecté avec succès!");
        map("sign.retry",               "Refaire ou typ: Stop.");
        map("sign.choise",              "Qu'êtes-vous prêt à faire?");
        map("sign.play",                "Jouer à un jeu");
        map("sign.quit",                "Arreter.");
        map("sign.game.conf",           "Configurer un nouveau jeu");
        map("sign.game.modify",         "Modifier un jeu existant");
        map("sign.quitted",             "Quitté");
        
        map("register.fill.in",         "Remplissez les données suivantes pour vous connecter:");
        map("register.retry",           "Refaire");
        map("register.succes",          "Vous vous avez t'inscrit avec succès:");
        
        map("game.welcome",             "Bienvenue");
        map("game.play",                "Vous aller joué un nouveaux jeu");
        map("game.choose.list",         "Choisissez un jeu dans la liste ci-dessous");
        map("game.choose",              "Choisissez un jeu");
        map("game.board.loading",       "Le tableau est en train de charger");
        map("game.board.retry",         "Refaire");
        map("game.board.completed",     "Félicitations! Vous avez completé le tableau");
        map("game.board.next",          "tableau suivant");
        map("game.board.moves",         "movements");
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
        map("err.integer",              "Il y avait un nombre entier attendue.");
        map("err.nonvalid",             "Choix non valide.");
    }
}
