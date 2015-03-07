package command.math;

import java.util.List;
import command.Command;


public class MinusCommand extends Command {
    private double double1;
    
    public MinusCommand(double op1){
        double1 = op1;
    }

    public double calculateValue () {
        return -double1;
    }
}
