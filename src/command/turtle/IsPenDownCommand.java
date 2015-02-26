package command.turtle;

import java.util.List;

import model.Turtle;
import command.Command;

public class IsPenDownCommand extends Command {
	@Override
	public double calculateValue(List<Object> param) {
		Turtle t = (Turtle) param.get(0);
		if (t.getLine()) {
			return 1;
		}
		return 0;
	}
}
