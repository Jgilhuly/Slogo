package command;


import java.util.List;


public abstract class Command {
    protected double double1;
    protected double double2;
    public Command(double hi){
        double1 = hi;
    }
    
    public Command(double hi, double bye){
        double1 = hi;
        double2 = bye;
    }

    public abstract double calculateValue(List<Object> param);
}


