package command.math;

import java.util.List;
import command.Command;

public class QuotientCommand extends Command {

    public QuotientCommand (double hi, double bye) {
        super(hi, bye);
    }

    public double calculateValue() {
	return double1 / double2;
    }
}
