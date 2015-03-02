package command.math;

import java.util.List;

import command.Command;

public class TangentCommand extends Command {
	public double calculateValue(List<Object> param) {
		return ((double) Math.tan(Math.toRadians((double) param.get(0))));
	}
}
