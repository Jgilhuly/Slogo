package command.math;

import command.Command;


/**
 * Returns the negative of a number
 * 
 * @author OWNER
 *
 */
public class MinusCommand extends Command {
    private double double1;

    public MinusCommand (double op1) {
        double1 = op1;
    }

    public double calculateValue () {
        return -double1;
    }
}
