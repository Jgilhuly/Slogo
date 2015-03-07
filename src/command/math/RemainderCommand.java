package command.math;

import command.Command;

public class RemainderCommand extends Command {

	
	public RemainderCommand(double op1, double op2){
    	        super(op1, op2);
    	    }
	
        public double calculateValue(){
    	    return (double) Math.floorMod((long) double1,
    	                                (long) double2);
    	    }
}
