package command.math;

import java.util.List;
import command.Command;

public class CosineCommand extends Command {
    public CosineCommand(double shit){
        super(shit);
    }
	@Override
	public double calculateValue(List<Object> param) {
		return Math.cos(Math.toRadians((double) param.get(0)));
	}

}
