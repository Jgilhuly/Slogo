package command.bool;

import java.util.List;

import command.Command;

public class AndCommand extends Command{

	@Override
	//returns 1 if test1 and test2 are non-zero, otherwise 0
	public double calculateValue(List<Object> param) {
		double test1 = (double) param.get(0);
		double test2 = (double) param.get(1);
		return (test1 != 0 && test2 != 0) ? 1: 0 ; //condition ? value_if_true : value_if_false
		
	}

}
