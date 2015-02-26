package command.math;

import java.util.List;
import command.Command;

public class CosineCommand extends Command {

	@Override
	public double calculateValue(List<Object> param) {
		return Math.cos((double) param.get(0));
	}

}
