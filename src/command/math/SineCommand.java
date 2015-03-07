package command.math;

import java.util.List;
import command.Command;

public class SineCommand extends Command {

	
	    public SineCommand(double op1){
	        super(op1);
	    }

	    public double calculateValue(){
	        return ((double) Math.sin(Math.toRadians(double1)));
	    }
}