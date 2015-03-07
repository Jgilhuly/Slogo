package command.math;

import command.Command;


/**
 * Returns the sine of a number, given in radians
 * 
 * @author OWNER
 *
 */
public class SineCommand extends Command {

    private double double1;

    public SineCommand (double op1) {
        double1 = op1;
    }

    public double calculateValue () {
        return ((double) Math.sin(Math.toRadians(double1)));
    }
}
