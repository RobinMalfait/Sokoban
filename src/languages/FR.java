package languages;

/**
 *
 * @author robin
 */
public class FR extends Language 
{
    public FR()
    {
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
    }
}


