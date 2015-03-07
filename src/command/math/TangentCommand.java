package command.math;

import command.Command;

public class TangentCommand extends Command {
<<<<<<< HEAD

=======
>>>>>>> 0d4b48ce23f685cc2b6adbbd8dfb2fefd1fb5a4a
    private double double1;
    
    public TangentCommand(double op1){
        double1 = op1;
    }

    public double calculateValue(){
        return ((double) Math.tan(Math.toRadians(double1)));
    }
<<<<<<< HEAD

=======
>>>>>>> 0d4b48ce23f685cc2b6adbbd8dfb2fefd1fb5a4a
}
