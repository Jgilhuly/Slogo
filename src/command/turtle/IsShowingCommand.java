package command.turtle;


import command.Command;
import model.Turtle;

/**
 * returns 1 if turtle is showing, 0 if it is hiding
 * @author GA
 *
 */
public class IsShowingCommand extends Command{
	private Turtle myTurtle;
	
	public IsShowingCommand(Turtle t) {
		myTurtle = t;
	}
	
	@Override
	public double calculateValue() {
		return (myTurtle.getVisibility()) ? 1:0;
	}
}
