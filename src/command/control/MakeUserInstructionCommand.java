package command.control;

import java.util.List;
import model.TreeInterpreter;
import model.Variable;
import parser.CommandTreeNode;
import command.Command;


public class MakeUserInstructionCommand {
    private String commandName;
    private List<CommandTreeNode> parameters;
    private List<CommandTreeNode> subCommands;
    private TreeInterpreter tree;

    public MakeUserInstructionCommand (CommandTreeNode node1,
                                       CommandTreeNode node2,
                                       CommandTreeNode node3,
                                       TreeInterpreter t) {
        commandName = node1.getName();
        parameters = node2.getChildren();
        subCommands = node3.getChildren();
        tree = t;
    }

    public double calculateValue (List<Object> param) {
        
        Variable var = tree.getVariableList().get(parameters.get(0).getName());

        for (int j = 1; j < parameters.size(); j++) {
            tree.interpretTree(parameters.get(j));
        }
        int start = (int) parameters.get(1).getValue();
        int end = (int) parameters.get(2).getValue();
        int increment = (int) parameters.get(3).getValue();

        for (int i = start; i < end; i += increment) {
            for (int j = 0; j < subCommands.size(); j++) {
                yay.interpretTree(subCommands.get(j));
            }
            shit.setValue(subCommands.get(subCommands.size() - 1).getValue()); // Is this a correct
                                                                               // interpretation?
        }
        // yay.getVariableList().printThing(); FIX BUG LATER
        return shit.getValue();
    }
}
