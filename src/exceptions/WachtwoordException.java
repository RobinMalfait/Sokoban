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
    
    /**
     * 
     * @param message 
     */
    public WachtwoordException(String message)
    {
        super(message);
    }

    /**
     * 
     * @param message
     * @param cause 
     */
    public WachtwoordException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * 
     * @param cause 
     */
    public WachtwoordException(Throwable cause)
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
    public WachtwoordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
