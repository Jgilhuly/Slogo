package command.math;

import command.Command;
import command.MathMod;


public class Sin extends MathMod implements Command{
    private double myValue;
    private double myParam;

    public Sin(double param){
        myValue = param;
    }
    
    public void update() {
        myValue = getSin(myParam);
    }

    public double getValue () {
        return myValue;
    } 
}