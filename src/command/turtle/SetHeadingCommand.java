package command.turtle;



import command.Command;
import model.Turtle;

/*
 * turns turtle to an absolute heading
 * returns number of degrees moved
 */

public class SetHeadingCommand extends Command {
    private Turtle myTurtle;
    private double double1;
    
    public SetHeadingCommand(double op1, Turtle t){
        double1 = op1;
        myTurtle = t;
    }
	public double calculateValue() {
		myTurtle.setHeading(double1);
		myTurtle.updateTurtleViewers();
		return double1;
	}
}
