package errors;

public class UnmatchedBracketException extends SLogoException {

    private static final long serialVersionUID = 1L;

    public UnmatchedBracketException () {
        super(String.format("The number of '[' and ']' do not match."));
    }

}
