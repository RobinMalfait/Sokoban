package exceptions;

/**
 *
 * @author robin
 */
public class GebruikersnaamException extends RuntimeException
{
    public GebruikersnaamException()
    {
    }
    
    /**
     * 
     * @param message String
     */
    public GebruikersnaamException(String message)
    {
        super(message);
    }

    /**
     * 
     * @param message String
     * @param cause Throwable
     */
    public GebruikersnaamException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * 
     * @param cause Throwable
     */
    public GebruikersnaamException(Throwable cause)
    {
        super(cause);
    }

    /**
     * 
     * @param message String
     * @param cause Throwable
     * @param enableSuppression boolean
     * @param writableStackTrace boolean
     */
    public GebruikersnaamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
