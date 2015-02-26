package command.turtle;

import java.util.List;

import model.Turtle;
import command.Command;

public class RightCommand extends Command{

	@Override
	public double calculateValue(List<Object> param) {
		Turtle t = (Turtle) param.get(1);
		t.setHeading(t.getHeading() + (double) param.get(0));
		t.updateTurtleViewers();
		return (double) param.get(0);
	}

}
