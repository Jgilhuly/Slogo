package command.control;

import java.util.List;
import model.Variable;
import command.Command;

public class MakeVariableCommand {
    public double calculateValue(List<Object> param) {
        Variable var = (Variable) param.get(0);
        Double value = (Double) param.get(1);
        List<Variable> variables = (List<Variable>) param.get(-1);
        var.setValue(value);
        return var.getValue();
    }
}