package command.turtle;


import model.Turtle;
import command.Command;
/**
 * returns 1 if turtle's pen is down, 0 if it is up
 * @author GA
 *
 */
public class IsPenDownCommand extends Command {
	private Turtle myTurtle;
	
	public IsPenDownCommand(Turtle t) {
		myTurtle = t;
	}
	
	@Override
	public double calculateValue() {
		return (myTurtle.getLine()) ? 1 : 0 ;
	}
}
