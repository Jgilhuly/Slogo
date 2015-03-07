package util;

import java.util.Observer;

import model.Turtle;
import javafx.beans.property.Property;
public class BindUtility {
	
	public void bindProperties(Property one, Property two ) {
		one.bindBidirectional(two);
	}
	
	public void linkTurtles(Turtle turtleModel, Observer view) {
		turtleModel.addObserver(view);
	}
	
}
