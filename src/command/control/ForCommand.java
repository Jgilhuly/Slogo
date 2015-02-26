package command.control;

import java.util.List;
import model.TreeInterpreter;
import model.Variable;
import parser.CommandTreeNode;
import command.Command;

public class ForCommand extends Command {
    /**
     * TODO: UNDO HACK WAY TO RETRIEVE PARAMETER NODES!!!
     * Current method extracts "second" bracket node from children list of the 1st bracket node
     */
    public double calculateValue(List<Object> param) {
        TreeInterpreter yay = (TreeInterpreter) param.get(1);
        List<CommandTreeNode> parameters = ((CommandTreeNode) param.get(0)).getChildren(); 
        CommandTreeNode bracket = parameters.get(parameters.size() -1);
//        List<CommandTreeNode> subCommands = ((CommandTreeNode) param.get(1)).getChildren();
        List<CommandTreeNode> subCommands = (bracket).getChildren();
        Variable shit = yay.getVariableList().get(parameters.get(0).getName());
        
        for (int j = 1; j < parameters.size(); j++){
            yay.interpretTree(parameters.get(j));
        }
        int start = (int) parameters.get(1).getValue();
        int end = (int) parameters.get(2).getValue();
        int increment = (int) parameters.get(3).getValue();

        for(int i = start; i < end; i+= increment){
            for(int j = 0; j < subCommands.size(); j++){
                yay.interpretTree(subCommands.get(j));
            }
            shit.setValue(subCommands.get(subCommands.size() - 1).getValue()); //Is this a correct interpretation?
        }
//        yay.getVariableList().printThing(); FIX BUG LATER
        return shit.getValue();
    }
}
