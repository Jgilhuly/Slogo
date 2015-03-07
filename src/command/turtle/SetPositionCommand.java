package command.turtle;


import model.Turtle;
import command.Command;
/*
 * moves turtle to an absolute screen position, where (0, 0) is the center of the screen
 * returns the distance turtle moved
 */
public class SetPositionCommand extends Command {
    private Turtle myTurtle;
    private double double1;
    private double double2;
    
    public SetPositionCommand(double op1, double op2, Turtle t){
        double1 = op1;
        double2 = op2;
        myTurtle = t;
    }

	public double calculateValue() {
		myTurtle.setXY(double1, double2);
		myTurtle.updateTurtleViewers();
		return Math.sqrt(Math.pow(myTurtle.getX() - 0, 2) + Math.pow(myTurtle.getY() - 0, 2));
	}
}
