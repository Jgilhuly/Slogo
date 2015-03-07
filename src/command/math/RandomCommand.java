package command.math;

import java.util.Random;
import command.Command;


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
