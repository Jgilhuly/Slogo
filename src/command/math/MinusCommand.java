package command.math;

import java.util.List;
import command.Command;

public class MinusCommand extends Command {

	    public MinusCommand (double op1) {
	        super(op1);
	    }
	    
	    public double calculateValue() {
	        return -double1;
	    }
}
