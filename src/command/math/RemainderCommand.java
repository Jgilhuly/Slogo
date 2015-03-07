package command.math;

import command.Command;


/**
 * Returns the remainder reuslting from division of op1 by op2
 * 
 * @author OWNER
 *
 */
public class RemainderCommand extends Command {
    private double double1;
    private double double2;

    public RemainderCommand (double op1, double op2) {
        double1 = op1;
        double2 = op2;
    }

    public double calculateValue () {
        return (double) Math.floorMod((long) double1,
                                      (long) double2);
    }
}
