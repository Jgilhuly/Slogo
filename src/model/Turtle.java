package model;
import java.util.Observable;

/**
 * Container for anything that draws on the canvas 
 * @author GA
 *
 */
public class Turtle extends Observable {
	private double x;
	private double y;
	private boolean isDrawingLine; //true for pen down, false for pen up
	private boolean isTurtleShowing;
	private int heading; //degrees, 0 is the positive y direction
	
	
	public void setHeading(int degrees) {
		heading = degrees;
		setChanged();
		notifyObservers();
	}
	
	public void setLine(boolean tf) {
		isDrawingLine = tf; 
		setChanged();
		notifyObservers();
	}
	
	public void setTurtleVisibility(boolean tf) {
		isTurtleShowing =tf;
		setChanged();
		notifyObservers();
	}
	
	public void setXY(double x, double y) {
		
	}
	
	public double getX() {
		return x;
	}
		
	public double getY() {
		return y;
	}
	public double getHeading() {
		return heading;
	}
}
