package command.turtle;

import java.util.List;

import command.Command;

import model.Turtle;

public class LeftCommand extends Command{
	@Override
	public double calculateValue(List<Object> param) {
		Turtle t = (Turtle) param.get(1);
		t.setHeading(t.getHeading() - (double) param.get(0));
		t.updateTurtleViewers();
		return (double) param.get(0);
	}
}
