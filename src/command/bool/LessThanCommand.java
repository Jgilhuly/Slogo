package command.bool;

import java.util.List;

import command.Command;
/**
 * returns 1 if the value of expr1 is strictly less than the value of expr2, otherwise 0
 * @author GA
 *
 */
public class LessThanCommand extends Command {
	
	@Override
	public double calculateValue(List<Object> param) {
		double expr1 = (double) param.get(0);
		double expr2 = (double) param.get(1);
		return (expr1<expr2) ? 1: 0 ; //condition ? value_if_true : value_if_false
	}
}
