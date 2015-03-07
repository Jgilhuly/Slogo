package command.control;

import java.util.List;
import model.TreeInterpreter;
import model.Variable;
import parser.CommandTreeNode;
import command.Command;


/**
 * runs command(s) for each value specified in the range, i.e., from (1 - limit) inclusive
 * returns the value of the final command executed
 * 
 * @author CZ
 *
 */
public class DoTimesCommand extends Command {
    private List<CommandTreeNode> parameters;
    private List<CommandTreeNode> subCommands;
    private TreeInterpreter tree;

    public DoTimesCommand (CommandTreeNode node1, CommandTreeNode node2, TreeInterpreter t) {
        parameters = node1.getChildren();
        subCommands = node2.getChildren();
        tree = t;
    }

    public double calculateValue () {
        Variable var = tree.getVariableList().get(parameters.get(0).getName());

        for (int j = 1; j < parameters.size(); j++) {
            tree.interpretTree(parameters.get(j));
        }

        int limit = (int) parameters.get(1).getValue();

        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < subCommands.size(); j++) {
                tree.interpretTree(subCommands.get(j));
            }
            tree.getVariableList().get(var.getName())
                    .setValue(subCommands.get(subCommands.size() - 1).getValue());// Is this a
                                                                                  // correct
                                                                                  // interpretation?
        }
        return var.getValue();
    }

}
