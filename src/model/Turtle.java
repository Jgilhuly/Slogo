package model;
import java.util.Observable;

/**
 * Container for anything that draws on the canvas 
 * @author GA
 *
 */
public class Turtle extends Observable {
	private int x;
	private int y;
	private boolean isDrawingLine; //true for pen down, false for pen up
	private boolean isTurtleShowing;
	private int heading; //degrees, 0 is the positive x direction
	
	
	
	
}
