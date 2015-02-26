package command.turtle;

import java.util.List;

import model.Turtle;
import command.Command;

public class SetPositionCommand extends Command {
	private static final int ORIGIN_X = 0;
	private static final int ORIGIN_Y = 0;
	//just in case the origin changes
	
	public double calculateValue(List<Object> param) {
		Turtle t = (Turtle) param.get(2);
		t.setXY((double) param.get(0), (double) param.get(1));
		t.updateTurtleViewers();
		return Math.sqrt(Math.pow(t.getX() - ORIGIN_X, 2)
				+ Math.pow(t.getY() - ORIGIN_Y, 2));
	}
}
