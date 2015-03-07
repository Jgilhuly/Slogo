package command.math;

import command.Command;

/**
 * Returns the constant PI
 * @author OWNER
 *
 */
public class PiCommand extends Command {
    public PiCommand(){
        
    }
    public double calculateValue () {
        return Math.PI;
    }
}
