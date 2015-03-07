package command.math;

import command.Command;


/**
 * Returns the cosine of arg, given in radians
 * 
 * @author OWNER
 *
 */
public class CosineCommand extends Command {
    private double double1;

    public CosineCommand (double op1) {
        double1 = op1;
    }

    public double calculateValue () {
        return ((double) Math.cos(Math.toRadians(double1)));
    }
}
