package command.math;

import java.util.List;

import command.Command;

public class TangentCommand extends Command {
	public double calculateValue(List<Object> param) {
		return ((double) Math.tan((double) param.get(0) * Math.PI / 180));
	}
}
