package persistentie;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author robin
 */
public class ConnectieTest
{
    
    @Before
    public void setUp() throws SQLException
    {
        Connectie conn1 = new Connectie1Impl();
        Connectie conn2 = new Connectie2Impl();
        
        Connection con1 = conn1.getConnection();
        Connection con2 = conn2.getConnection();
    }

    @Test
    public void testGetConnection() throws Exception
    {
        assertEquals(1, Connectie.connections);
    }

    public class Connectie1Impl extends Connectie
    {
        
    }
    
    public class Connectie2Impl extends Connectie
    {
        
    }
    
}
