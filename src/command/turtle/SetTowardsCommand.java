package command.turtle;

import java.util.List;

import model.Turtle;
import command.Command;

public class SetTowardsCommand extends Command{
	public double calculateValue(List<Object> param) {
		Turtle t = (Turtle) param.get(2);
		double xdiff = (double) param.get(0) - t.getX();
		double ydiff = (double) param.get(1) - t.getY();
		Double angle  = Math.toDegrees(Math.atan(ydiff/xdiff));
		t.setHeading(angle);
		t.updateTurtleViewers();
		return angle;
	}
}
