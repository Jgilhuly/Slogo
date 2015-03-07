package command.math;

import command.Command;

public class CosineCommand extends Command {
    private double double1;
    
    public CosineCommand(double op1){
        double1 = op1;
    }

    public double calculateValue(){
        return ((double) Math.cos(Math.toRadians(double1)));
    }
}
