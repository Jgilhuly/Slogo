package command.math;

import java.util.List;
import java.util.Random;
import command.Command;

public class RandomCommand extends Command {
    public RandomCommand(double op1){
        super(op1);
    }
    
    public double calculateValue() {
	Random r = new Random();

	return r.nextInt((int) double1);
    }
}
