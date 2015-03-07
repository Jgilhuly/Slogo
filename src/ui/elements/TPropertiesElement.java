package ui.elements;

import java.util.ResourceBundle;

import ui.Pen;
import ui.TurtleView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TPropertiesElement {

	private VBox myBaseNode;
	private ResourceBundle myResources;
	private TurtleView tView;
	private Pen myPen;

	public TPropertiesElement(final ResourceBundle resourcesIn, TurtleView tViewIn,
			Pen penIn) {
		tView = tViewIn;
		myResources = resourcesIn;
		myPen = penIn;

		init();
	}

	private void init() {
		myBaseNode = new VBox();
		myBaseNode.setSpacing(5);

		ToggleButton penUpDown = makeToggleButton(myResources.getString("PenDownToggle"), null, null);
		penUpDown.setOnAction(e -> myPen.setPenIsDown(!penUpDown.isSelected()));

		ToggleButton turtleVisible = makeToggleButton(myResources.getString("TurtleHideToggle"), null, null);
		turtleVisible.setOnAction(e -> tView.setTurtleVisible(turtleVisible.isSelected()));

		ToggleGroup lineStyleGroup = new ToggleGroup();
		ToggleButton normal = makeToggleButton(myResources.getString("NormalLine"), e -> myPen.setLineStyle("normal"), lineStyleGroup);
		ToggleButton dashed = makeToggleButton(myResources.getString("DashedLine"), e -> myPen.setLineStyle("dashed"), lineStyleGroup);
		ToggleButton dotted = makeToggleButton(myResources.getString("DottedLine"), e -> myPen.setLineStyle("dotted"), lineStyleGroup);

		myBaseNode.getChildren().addAll(penUpDown, turtleVisible, makeTurtleHeadingBox(), normal, dashed, dotted);
	}

	private ToggleButton makeToggleButton(String property,
			EventHandler<ActionEvent> handler, ToggleGroup myGroup) {
		ToggleButton result = new ToggleButton();
		result.setText(property);
		if (handler != null) {			
			result.setOnAction(handler);
		}
		if (myGroup != null) {
			result.setToggleGroup(myGroup);			
		}
		return result;
	}

	private HBox makeTurtleHeadingBox() {
		HBox turtleHeadingBox = new HBox();
		turtleHeadingBox.setSpacing(5);
		Label turtleHeadingLabel = new Label(
				myResources.getString("TurtleHeadingLabel"));

		Label turtleHeadingValue = new Label("0.0");
		tView.getHeading().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> o, Object oldVal,
					Object newVal) {
				turtleHeadingValue.setText(newVal.toString());
			}
		});

		turtleHeadingBox.getChildren().addAll(turtleHeadingLabel,
				turtleHeadingValue);
		return turtleHeadingBox;
	}

	public Node getMyBaseNode() {
		return myBaseNode;
	}
}
