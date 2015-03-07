package command.math;

import command.Command;


/**
 * Returns the tangent of a given number, in radians
 * 
 * @author OWNER
 *
 */
public class TangentCommand extends Command {
    private double double1;

    public TangentCommand (double op1) {
        double1 = op1;
    }

    public double calculateValue () {
        return ((double) Math.tan(Math.toRadians(double1)));
    }

}
