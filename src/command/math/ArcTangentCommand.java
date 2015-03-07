package command.math;

import command.Command;

/**
 * returns the tangent of arg, given in radians
 * @author 
 *
 */
public class ArcTangentCommand extends Command {
    private double double1;
  
    public ArcTangentCommand(double op1){
        double1 = op1;
    }

    public double calculateValue(){
        return (double) Math.atan(Math.toRadians(Math.toRadians(double1)));
    }
}
