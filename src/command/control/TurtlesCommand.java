package command.control;

import model.TreeInterpreter;
import command.Command;

public class TurtlesCommand extends Command {
    private TreeInterpreter tree;

    public TurtlesCommand (TreeInterpreter t) {
        tree = t;
    }
    
    public double calculateValue () {
        return tree.getTurtleList().size();
    }

}
