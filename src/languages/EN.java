package languages;

/**
 *
 * @author robin
 */
public class EN extends Language 
{
    public EN()
    {
        map("user.username",            "username");
        map("user.name",                "name");
        map("user.firstname",           "first name");
        map("user.password",            "password");
        map("user.password.repeat",     "repeat password");
        map("user.admin",               "administrator");
        map("user.logged.in",           "logged in");
        map("user.logged.out",          "logged out");
        map("credentials.wrong",        "wrong credentials");
        map("sign.in",                  "sign in");
        map("sign.up",                  "sign up");
    }
}
