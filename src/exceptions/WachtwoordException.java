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
     * @param message String
     */
    public WachtwoordException(String message)
    {
        super(message);
    }

    /**
     * 
     * @param message String
     * @param cause Throwable
     */
    public WachtwoordException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * 
     * @param cause Throwable
     */
    public WachtwoordException(Throwable cause)
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
    public WachtwoordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
