package errors;

/**
 * Error that is thrown when a command has an invalid number of
 * 
 * @author GA
 *
 */
public class IllegalParameterNumberException extends SLogoException {
    private static final long serialVersionUID = 1L;

    public IllegalParameterNumberException () {

        super(String.format("Illegal number of parameters!"));

    }

}
