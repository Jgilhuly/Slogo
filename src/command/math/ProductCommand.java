package command.math;

import command.Command;

public class ProductCommand extends Command {

    public ProductCommand (double op1, double op2) {
        super(op1, op2);
    }
    
    public double calculateValue() {
		// (double) Math.multiplyExact((long) (double) param.get(0),
		// (long) (double) param.get(1)) doesn't work for some cases, as decimal
		// points get cut off for long
	return double1 * double2;
    }
}
