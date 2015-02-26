package command.turtle;

import java.util.List;

import command.Command;

import model.Turtle;

public class ShowTurtleCommand extends Command {
	private void updateTurtle(Turtle t) {
		t.setTurtleVisibility(true);
		t.updateTurtleViewers();
	}

	@Override
	public double calculateValue(List<Object> param) {
		updateTurtle((Turtle) param.get(0));
		return 1;
	}
}
