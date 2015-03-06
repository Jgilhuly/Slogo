package command.math;

import java.util.List;

import command.Command;

public class TangentCommand extends Command {
    
    public TangentCommand(double op1){
        super(op1);
    }

    public double calculateValue(){
        return ((double) Math.tan(Math.toRadians(double1)));
    }
}
