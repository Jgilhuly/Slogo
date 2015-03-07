package command.math;

import command.Command;

public class NaturalLogCommand extends Command {
    public NaturalLogCommand (double op1) {
        super(op1);
    }
    
    public double calculateValue() {
	return Math.log(double1);
    }
}
