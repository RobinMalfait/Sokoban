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
     * @param message String
     */
    public GebruikerBestaatException(String message)
    {
        super(message);
    }

    /**
     * 
     * @param message String
     * @param cause Throwable
     */
    public GebruikerBestaatException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * 
     * @param cause Throwable
     */
    public GebruikerBestaatException(Throwable cause)
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
    public GebruikerBestaatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
