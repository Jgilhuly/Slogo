package command.turtle;

import model.Turtle;
import command.Command;
/**
 * turns turtle to an absolute heading
 * returns number of degrees moved
 * @author GA
 *
 */
public class HeadingCommand extends Command {
	private Turtle myTurtle;
	
	public HeadingCommand(Turtle t) {
		myTurtle = t;
	}
	
	@Override
	public double calculateValue() {
		return myTurtle.getHeading();
		
	}

}
