package command.math;

import java.util.List;
import command.Command;

public class SineCommand extends Command {
	public double calculateValue(List<Object> param) {
		return (double) Math.sin(Math.toRadians((double)param.get(0)));
	}
}