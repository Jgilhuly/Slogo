package command.control;

import java.util.List;
import model.TreeInterpreter;
import parser.CommandTreeNode;
import command.Command;


/**
 * if expr is not 0, runs the trueCommands given in the first list,
 * otherwise runs the falseCommands given in the second list
 * returns the value of the final command executed
 * 
 * @author GA, CZ
 *
 */
public class IfElseCommand extends Command {
    private CommandTreeNode expr;
    private List<CommandTreeNode> subCommandsA;
    private List<CommandTreeNode> subCommandsB;
    private TreeInterpreter tree;

    public IfElseCommand (CommandTreeNode node1,
                          CommandTreeNode node2,
                          CommandTreeNode node3,
                          TreeInterpreter t) {
        expr = node1;
        subCommandsA = node2.getChildren();
        subCommandsB = node3.getChildren();
        tree = t;
    }

    public double calculateValue () {

        tree.interpretTree(expr);
        int value = (int) expr.getValue();
        List<CommandTreeNode> subCommands = (value != 0 ? subCommandsA : subCommandsB);

        for (int j = 0; j < subCommands.size(); j++) {
            tree.interpretTree(subCommands.get(j));
            if (j == (subCommands.size() - 1)) { // return the last command run
                return subCommands.get(j).getValue();
            }
        }

        return 0;
    }
}
