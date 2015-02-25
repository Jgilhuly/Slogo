package command.math;

import java.util.List;
import command.Command;

public class RemainderCommand extends Command {

	public double calculateValue(List<Object> param) {
		return (double) Math.floorMod((long) (double) param.get(0),
				(long) (double) param.get(1));
	}
}
