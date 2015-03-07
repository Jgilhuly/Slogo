package command.turtle;

import model.Turtle;
import command.Command;


/**
 * moves turtle backward in its current heading by pixels distance
 * returns the value of pixels
 * 
 * @author GA
 *
 */

public class BackwardCommand extends Command {
    private double double1;
    private Turtle myTurtle;

    public BackwardCommand (double op1, Turtle t) {
        op1 = double1;
        myTurtle = t;
    }

    @Override
    public double calculateValue () {
        double newX = myTurtle.getX() - double1 * Math.sin(Math.toRadians(myTurtle.getHeading()));
        double newY = myTurtle.getY() - double1 * Math.cos(Math.toRadians(myTurtle.getHeading()));
        myTurtle.setXY(newX, newY);
        myTurtle.updateTurtleViewers();
        return double1;
    }

}
