package command.math;

import java.util.List;
import command.Command;

public class PowerCommand extends Command {

	public double calculateValue(List<Object> param) {
		return Math.pow((double) param.get(0), (double) param.get(1));
	}
}
