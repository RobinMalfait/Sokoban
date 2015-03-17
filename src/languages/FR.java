package languages;

/**
 *
 * @author robin
 */
public class FR extends Language 
{
    public FR()
    {
        map("registration.new", "Registration pour le nouveaux joueur:");
        map("user.username",            "nom d'utilisateur");
        map("user.name",                "nom");
        map("user.firstname",           "prénom");
        map("user.password",            "mot d'acces");
        map("user.password.repeat",     "repete le mot d'acces");
        map("user.admin",               "administrateur");
        map("user.logged.in",           "connecté");
        map("user.logged.out",          "déconnecté");
        map("credentials.wrong",        "mal informations d'identification");
        map("sign.in",                  "se connecter");
        map("sign.up",                  "s'inscrire"); 
         map("new.player", "Nouveaux joueur ajouté");
        map("game.welcome", "Bienvenue");
        map("game.play", "Vous aller joué un nouveaux jeu");
        map("game.choose.list", "%Choisissez un jeu dans la liste ci-dessous");
        map("game.choose", "Choisissez un jeu");
        map("game.playboard.load", "Le plateau de jeu est en cours de chargement");
    }
}


