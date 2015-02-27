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
    
    public GebruikersnaamException(String message)
    {
        super(message);
    }

    public GebruikersnaamException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public GebruikersnaamException(Throwable cause)
    {
        super(cause);
    }

    public GebruikersnaamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
