package command.control;

import java.util.List;

import model.TreeInterpreter;
import parser.CommandTreeNode;
import command.Command;

/**
 * if expr is not 0, runs the trueCommands given in the first list, 
 * otherwise runs the falseCommands given in the second list
 * returns the value of the final command executed
 * @author GA
 *
 */
public class IfElseCommand extends Command {

	@Override
	public double calculateValue(List<Object> param) {
		// TODO Auto-generated method stub
		return 0;
	}



}
