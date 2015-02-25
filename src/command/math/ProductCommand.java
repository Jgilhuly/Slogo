package command.math;

import java.util.List;
import command.Command;

public class ProductCommand extends Command {

	public double calculateValue(List<Object> param) {
		return (double) Math.multiplyExact((long) (double) param.get(0),
				(long) (double) param.get(1));
	}
}
