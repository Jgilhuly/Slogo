package command.math;

import java.util.List;
import command.Command;

public class SumCommand extends Command {
    public SumCommand(double hi, double bye){
        super(hi, bye);
    }
	public double calculateValue(List<Object> param) {
		return double1 + double2;
	}
}
