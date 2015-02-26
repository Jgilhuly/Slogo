package command.control;

import java.util.List;
import parser.CommandTreeNode;
import model.Variable;
import model.VariableList;
import command.Command;

public class MakeVariableCommand extends Command {
    public double calculateValue(List<Object> param) {
        VariableList variables = (VariableList) param.get(2); 
        CommandTreeNode node = (CommandTreeNode) param.get(0);
        CommandTreeNode node2 = (CommandTreeNode) param.get(1);
        String name = node.getName();
        if (!variables.contains(name)){
            variables.add(name);    
        }
        variables.get(name).setValue(node2.getValue());
        return variables.get(name).getValue();
    }
}