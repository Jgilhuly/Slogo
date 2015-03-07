package command.control;

import java.util.List;
import model.TreeInterpreter;
import parser.CommandTreeNode;
import command.Command;


/**
 * tell turtles given in first list to run commands given in the second list
 * returns result of last command run
 * 
 * @author OWNER
 *
 */
public class AskCommand extends Command {
    private TreeInterpreter tree;
    private List<CommandTreeNode> subCommands;
    private List<CommandTreeNode> turtles;

    public AskCommand (CommandTreeNode node1, CommandTreeNode node2, TreeInterpreter t) {
        tree = t;
        turtles = node1.getChildren();
        subCommands = node2.getChildren();
    }

    public double calculateValue () {
        for (int i = 0; i < turtles.size(); i++) {
            tree.setActiveTurtleIndex((int) turtles.get(i).getValue());
            for (int j = 0; j < subCommands.size(); j++) {
                tree.interpretTree(subCommands.get(j));
                if ((i == turtles.size() - 1) && j == (subCommands.size() - 1)) { // return the last command run
                    tree.setActiveTurtleIndex(1); // set the activeTurtleIndex back to 1;
                    return subCommands.get(j).getValue();
                }
            }
        }
        return 0;
    }

}
