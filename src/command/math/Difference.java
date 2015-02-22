package command.math;

import command.Command;
import command.MathMod;


public class Difference extends MathMod implements Command{
    private double myValue;
    private double myParam;

    public Difference(double operand1, double operand 2){
        myValue = param;
    }
    
    public void update() {
        myValue = getSum(myParam);
    }

    public double getValue () {
        return myValue;
    } 
}