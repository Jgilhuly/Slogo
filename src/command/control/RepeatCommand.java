package command.control;

import java.util.List;
import parser.CommandTreeNode;
import model.TreeInterpreter;
import command.Command;

/**
 * runs command(s) given in the list the value of expr number of times
 * returns the value of the final command executed
 * @author CZ
 *
 */

public class RepeatCommand extends Command {
    private TreeInterpreter tree;
    private CommandTreeNode num;
    private List<CommandTreeNode> subCommands;

    public RepeatCommand (CommandTreeNode node1, CommandTreeNode node2, TreeInterpreter t) {
        tree = t;
        num = node1;
        subCommands = node2.getChildren();
    }

    public double calculateValue () {
        tree.interpretTree(num); // Retrieves the number of times to be traversed
        int numTimes = (int) num.getValue();
        System.out.println(numTimes);
        for (int i = 0; i < numTimes; i++) {
            for (int j = 0; j < subCommands.size(); j++) {
                tree.interpretTree(subCommands.get(j));
                if (i == (numTimes - 1) && j == (subCommands.size() - 1)) { 
                    return subCommands.get(j).getValue(); }
            }
        }
        return 69.0;
    }
}
