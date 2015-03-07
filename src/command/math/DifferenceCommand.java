package command.math;

import java.util.List;
import command.Command;

public class DifferenceCommand extends Command {
    public DifferenceCommand(double op1, double op2){
        super(op1, op2);
    }

    public double calculateValue () {
        return double1 - double2;
    }
}