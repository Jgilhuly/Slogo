package command.turtle;

import command.Command;
import model.Turtle;


/**
 * moves turtle to the center of the screen (0,0)
 * returns the distance turtle moved
 *
 */
public class HomeCommand extends Command {
    private Turtle myTurtle;

    private HomeCommand (Turtle t) {
        myTurtle = t;
    }

    public double calculateValue () {
        double distance =
                Math.sqrt(Math.pow(myTurtle.getX() - 0, 2) + Math.pow(myTurtle.getY() - 0, 2));
        myTurtle.setXY(0, 0);
        myTurtle.updateTurtleViewers();
        return distance;

    }

}
