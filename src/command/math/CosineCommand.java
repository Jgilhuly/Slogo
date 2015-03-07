package command.math;

import java.util.List;
import command.Command;

public class CosineCommand extends Command {

    
    public CosineCommand(double op1){
        super(op1);
    }

    public double calculateValue(){
        return ((double) Math.cos(Math.toRadians(double1)));
    }
}
