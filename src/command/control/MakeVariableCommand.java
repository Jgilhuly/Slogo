package command.control;

import java.util.List;
import parser.CommandTreeNode;
import model.Variable;
import model.VariableList;
import model.TreeInterpreter;
import command.Command;

public class MakeVariableCommand extends Command {
    public double calculateValue(List<Object> param) {
        VariableList variables = (VariableList) ((TreeInterpreter) param.get(2)).getVariableList(); 
        
        CommandTreeNode var = (CommandTreeNode) param.get(0);
        CommandTreeNode value = (CommandTreeNode) param.get(1);
        String name = var.getName();
        
        if (!variables.contains(name)){
            variables.add(name);    
        }
        variables.get(name).setValue(value.getValue());
        return variables.get(name).getValue(); //Returns the value of the variable
    }
}