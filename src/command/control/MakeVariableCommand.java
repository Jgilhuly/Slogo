package command.control;

import parser.CommandTreeNode;
import model.VariableList;
import model.TreeInterpreter;
import command.Command;

/**
 * assigns the value of expr to variable, creating the variable if necessary
 * returns expr
 * @author CZ
 *
 */
public class MakeVariableCommand extends Command {
    private VariableList variables;
    private CommandTreeNode var;
    private CommandTreeNode value;
    
    public MakeVariableCommand(CommandTreeNode node1, CommandTreeNode node2, TreeInterpreter tree){
        variables = tree.getVariableList();
        var = node1;
        value = node2;
    }
    public double calculateValue() {
        String name = var.getName();
        
        if (!variables.contains(name)){
            variables.add(name);    
        }
        variables.get(name).setValue(value.getValue());
        return variables.get(name).getValue(); //Returns the value of the variable
    }
}