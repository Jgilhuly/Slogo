package command.control;

import java.util.List;
import command.Command;
import parser.CommandTreeNode;
import model.TreeInterpreter;
import model.Turtle;

/**
 * sets turtles that will follow commands hereafter
 * returns last value in turtles list
 * @author CZ
 * NOTE: Can only Tell on ONE turtle at this time
 */
public class TellCommand extends Command{
    private TreeInterpreter tree;
    private List<CommandTreeNode> turtles;

    public TellCommand (CommandTreeNode node, TreeInterpreter t) {
        tree = t;
        turtles = node.getChildren();
    }
    
    public double calculateValue () {
        for (int i = 0 ; i < turtles.size() ; i++){
            if(turtles.get(i).getValue() > (double) tree.getTurtleList().size() - 1){
                tree.getTurtleList().add(new Turtle());
            }
            tree.setActiveTurtleIndex((int) turtles.get(i).getValue());
        }
        return (double) tree.getTurtleList().size();
    }

}
