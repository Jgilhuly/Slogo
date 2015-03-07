package command.math;

import command.Command;

/**
 * Returns the quotient of two numbers
 * @author OWNER
 *
 */
public class QuotientCommand extends Command {
    private double double1;
    private double double2;
    
    public QuotientCommand(double op1, double op2){
        double1 = op1;
        double2 = op2;
    }

    public double calculateValue() {
	return double1 / double2;
    }
}
