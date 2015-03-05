package command.math;

import java.util.List;
import command.Command;

public class QuotientCommand extends Command {

	public QuotientCommand (double hi, double bye) {
        super(hi, bye);
    }

    public double calculateValue(List<Object> param) {
		return (double) param.get(0) / (double) param.get(1);
	}
}
