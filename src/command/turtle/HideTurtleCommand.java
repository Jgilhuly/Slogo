package command.turtle;

import java.util.List;

import model.Turtle;
import command.Command;


public class HideTurtleCommand extends Command {
	
	private void updateTurtle(Turtle t) {
		t.setTurtleVisibility(false);
		t.updateTurtleViewers();
	}

	@Override
	public double calculateValue(List<Object> param) {
		updateTurtle((Turtle) param.get(0));
		return 0;
	}





}
