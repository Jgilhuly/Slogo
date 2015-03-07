package command.bool;

import command.Command;


/**
 * returns 1 if the value of expr1 is strictly less than the value of expr2, otherwise 0
 * 
 * @author GA
 *
 */
public class LessThanCommand extends Command {
    private double double1;
    private double double2;

    public LessThanCommand (double op1, double op2) {
        double1 = op1;
        double2 = op2;
    }

    // returns 1 if the value of expr1 is strictly less than expr2, otherwise 0
    public double calculateValue () {
        return (double1 == double2) ? 1 : 0; // condition ? value_if_true : value_if_false
    }
}
