package command.turtle;



import command.Command;

import model.Turtle;
/*
 * returns the turtle's Y coordinate from the center of the screen
 */
public class YCoordinateCommand extends Command {
	private Turtle myTurtle;
	
	public YCoordinateCommand(Turtle t) {
		myTurtle = t;
	}
	
	@Override
	public double calculateValue() {
		return myTurtle.getY();
	}
}
