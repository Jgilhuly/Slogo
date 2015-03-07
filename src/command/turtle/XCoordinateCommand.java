package command.turtle;

import command.Command;
import model.Turtle;


/*
 * returns the turtle's X coordinate from the center of the screen
 */
public class XCoordinateCommand extends Command {
    private Turtle myTurtle;

    public XCoordinateCommand (Turtle t) {
        myTurtle = t;
    }

    @Override
    public double calculateValue () {
        return myTurtle.getX();
    }
}
