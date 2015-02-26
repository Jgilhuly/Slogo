package command.bool;

import java.util.List;

import command.Command;

public class OrCommand extends Command{

	@Override
	//returns 1 if test1 or test2 are non-zero, otherwise 0
	public double calculateValue(List<Object> param) {
		double expr1 = (double) param.get(0);
		double expr2 = (double) param.get(1);
		return (expr1 !=0 || expr2 != 0) ? 1: 0 ; //condition ? value_if_true : value_if_false
	}

}
