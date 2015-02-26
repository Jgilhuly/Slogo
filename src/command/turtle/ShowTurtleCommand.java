package command.turtle;

import java.util.List;

import command.Command;

import model.Turtle;

public class ShowTurtleCommand extends Command {

	@Override
	public double calculateValue(List<Object> param) {
		Turtle t = (Turtle) param.get(0);
		t.setTurtleVisibility(true);
		t.updateTurtleViewers();
		return 1;
	}
}
