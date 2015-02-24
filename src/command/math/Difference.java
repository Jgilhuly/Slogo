package command.math;

import command.Command;
import command.MathMod;


public class Difference extends MathMod implements Command{
    private double myValue;
    private double myParam1;
    private double myParam2;

    public Difference(double operand1, double operand2){
        myParam1 = operand1;
        myParam2 = operand2;
    }
    
    public void update() {
        myValue = getSum(myParam1, myParam2);
    }

    public double getValue () {
        return myValue;
    } 
}