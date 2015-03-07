package command.bool;

import command.Command;


public class EqualCommand extends Command {
    private double double1;
    private double double2;

    public EqualCommand (double op1, double op2) {
        double1 = op1;
        double2 = op2;
    }

    // returns 1 if the value of expr1 and the value of expr2 are equal, otherwise 0
    public double calculateValue () {
        return (double1 == double2) ? 1 : 0; // condition ? value_if_true : value_if_false
    }

}
