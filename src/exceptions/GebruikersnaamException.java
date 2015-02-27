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
     * @param message 
     */
    public GebruikersnaamException(String message)
    {
        super(message);
    }

    /**
     * 
     * @param message
     * @param cause 
     */
    public GebruikersnaamException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * 
     * @param cause 
     */
    public GebruikersnaamException(Throwable cause)
    {
        super(cause);
    }

    /**
     * 
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace 
     */
    public GebruikersnaamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
