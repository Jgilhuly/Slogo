package command.math;

import command.Command;
import command.MathMod;


public class Tan extends MathMod implements Command{
    private double myValue;
    private double myParam;

    public Tan(double param){
        myValue = param;
    }
    
    public void update() {
        myValue = getSin(myParam)/getCos(myParam);
    }

    public double getValue () {
        return myValue;
    } 
}
