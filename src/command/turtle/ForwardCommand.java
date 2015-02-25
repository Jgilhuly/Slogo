package command.turtle;

import java.util.List;

import model.Turtle;
import command.Command;

public class ForwardCommand extends Command {

	public double calculateValue(List<Object> param) {
		updateTurtle((Turtle) param.get(1));
		return (double) param.get(0);
		
	}
	
	private void updateTurtle(Turtle t) {
		double newX = t.getX() * Math.sin(t.getHeading());
		double newY = t.getY() * Math.cos(t.getHeading());
		t.setXY(newX, newY);
		t.updateTurtleViewers();
	}
	
}
