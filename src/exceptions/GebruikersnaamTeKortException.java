package exceptions;

/**
 *
 * @author robin
 */
public class GebruikersnaamTeKortException extends RuntimeException
{
    public GebruikersnaamTeKortException()
    {
    }
    
    public GebruikersnaamTeKortException(String message)
    {
        super(message);
    }

    public GebruikersnaamTeKortException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public GebruikersnaamTeKortException(Throwable cause)
    {
        super(cause);
    }

    public GebruikersnaamTeKortException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
