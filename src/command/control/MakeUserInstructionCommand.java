package command.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.TreeInterpreter;
import parser.CommandTreeNode;

/**
 * STILL A WORKING PROGRESS
 * @author OWNER
 *
 */
public class MakeUserInstructionCommand {
    private String commandName;
    private CommandTreeNode parameters;
    private CommandTreeNode subCommands;
    private TreeInterpreter tree;

    public MakeUserInstructionCommand (CommandTreeNode node1,
                                       CommandTreeNode node2,
                                       CommandTreeNode node3,
                                       TreeInterpreter t) {
        commandName = node1.getName();
        parameters = node2;
        subCommands = node3;
        tree = t;
    }

    public double calculateValue () {
        Map<String, List<CommandTreeNode>> currentMap = tree.getUserCommands();
        List<CommandTreeNode> toAdd = new ArrayList<CommandTreeNode>();
        toAdd.add(parameters);
        toAdd.add(subCommands);
        currentMap.put(commandName, toAdd);
        tree.setUserCommands(currentMap);
        return 1;
    }
}
