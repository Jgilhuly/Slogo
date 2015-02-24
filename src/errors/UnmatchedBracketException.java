package errors;


public class UnmatchedBracketException extends SLogoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnmatchedBracketException(Object ... values) {
		super(String.format("The number of '[' and ']' do not match.", values));
	}

    public UnmatchedBracketException (Throwable cause, String message, Object ... values) {
        super(String.format(message, values));
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public UnmatchedBracketException (Throwable exception) {
        super(exception);
    }

}
