package command.control;

import java.util.List;

import model.TreeInterpreter;
import parser.CommandTreeNode;
import command.Command;


/**
 * IF expr [ command(s) ]
 * if expr is not 0, runs the command(s) given in the list
returns the value of the final command executed
 * @author GA
 *
 */
public class IfCommand extends Command {

	@Override
	public double calculateValue(List<Object> param) {
        TreeInterpreter interpret = (TreeInterpreter) param.get(2);
        CommandTreeNode expr = (CommandTreeNode) param.get(0); //getting the expression to be evaluated
        CommandTreeNode subCommands = (CommandTreeNode) param.get(1); //getting the commands that execute
        
        interpret.interpretTree(expr);
        int value = (int) expr.getValue();
        if (value != 0) { //execute
            for(int j = 0; j < subCommands.getChildren().size(); j++){
            	interpret.interpretTree(subCommands.getChildren().get(j));
                if(j == (subCommands.getChildren().size()-1)){ //return the last command run
                    return subCommands.getChildren().get(j).getValue();
                }
            }
        }
   
        return 0;
	}
	
}
