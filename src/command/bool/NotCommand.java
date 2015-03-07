package command.bool;

import command.Command;


/**
 * returns 1 if expression is 0; 0 if expression is non-zero
 * 
 * @author GA, CZ
 *
 */

public class NotCommand extends Command {
    private double double1;

    public NotCommand (double op1) {
        double1 = op1;
    }

    // returns 1 if test is 0 and 0 if test is non-zero
    public double calculateValue () {
        return (double1 == 0) ? 1 : 0; // condition ? value_if_true : value_if_false
    }

}
