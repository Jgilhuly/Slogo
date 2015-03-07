package command.math;

import java.util.List;
import command.Command;

public class ArcTangentCommand extends Command {
    
    public ArcTangentCommand(double op1){
        super(op1);
    }

    public double calculateValue(){
        return (double) Math.atan(Math.toRadians(Math.toRadians(double1)));
    }
}
