package command.bool;

import java.util.List;

import command.Command;

public class NotEqualCommand extends Command {

	@Override
	//returns 1 if the value of expr1 and the value of expr2 are not equal, otherwise 0
	public double calculateValue(List<Object> param) {
		double expr1 = (double) param.get(0);
		double expr2 = (double) param.get(1);
		return (expr1 != expr2) ? 1: 0 ; //condition ? value_if_true : value_if_false
	}

}
