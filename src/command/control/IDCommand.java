package command.control;

import model.TreeInterpreter;
import model.Turtle;
import command.Command;

/**
 * returns number of turtles created so far (i.e., the ID of the last turtle)
 * @author CZ
 *
 */
public class IDCommand extends Command {
    private TreeInterpreter tree;

    public IDCommand (TreeInterpreter t) {
        tree = t;
    }
    
    public double calculateValue () {
        return (double) tree.getTurtleList().size();
    }

}
