package command.bool;

import command.Command;

public class OrCommand extends Command{
    private double double1;
    private double double2;

    public OrCommand (double op1, double op2) {
        double1 = op1;
        double2 = op2;
    }

    // returns 1 if test1 or test2 are non-zero, otherwise 0
    public double calculateValue () {
        return (double1 != 0 || double2 != 0) ? 1 : 0; // condition ? value_if_true : value_if_false
    }

}
