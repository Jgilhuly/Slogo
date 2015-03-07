package command.math;

import java.util.Random;
import command.Command;

/**
 * Returns random integer generated between 0 and given number
 * @author OWNER
 *
 */
public class RandomCommand extends Command {
    private double double1;

    public RandomCommand (double op1) {
        double1 = op1;
    }

    public double calculateValue () {
        Random r = new Random();
        return r.nextInt((int) double1);
    }
}
