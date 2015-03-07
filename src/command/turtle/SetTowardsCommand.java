package command.turtle;

import model.Turtle;
import command.Command;


/*
 * turns turtle to face the point (x, y), where (0, 0) is the center of the screen
 * returns the number of degrees turtle turned
 */
public class SetTowardsCommand extends Command {
    private Turtle myTurtle;
    private double double1;
    private double double2;

    public SetTowardsCommand (double op1, double op2, Turtle t) {
        double1 = op1;
        double2 = op2;
        myTurtle = t;
    }

    public double calculateValue () {
        double xdiff = double1 - myTurtle.getX();
        double ydiff = double2 - myTurtle.getY();

        Double angle = Math.toDegrees(Math.atan(ydiff / xdiff));
        // third quadrant
        if (xdiff < 0 && ydiff < 0) {
            angle = -(90 + angle);
        }
        // fourth quadrant
        else if (xdiff > 0 && ydiff < 0) {
            angle = 90 - angle;
        }
        myTurtle.setHeading(angle);
        myTurtle.updateTurtleViewers();
        return angle;
    }
}
