package languages;

/**
 *
 * @author robin
 */
public class EN extends Language 
{
    public EN()
    {
        map("user.username", "username");
        map("user.name", "name");
        map("user.logged.in", "logged in");
        map("user.logged.out", "logged out");
        map("credentials.wrong", "wrong credentials");
    }
}
