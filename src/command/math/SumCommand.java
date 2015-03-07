package command.math;

import command.Command;

public class SumCommand extends Command {
    public SumCommand(double op1, double op2){
        super(op1, op2);
    }

    public double calculateValue () {
        return double1 + double2;
    }
}
