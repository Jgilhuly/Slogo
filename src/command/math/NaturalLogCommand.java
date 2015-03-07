package command.math;

import command.Command;

/**
 * Returns the natural log of a number
 * @author OWNER
 *
 */
public class NaturalLogCommand extends Command {
    private double double1;
    
    public NaturalLogCommand(double op1){
        double1 = op1;
    }
    public double calculateValue() {
	return Math.log(double1);
    }
}
