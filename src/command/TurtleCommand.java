package command;

import model.Turtle;

public abstract class TurtleCommand extends Command {
	//method that updates the turtle accordingly, will be called in calculate value?
	public abstract void updateTurtle(Turtle t);
}
