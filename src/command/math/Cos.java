package command.math;

import command.Command;
import command.MathMod;


public class Cos extends MathMod implements Command{
    private double myValue;
    private double myParam;

    public Cos(double param){
        myValue = param;
    }
    
    public void update() {
        myValue = getCos(myParam);
    }

    public double getValue () {
        return myValue;
    } 
}
