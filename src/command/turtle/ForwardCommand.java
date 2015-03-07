package command.turtle;

import java.util.List;

import model.Turtle;
import command.Command;

public class ForwardCommand extends Command {
    private Turtle myTurtle;
    
    public ForwardCommand(double op1, Turtle turtle){
        super(op1);
        myTurtle = turtle;
    }
    
    public double calculateValue() {
	Turtle t = myTurtle;
	double newX = t.getX() + double1 * Math.sin(t.getHeading()*Math.PI/180);
	double newY = t.getY() + double1 * Math.cos(t.getHeading()*Math.PI/180);
	t.setXY(newX, newY);
	t.updateTurtleViewers();
	return double1;	
    }
	
}
