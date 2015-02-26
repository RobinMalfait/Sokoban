package exceptions;

/**
 *
 * @author robin
 */
public class WachtwoordException extends RuntimeException
{
    public WachtwoordException()
    {
    }
    
    public WachtwoordException(String message)
    {
        super(message);
    }

    public WachtwoordException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public WachtwoordException(Throwable cause)
    {
        super(cause);
    }

    public WachtwoordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
