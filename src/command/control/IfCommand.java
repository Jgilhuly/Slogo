package command.control;

import java.util.List;
import model.TreeInterpreter;
import parser.CommandTreeNode;
import command.Command;


/**
 * IF expr [ command(s) ]
 * if expr is not 0, runs the command(s) given in the list
 * returns the value of the final command executed
 * 
 * @author GA
 *
 */
public class IfCommand extends Command {
    private CommandTreeNode expr;
    private List<CommandTreeNode> subCommands;
    private TreeInterpreter tree;

    public IfCommand (CommandTreeNode node1, CommandTreeNode node2, TreeInterpreter t) {
        expr = node1;
        subCommands = node2.getChildren();
        tree = t;
    }

    public double calculateValue () {

        tree.interpretTree(expr);
        int value = (int) expr.getValue();
        if (value != 0) { // execute
            for (int j = 0; j < subCommands.size(); j++) {
                tree.interpretTree(subCommands.get(j));
                if (j == (subCommands.size() - 1)) { // return the last command run
                    return subCommands.get(j).getValue();
                }
            }
        }

        return 0;
    }

}
