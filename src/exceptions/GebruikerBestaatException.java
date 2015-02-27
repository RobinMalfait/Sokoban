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
    
    public GebruikerBestaatException(String message)
    {
        super(message);
    }

    public GebruikerBestaatException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public GebruikerBestaatException(Throwable cause)
    {
        super(cause);
    }

    public GebruikerBestaatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
