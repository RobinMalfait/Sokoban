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
     * @param message String
     */
    public WachtwoordBevestigingNietCorrectException(String message)
    {
        super(message);
    }

    /**
     * 
     * @param message String
     * @param cause Throwable
     */
    public WachtwoordBevestigingNietCorrectException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * 
     * @param cause Throwable
     */
    public WachtwoordBevestigingNietCorrectException(Throwable cause)
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
    public WachtwoordBevestigingNietCorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
