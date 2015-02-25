package errors;

public class NoInputFoundException extends SLogoException{
private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor that attaches a message of the error
	 * 
	 * @param message
	 */
	public NoInputFoundException(Object ... values) {
		super(String.format("No input found!", values));
	}

    public NoInputFoundException (Throwable cause, String message, Object ... values) {
        super(String.format(message, values));
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public NoInputFoundException (Throwable exception) {
        super(exception);
    }
}
