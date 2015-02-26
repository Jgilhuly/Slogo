package command.math;

import java.util.List;
import command.Command;

public class ProductCommand extends Command {

	public double calculateValue(List<Object> param) {
		// (double) Math.multiplyExact((long) (double) param.get(0),
		// (long) (double) param.get(1)) doesn't work for some cases, as decimal
		// points get cut off for long
		return (double) param.get(0) * (double) param.get(1);
	}
}
