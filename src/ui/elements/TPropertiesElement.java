package ui.elements;

import java.io.File;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ui.GUI;
import ui.Pen;
import ui.TurtleView;
import util.ButtonMaker;

public class TPropertiesElement {

	private VBox myBaseNode;
	private ResourceBundle myResources;
	private TurtleView tView;
	private Pen myPen;
	private Stage myStage;
	private GUI myGui;

	public TPropertiesElement(final ResourceBundle resourcesIn, TurtleView tViewIn,
			Pen penIn, Stage stageIn, GUI guiIn) {
		tView = tViewIn;
		myResources = resourcesIn;
		myPen = penIn;
		myStage = stageIn;
		myGui = guiIn;
		init();
	}

	private void init() {
		ButtonMaker bm = new ButtonMaker();
		myBaseNode = new VBox();
		myBaseNode.setSpacing(5);

		ToggleButton penUpDown = bm.makeToggleButton(myResources.getString("PenDownToggle"), null, null);
		penUpDown.setOnAction(e -> myPen.setPenIsDown(!penUpDown.isSelected()));

		VBox turtleButtons = new VBox();
		ToggleButton turtleVisibleButton = bm.makeToggleButton(myResources.getString("TurtleHideToggle"), null, null);
		turtleVisibleButton.setOnAction(e -> tView.setTurtleVisible(turtleVisibleButton.isSelected()));
		Button chooseNewTurtleImageButton = bm.makeButton(myResources.getString("ChooseTurtleImage"), e -> selectAndChangeTurtleImage());
		Button createNewTurtleButton = bm.makeButton(myResources.getString("CreateNewTurtle"), e -> myGui.makeTurtleView(GUI.DEFAULT_TURTLE_IMAGE_PATH));
		turtleButtons.getChildren().addAll(turtleVisibleButton, chooseNewTurtleImageButton, createNewTurtleButton);

		ToggleGroup lineStyleGroup = new ToggleGroup();
		ToggleButton normal = bm.makeToggleButton(myResources.getString("NormalLine"), e -> myPen.setLineStyle("normal"), lineStyleGroup);
		ToggleButton dashed = bm.makeToggleButton(myResources.getString("DashedLine"), e -> myPen.setLineStyle("dashed"), lineStyleGroup);
		ToggleButton dotted = bm.makeToggleButton(myResources.getString("DottedLine"), e -> myPen.setLineStyle("dotted"), lineStyleGroup);
		

		myBaseNode.getChildren().addAll(penUpDown, turtleButtons, makeTurtleHeadingBox(), normal, dashed, dotted);
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
	
	/**
	 * Shows a filechooser to select a new turtle image, then changes to that
	 * image
	 */
	private void selectAndChangeTurtleImage() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(myResources.getString("OpenResourceFile"));
		fileChooser.getExtensionFilters().add(
				new FileChooser.ExtensionFilter("PNG File", "*.png"));

		File file = fileChooser.showOpenDialog(myStage);
		if (file != null) {
			tView.setImage(new Image(file.toURI().toString()));
		} else {
			System.err.println("Error Loading Image File");
			// TODO: MAKE SLOGO EXCEPTION POPUP
		}
	}

	public Node getMyBaseNode() {
		return myBaseNode;
	}
}
