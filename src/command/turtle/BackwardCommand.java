package command.turtle;

import java.util.List;

import model.Turtle;
import command.Command;

public class BackwardCommand extends Command {

	@Override
	public double calculateValue(List<Object> param) {
		Turtle t = (Turtle) param.get(1);
		double newX = t.getX() - (double) param.get(0) * Math.sin(t.getHeading()*Math.PI/180);
		double newY = t.getY() - (double) param.get(0) * Math.cos(t.getHeading()*Math.PI/180);
		t.setXY(newX, newY);
		t.updateTurtleViewers();
		return (double) param.get(0);
	}
	

}
