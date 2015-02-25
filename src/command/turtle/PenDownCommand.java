package command.turtle;

import java.util.List;

import command.Command;

import model.Turtle;

public class PenDownCommand extends Command{
	@Override
	public double calculateValue(List<Object> param) {
		Turtle t = (Turtle) param.get(0);
		t.setLine(true);
		t.updateTurtleViewers();
		return 1;
	}
}
