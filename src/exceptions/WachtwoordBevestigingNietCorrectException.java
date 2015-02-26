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
    
    public WachtwoordBevestigingNietCorrectException(String message)
    {
        super(message);
    }

    public WachtwoordBevestigingNietCorrectException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public WachtwoordBevestigingNietCorrectException(Throwable cause)
    {
        super(cause);
    }

    public WachtwoordBevestigingNietCorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
