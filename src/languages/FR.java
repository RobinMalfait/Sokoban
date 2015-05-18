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
        //user
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
        map("user.username.exists",     "Ce nom d'utilisateur est déjà pris.");
        map("credentials.wrong",        "Mal informations d'identification");
        
        //sign in/up
        map("sign.in",                  "se connecter");
        map("sign.up",                  "s'inscrire");
        map("sign.succes",              "Vous êtes connecté avec succès!");
        map("sign.retry",               "Refaire ou typ: Stop.");
        map("sign.choise",              "Qu'êtes-vous prêt à faire?");
        map("sign.play",                "Jouer un jeu");
        map("sign.quit",                "Arreter.");
        map("sign.game.conf",           "Configurer un nouveau jeu");
        map("sign.game.modify",         "Modifier un jeu existant");
        map("sign.quitted",             "Quitté");
        
        //register
        map("register.fill.in",         "Remplissez les données suivantes pour vous connecter:");
        map("register.retry",           "Refaire");
        map("register.succes",          "Vous vous avez t'inscrit avec succès:");
        
        //game
        map("game.welcome",             "Bienvenue");
        map("game.play",                "Joue un jeu");
        map("game.choose.list",         "Choisissez un jeu dans la liste ci-dessous");
        map("game.choose",              "Choisissez un jeu");
        map("game.completed",           "Vous avez completé le jeu");
        map("game.notFound",            "Le jeu avec l'id :id n'a pas été trouvé.");
        map("game.exists",              "Il existe déjà un jeu avec ce nom.");
        map("game.notSaved",            "Le jeu n'a pas été enregistré.");
        map("game.modify",              "Modifie un jeu");
        map("game.create",              "Crée nouveau jeu");
        map("game.notCompleted",        "Le jeu n'a aucune tableau complète");
        map("game.new",                 "Nom pour le nouveau jeu");
        map("game.contentsBoards",      "");//"Het spel telt :count voltooid spelbord.|Het spel telt :count voltooide spelborden.");
        map("game.saved",               "");//"Het spel werd opgeslaan met de voltooide spelborden!");
        map("game.name",                ""); //"Naam spel");
        map("game.dialog.save",         ""); //"Wenst u het spel op te slaan?");
        map("game.save",                ""); //"Sla spel op
                
        //gameboard
        map("game.board",               "Tableau");
        map("game.board.loading",       "Le tableau est en train de charger");
        map("game.board.retry",         "Refaire");
        map("game.board.completed",     "Félicitations! Vous avez completé le tableau");
        map("game.board.next",          "tableau suivant");
        map("game.board.moves",         "movements");
        map("game.board.notFound",      "Le tableau avec l'id :id n'a pas été trouvé dans ce jeu.");
        map("game.board.exists",        "Il existe déjà un tableu avec ce nom dans ce jeu.");
        map("game.board.has",           "Le tableau contient :count mec|Le tableau contient :count mecs"); // 1 mannetje | meerdere mannetjes
        map("game.board.mustHavePlayer","Le tableau doit contenir un mec");
        map("game.board.playerLimit",   "Le tableau peut contenir qu'un seul mec");
        map("game.board.boxEquality",   "Le nombre de boîtes et des cibles ne sont pas égaux.");
        map("game.board.noElements",    "Le tableau doit avoir au moins une boîte et une cible.");
        map("game.board.choose.list",   "Choisissez un tableau dans la liste ci-dessous");
        map("game.board.edit.active",   "Actif");
        map("game.board.save",          "Etes-vous sûr que vous voulez enregistrer le plateau de jeu?");
        map("game.board.cancel",        "Vous allez annuler toutes les modifications. Etes-vous sûr?");
        map("game.board.cancelled",     "Le plateau de jeu a été ramené à son état d'origine!");
        map("game.board.saved",         "Le plateau de jeu a été sauvé!");  
        map("game.board.name",          ""); //"Naam spelbord");
        
        
        //player
        map("player.move",              "Déplacer le mec dans une direction");
        map("player.up",                "en haut");
        map("player.down",              "en bas");
        map("player.left",              "à gauche");
        map("player.right",             "à droite");
        map("player.wrongDirection",    "Le direction doit se situer entre :min et :max.");
        map("player.wrongCoordinates",  "Le coordinat ne se situe pas sur le tableau.");
        
        //list
        map("list.choice",              "mon choix");
        map("list.choose",              "Qu'est-ce que vous voulez faire?");
        
        //application
        map("app.quit",                 "arrêter");
        map("app.quited",               "arrêté");
        
        //error
        map("err.login",                "Le nom d'utilisateur ou le mot de passe est incorrect");
        map("err.passwordrepeat",       "Le mot de passe et la repitition de mot de passe ne correspondent pas");
        map("err.usernameDR",           "Le nom d'utilisateur doit être au moins 8 caractères");
        map("err.passwordDR",           "Le mot de passe doit être au moins 8 caractères et doit contenir un majuscule et un chiffre.");
        map("err.integer",              "Il y avait un nombre entier attendue.");
        map("err.nonvalid",             "Choix non valide.");
        map("err.input",                "Le choix doit se situer entre :min et :max.");
        map("err.NaN",                  "Vous n'avez pas entrer un nombre.");
        map("err.invalidType",          "type non valide");
        map("err.noCurrentBoard",       "Ne pouvais pas sélectionner un tableau dans ce jeu.");
        map("err.noGameName",           "Entrez un nom pour le nouveau jeu!");
        
        //keywords
        map("yes",                      "Oui");
        map("no",                       "Non");
        map("cancel",                   "Annuler");
        map("save",                     "Sauver");
        map("delete",                   "Supprimer");
    
    }
}
