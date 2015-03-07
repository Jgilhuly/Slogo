package command.math;

import command.Command;


/**
 * Returns the product of two numbers
 * 
 * @author OWNER
 *
 */

public class ProductCommand extends Command {
    private double double1;
    private double double2;

    public ProductCommand (double op1, double op2) {
        double1 = op1;
        double2 = op2;
    }

    public double calculateValue () {
        // (double) Math.multiplyExact((long) (double) param.get(0),
        // (long) (double) param.get(1)) doesn't work for some cases, as decimal
        // points get cut off for long
        return double1 * double2;
    }
}
