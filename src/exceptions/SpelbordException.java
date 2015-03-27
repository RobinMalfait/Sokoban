package exceptions;

/**
 *
 * @author robin
 */
public class SpelbordException extends RuntimeException
{
    public SpelbordException()
    {
    }
    
    /**
     * 
     * @param message String
     */
    public SpelbordException(String message)
    {
        super(message);
    }

    /**
     * 
     * @param message String
     * @param cause Throwable
     */
    public SpelbordException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * 
     * @param cause Throwable
     */
    public SpelbordException(Throwable cause)
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
    public SpelbordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
