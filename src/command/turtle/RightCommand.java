package command.turtle;

import model.Turtle;
import command.Command;


/*
 * turns turtle clockwise by degrees angle
 * returns the value of degrees
 */
public class RightCommand extends Command {
    private Turtle myTurtle;
    private double double1;

    public RightCommand (double op1, Turtle t) {
        double1 = op1;
        myTurtle = t;
    }

    @Override
    public double calculateValue () {
        myTurtle.setHeading(myTurtle.getHeading() + double1);
        myTurtle.updateTurtleViewers();
        return double1;
    }

}
