package security;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 *
 * @author robin
 */
public class BCryptTest
{
    private final String password = "foo";
    
    private final String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

    @Test
    public void it_shoud_hash_a_password()
    {
        assertEquals(false, (this.password == null ? this.hashed == null : this.password.equals(this.hashed)));
    }
    
    @Test
    public void it_should_check_a_hashed_password()
    {
        assertEquals(true, BCrypt.checkpw(this.password, this.hashed));
    }
}
