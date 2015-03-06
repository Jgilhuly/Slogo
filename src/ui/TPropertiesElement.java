package ui;

import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TPropertiesElement {

	private VBox myBaseNode;
	private ResourceBundle myResources;
	private TurtleView tView;

	public TPropertiesElement(ResourceBundle resourcesIn, TurtleView tViewIn) {
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

		myBaseNode.getChildren().addAll(penUpDown, turtleVisible, makeTurtleHeadingBox());
	}

	private HBox makeTurtleHeadingBox() {
		HBox turtleHeadingBox = new HBox();
		turtleHeadingBox.setSpacing(5);
		Label turtleHeadingLabel = new Label(myResources.getString("TurtleHeadingLabel"));
		
		Label turtleHeadingValue = new Label("0.0");
		tView.getHeading().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> o, Object oldVal, Object newVal) {
				turtleHeadingValue.setText(newVal.toString());
			}
		});
		
		turtleHeadingBox.getChildren().addAll(turtleHeadingLabel, turtleHeadingValue);
		return turtleHeadingBox;
	}

	public Node getMyBaseNode() {
		return myBaseNode;
	}
}
