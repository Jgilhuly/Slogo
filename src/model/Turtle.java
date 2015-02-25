package model;
import java.util.Observable;

import javafx.scene.paint.Color;

/**
 * Container for anything that draws on the canvas 
 * @author GA
 *
 */
public class Turtle extends Observable {
	private double xLocation;
	private double yLocation;
	private boolean isDrawingLine; //true for pen down, false for pen up
	private boolean isTurtleShowing;
	private Color penColor;
	private double heading; //degrees, 0 is the positive y direction
	
	public Turtle (){
		//set defaults of a new turtle
	    xLocation = 0;
	    yLocation = 0;
	    isDrawingLine = true;
	    isTurtleShowing = true;
	    penColor = Color.BLACK;
	    heading = 0;
	}
	
	public void setHeading(double degrees) {
		heading = degrees;

	}
	
	public void setLine(boolean tf) {
		isDrawingLine = tf; 
		
	}
	
	public void setTurtleVisibility(boolean tf) {
		isTurtleShowing =tf;
		
	}
	public void updateTurtleViewers() {
		setChanged();
		notifyObservers();
	}
	
	public void setXY(double x, double y) {
		xLocation = x;
		yLocation = y;
	}
	
	public double getX() {
		return xLocation;
	}
		
	public double getY() {
		return yLocation;
	}
	public double getHeading() {
		return heading;
	}
}
