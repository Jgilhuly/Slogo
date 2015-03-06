package ui;

import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;


public class TPropertiesElement {

	private VBox myBaseNode;
	private ResourceBundle myResources;
	private TurtleView tView;

	public TPropertiesElement (ResourceBundle resourcesIn, TurtleView tViewIn) {
		tView = tViewIn;
		myResources = resourcesIn;
		
		init();
	}

	private void init() {
		myBaseNode = new VBox();
		myBaseNode.setSpacing(5);
		
		ToggleButton penUpDown = new ToggleButton(myResources.getString("PenDownToggle"));
		penUpDown.setOnAction(e -> tView.setPen(penUpDown));
		
		ToggleButton turtleVisible = new ToggleButton(myResources.getString("TurtleHideToggle"));
		turtleVisible.setOnAction(e -> tView.setTurtleVisible(turtleVisible));
		
		myBaseNode.getChildren().addAll(penUpDown, turtleVisible);
	}
	
	public Node getMyBaseNode() {
		return myBaseNode;
	}
}
