package command.math;

import command.Command;

public class PowerCommand extends Command {
    
    public PowerCommand (double op1, double op2) {
        super(op1, op2);
    }
	
    public double calculateValue() {
        
	return Math.pow(double1, double2);
	
    }
}
