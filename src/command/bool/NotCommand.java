package command.bool;

import java.util.List;

import command.Command;

public class NotCommand extends Command {

	@Override
	//returns 1 if test is 0 and 0 if test is non-zero
	public double calculateValue(List<Object> param) {
		double test = (double) param.get(0);
		return (test == 0 ) ? 1: 0 ; //condition ? value_if_true : value_if_false
	}

}
