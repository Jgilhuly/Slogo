package errors;

/**
 * Exception for different number of brackets
 * 
 * @author Kei Yoshikoshi
 *
 */
public class UnmatchedBracketException extends SLogoException {

	private static final long serialVersionUID = 1L;

	public UnmatchedBracketException() {
		super(String.format("The number of '[' and ']' do not match."));
	}

}
