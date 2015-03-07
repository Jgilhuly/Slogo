package command.math;

import command.Command;

public class DifferenceCommand extends Command {
    private double double1;
    private double double2;
    
    public DifferenceCommand(double op1, double op2){
        double1 = op1;
        double2 = op2;
    }

    public double calculateValue () {
        return double1 - double2;
    }
}