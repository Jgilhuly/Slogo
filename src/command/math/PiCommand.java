package command.math;

import java.util.List;
import command.Command;

public class PiCommand extends Command {

	public double calculateValue(List<Object> param) {
		return Math.PI;
	}
}
