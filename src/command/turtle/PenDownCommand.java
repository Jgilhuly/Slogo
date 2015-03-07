package command.turtle;

import command.Command;
import model.Turtle;


/*
 * puts pen down such that when the turtle moves, it leaves a trail
 * returns 1
 */
public class PenDownCommand extends Command {
    private Turtle myTurtle;

    public PenDownCommand (Turtle t) {
        myTurtle = t;
    }

    @Override
    public double calculateValue () {
        myTurtle.setLine(true);
        myTurtle.updateTurtleViewers();
        return 1;
    }
}
