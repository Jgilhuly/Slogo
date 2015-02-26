package command.turtle;

import java.util.List;

import command.Command;

import model.Turtle;

public class ClearScreenCommand extends Command {
	public double calculateValue(List<Object> param) {
		Turtle t = (Turtle) param.get(0);
		double distance = Math.sqrt(Math.pow(t.getX() - ORIGIN_X, 2)
				+ Math.pow(t.getY() - ORIGIN_Y, 2));
		t.setXY(ORIGIN_X, ORIGIN_Y);
		t.setHeading(ORIGIN_HEADING);
		t.setClear(true);
		t.updateTurtleViewers();
		t.setClear(false);
		return distance;

	}
}
