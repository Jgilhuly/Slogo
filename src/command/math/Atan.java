package command.math;

import command.Command;
import command.MathMod;

public class Atan extends MathMod implements Command {
    private double myValue;
    private double myParam;

    public Atan(double param){
        myParam= param;
    }
    
    public void update() {
        myValue = getAtan(myParam);
    }

    public double getValue () {
        return myValue;
    } 
}
