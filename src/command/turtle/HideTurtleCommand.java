package command.turtle;


import model.Turtle;
import command.Command;

/**
 * makes turtle invisible
 * returns 0
 * @author GA
 *
 */
public class HideTurtleCommand extends Command {
	private Turtle myTurtle;
	
	public HideTurtleCommand(Turtle t) {
		myTurtle = t;
	}
	
	@Override
	public double calculateValue() {
		myTurtle.setTurtleVisibility(false);
		myTurtle.updateTurtleViewers();
		return 0;
	}

}






