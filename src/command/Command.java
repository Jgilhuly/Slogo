package command;


import java.util.List;


public abstract class Command {
    protected double double1;
    protected double double2;
    
    public Command(){
        //is this empty constructor necessary?
    }
    
    public Command(double num){
        double1 = num;
    }
    
    public Command(double num1, double num2){
        double1 = num1;
        double2 = num2;
    }

//    public abstract double calculateValue(List<Object> param);
    public abstract double calculateValue();
}


