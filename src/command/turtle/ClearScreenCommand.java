package command.turtle;

import command.Command;
import model.Turtle;


/**
 * erases turtle's trails and sends it to the home position
 * returns the distance turtle moved
 * 
 * @author GA
 *
 */
public class ClearScreenCommand extends Command {
    private Turtle myTurtle;

    public ClearScreenCommand (Turtle t) {
        myTurtle = t;
    }

    public double calculateValue () {
        double distance =
                Math.sqrt(Math.pow(myTurtle.getX() - 0, 2) + Math.pow(myTurtle.getY() - 0, 2));
        myTurtle.setXY(0, 0);
        myTurtle.setHeading(0);
        myTurtle.setClear(true);
        myTurtle.updateTurtleViewers();
        myTurtle.setClear(false);
        return distance;

    }

}
