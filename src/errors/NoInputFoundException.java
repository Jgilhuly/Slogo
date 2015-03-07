package errors;

public class NoInputFoundException extends SLogoException{
private static final long serialVersionUID = 1L;
	

	public NoInputFoundException() {
		super(String.format("No input found!"));
		
	}

}
