package errors;

/**
 * An exception for when no input is found
 * 
 * @author Kei Yoshikoshi
 *
 */
public class NoInputFoundException extends SLogoException {
	private static final long serialVersionUID = 1L;

	public NoInputFoundException() {
		super(String.format("No input found!"));

	}

}
