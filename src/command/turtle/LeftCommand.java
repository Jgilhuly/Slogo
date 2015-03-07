package command.turtle;

import command.Command;
import model.Turtle;


/**
 * turns turtle counterclockwise by degrees angle
 * returns the value of degrees
 * 
 * @author GA
 *
 */
public class LeftCommand extends Command {
    private Turtle myTurtle;
    private double double1;

    public LeftCommand (double op1, Turtle t) {
        double1 = op1;
        myTurtle = t;
    }

    @Override
    public double calculateValue () {
        myTurtle.setHeading(myTurtle.getHeading() - double1);
        myTurtle.updateTurtleViewers();
        return double1;
    }
}
