package exceptions;

/**
 *
 * @author robin
 */
public class WachtwoordBevestigingNietCorrectException extends RuntimeException
{
    public WachtwoordBevestigingNietCorrectException()
    {
    }
    
    /**
     * 
     * @param message 
     */
    public WachtwoordBevestigingNietCorrectException(String message)
    {
        super(message);
    }

    /**
     * 
     * @param message
     * @param cause 
     */
    public WachtwoordBevestigingNietCorrectException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * 
     * @param cause 
     */
    public WachtwoordBevestigingNietCorrectException(Throwable cause)
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
    public WachtwoordBevestigingNietCorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
