package command.math;

import java.util.List;
import command.Command;

public class SumCommand extends Command {

	public double calculateValue(List<Object> param) {
		return (double) param.get(0) + (double) param.get(1);
	}
}
