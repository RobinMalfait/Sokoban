package languages;

/**
 *
 * @author robin
 */
public class NL extends Language 
{
    public NL()
    {
        map("user.username", "gebruikersnaam");
        map("user.name", "naam");
        map("user.firstname", "voornaam");
        map("user.password", "wachtwoord");
        map("user.password.repeat", "wachtwoordherhaling");
        map("user.logged.in", "ingelogd");
        map("user.logged.out", "uitgelogd");
        map("credentials.wrong", "foute gegevens");
    }
}
