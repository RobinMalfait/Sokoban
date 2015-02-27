package exceptions;

/**
 *
 * @author robin
 */
public class GebruikerBestaatException extends RuntimeException
{
    public GebruikerBestaatException()
    {
    }
    
    /**
     * 
     * @param message 
     */
    public GebruikerBestaatException(String message)
    {
        super(message);
    }

    /**
     * 
     * @param message
     * @param cause 
     */
    public GebruikerBestaatException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * 
     * @param cause 
     */
    public GebruikerBestaatException(Throwable cause)
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
    public GebruikerBestaatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
