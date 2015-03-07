package command.math;

import command.Command;

public class TangentCommand extends Command {
<<<<<<< HEAD
	
	public double calculateValue(double o1) {
		return  Math.tan(Math.toRadians(o1));
	}


=======
    private double double1;
    
    public TangentCommand(double op1){
        double1 = op1;
    }

    public double calculateValue(){
        return ((double) Math.tan(Math.toRadians(double1)));
    }
>>>>>>> e2d604e384bd08560f3e3ec2461f93eb9b024847
}
