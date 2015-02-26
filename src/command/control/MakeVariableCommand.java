package command.control;

import java.util.List;
import parser.CommandTreeNode;
import model.Variable;
import model.VariableList;
import command.Command;

public class MakeVariableCommand extends Command {
    public double calculateValue(List<Object> param) {
        VariableList variables = (VariableList) param.get(2); 

        Variable var = variables.get(((Double)param.get(0)));  //Retrieval of the specific variable from variableList
        var.setValue((double) param.get(1));
        
        
        return var.getValue();
    }
    
    /**
     * 
     * @param name  , the name of the Variable
     * @param variables , the current list of existing variables
     * @return index of the newly created Variable in the variables list
     */
//        public Double createVariable (String name, List<Variable> variables) {
//            Variable var = new Variable(name, 0.0);
//            (variables).add(var);
//            return (double) variables.indexOf(var);
//        }
//        private Variable checkContains (){
//            for (Variable var : variables){
//                if(var.getName() == )
//            }
//        }
}