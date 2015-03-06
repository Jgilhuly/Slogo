package command.math;

import java.util.List;

import command.Command;

public class TangentCommand extends Command {
	
	public double calculateValue(double o1) {
		return  Math.tan(Math.toRadians(o1));
	}


}
