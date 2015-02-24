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
        map("user.logged.in", "ingelogd");
        map("user.logged.out", "uitgelogd");
        map("credentials.wrong", "foute gegevens");
    }
}
