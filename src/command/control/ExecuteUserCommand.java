package command.control;

import java.util.List;
import parser.CommandTreeNode;
import model.TreeInterpreter;
import command.Command;

/**
 * Class that will handle execution of all user-defined commands
 * @author OWNER
 *
 */
public class ExecuteUserCommand extends Command{
    private TreeInterpreter tree;
    private String commandName;
    public ExecuteUserCommand(String name, TreeInterpreter t){
        commandName = name;
        tree = t;
    }

    public double calculateValue () {
        List<CommandTreeNode> properties = tree.getUserCommands().get(commandName);
        List<CommandTreeNode> subCommands = properties.get(1).getChildren();
        for(int i = 0; i < subCommands.size() ; i++){
            tree.interpretTree(subCommands.get(i));
            if (i == (subCommands.size() - 1)) { // return the last command run
                return subCommands.get(i).getValue();
            }
        }
        return 0;
    }
}
