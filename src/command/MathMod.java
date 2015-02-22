package command;

import java.util.Random;

//Is this neccessary?...//
public abstract class MathMod {
   
    public double getAtan(double degrees){
        return Math.atan(degrees); 
    }
    
    public double getCos(double degrees){
        return Math.cos(degrees);
    }
    
    public double getSin(double degrees){
        return Math.sin(degrees);
    }
    
    public double getSum(double operand1, double operand2){
        return Math.addExact((long) operand1, (long) operand2);
    }
    
    public double[] divideOp(double dividend, double divisor){
        double[] division = new double[2];
        division[0] = dividend/divisor;
        division[1] = dividend%divisor;
        return division;
    }
    
    public double getMinus(double number){
        return -number;
    }
    
    public double getRandom(double limit){
        Random random = new Random();
        return (double) random.nextInt((int) limit);
    }
    
    public double getLog(double number){
        return Math.log(number);
    }
    
    public double getPower(double base, double exp){
        return Math.pow(base, exp);
    }
    
    public double getPi(){
        return Math.PI;
    }
}
