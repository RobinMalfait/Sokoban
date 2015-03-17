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
        map("registration.new",         "Inscription d'un nouveaux joueur");
        map("user.username",            "nom d'utilisateur");
        map("user.name",                "nom");
        map("user.firstname",           "prénom");
        map("user.password",            "mot de passe");
        map("user.password.repeat",     "repetition de mot de passe");
        map("user.admin",               "administrateur");
        map("user.logged.in",           "en ligne");
        map("user.logged.out",          "hors ligne");
        map("credentials.wrong",        "Mal informations d'identification");
        map("sign.in",                  "se connecter");
        map("sign.up",                  "s'inscrire");
        map("user.added",               "Nouveaux joueur ajouté");
        map("game.welcome",             "Bienvenue");
        map("game.play",                "Vous aller joué un nouveaux jeu");
        map("game.choose.list",         "Choisissez un jeu dans la liste ci-dessous");
        map("game.choose",              "Choisissez un jeu");
        map("game.board.loading",       "Le tableau est en train de charger");
        map("player.move",              "Déplacer le mec dans une direction");
        map("player.up",                "en haut");
        map("player.down",              "en bas");
        map("player.left",              "à gauche");
        map("player.right",             "à droite");
        map("game.board.completed",     "Félicitations! Vous avez completé le tableau");
        map("game.completed",           "Vous avez completé le jeu");
        map("list.choice",              "mon choix");
        map("list.choose",              "Qu'est-ce que vous voulez faire?");
        map("app.quit",                 "arrêter");
        map("app.quited",               "arrêté");
    }
}


