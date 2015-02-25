package command.turtle;

import java.util.List;

import model.Turtle;
import command.Command;

public class HeadingCommand extends Command {

	@Override
	public double calculateValue(List<Object> param) {
		Turtle t = (Turtle) param.get(0);
		return t.getHeading();
		
	}

}
