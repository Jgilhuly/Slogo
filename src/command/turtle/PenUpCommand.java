package command.turtle;

import java.util.List;

import model.Turtle;
import command.Command;

public class PenUpCommand extends Command {

	@Override
	public double calculateValue(List<Object> param) {
		// for these cases do we just hard code that theres only turtle in there
		// or is there a hierachy?

		Turtle t = (Turtle) param.get(0);
		t.setLine(false);
		t.updateTurtleViewers();
		return 0;
	}

}
