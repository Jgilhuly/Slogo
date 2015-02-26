package command.control;

import java.util.List;
import parser.CommandTreeNode;
import model.Variable;
import command.Command;

public class MakeVariableCommand extends Command {
    public double calculateValue(List<Object> param) {
        List<Variable> variables = (List<Variable>) param.get(2);
        Variable var = variables.get(((Double)param.get(0)).intValue()); 
        var.setValue((double) param.get(1));
        return var.getValue();
    }
}