package command.bool;


import command.Command;

public class AndCommand extends Command{

    public AndCommand(double op1, double op2){
        super(op1, op2);
    }
	//returns 1 if test1 and test2 are non-zero, otherwise 0
    public double calculateValue(){
	return (double1 != 0 && double2 != 0) ? 1: 0 ; //condition ? value_if_true : value_if_false	
	}

}
