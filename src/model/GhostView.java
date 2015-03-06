package model;

import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

public class GhostView {
	
	private ObservableList<Color> penColors;
	private ObservableList<Color> backgroundColors;
	private IntegerProperty penSize;
	private BooleanProperty isDown;
	private IntegerProperty penColorIndex;
	private IntegerProperty backgroundColorIndex;
	

	
	public ObservableList<Color> getPenColors() {
		return penColors;
	}
	public ObservableList<Color> getBackgroundColors() {
		return backgroundColors;
	}
	
	public Property getPenSize() {
		return penSize;
	}
	
	public Property getIsPenDown() {
		return isDown;
	}
	public Property getPenIndex() {
		return penColorIndex;
	}
	public Property getBackgroundColorIndex() {
		return backgroundColorIndex;
	}
	
	//COLOR INDICIES

	//setShape
	

		
	
}
