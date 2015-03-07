package command.turtle;



import command.Command;

import model.Turtle;
/*
 * makes turtle visible
 * returns 1
 */
public class ShowTurtleCommand extends Command {
	private Turtle myTurtle;
	
	public ShowTurtleCommand(Turtle t) {
		myTurtle = t;
	}
	
	@Override
	public double calculateValue() {
		myTurtle.setTurtleVisibility(true);
		myTurtle.updateTurtleViewers();
		return 1;
	}
}
