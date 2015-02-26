package command.turtle;

import java.util.List;

import command.Command;

import model.Turtle;

/**
 * resets the turtle's position to 
 * @author GA
 *
 */
public class HomeCommand extends Command{
	private static final int ORIGIN_X = 0;
	private static final int ORIGIN_Y = 0;
	
	public double calculateValue(List<Object> param) {
		Turtle t = (Turtle) param.get(0);
		double distance = Math.sqrt(Math.pow(t.getX() - ORIGIN_X, 2)
				+ Math.pow(t.getY() - ORIGIN_Y, 2));
		t.setXY(ORIGIN_X, ORIGIN_Y);
		t.updateTurtleViewers();
		return distance;

	}
}
