package command.turtle;

import model.Turtle;
import command.Command;
/**
 * moves turtle forward in its current heading by pixels distance
 * returns the value of pixels
 * @author GA
 *
 */
public class ForwardCommand extends Command {
    private Turtle myTurtle;
    private double double1;
    
    public ForwardCommand(double op1, Turtle t){
        double1 = op1;
        myTurtle = t;
    }
    
    public double calculateValue() {
		double newX = myTurtle.getX() + double1 * Math.sin(myTurtle.getHeading() * Math.PI / 180);
		double newY = myTurtle.getY() + double1 * Math.cos(myTurtle.getHeading() * Math.PI / 180);
		myTurtle.setXY(newX, newY);
		myTurtle.updateTurtleViewers();
		return double1;
	}

}
