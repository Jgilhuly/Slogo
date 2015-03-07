package command.turtle;


import model.Turtle;
import command.Command;
/*
 * puts pen up such that when the turtle moves, it does not leave a trail
 * returns 0
 */
public class PenUpCommand extends Command {
	private Turtle myTurtle;
	
	public PenUpCommand(Turtle t) {
		myTurtle = t;
	}
	@Override
	public double calculateValue() {
		myTurtle.setLine(false);
		myTurtle.updateTurtleViewers();
		return 0;
	}

}
