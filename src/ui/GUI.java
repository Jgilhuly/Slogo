package ui;

import java.util.*;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI {

	public static final String DEFAULT_RESOURCE_PACKAGE = "resources.displayText/";
	private static final String DEFAULT_TURTLE_IMAGE_PATH = "/resources/images/turtleImage.png";

	private ResourceBundle myResources; // for node text/names
	private Color DEFAULT_BACKGROUND = Color.FUCHSIA;
	private Scene myScene;
	private Stage myStage;
	private BorderPane myRoot;
	private SceneUpdater mySceneUpdater;
	private TurtleView tView;
	private Pen myPen;

	private IOElement ioPane;
	private CanvasElement canvasPane;
	private InfoElement infoPane;
	private MenuBarElement menuPane;
	private TPropertiesElement propertiesPane;

	private String DEFAULT_LANG = "English";
	private String[] languages = { "Chinese", "English", "French", "German",
			"Italian", "Japanese", "Korean", "Portuguese", "Russian", "Spanish" };

	// private List<String> variables;

	public GUI(Stage stageIn, SceneUpdater sceneUpIn) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE
				+ "English");
		myStage = stageIn;
		mySceneUpdater = sceneUpIn;
	}

	/**
	 * Returns scene for this view so it can be added to stage.
	 */
	public void initialize() {

		myStage.setTitle(myResources.getString("Title"));
		myRoot = new BorderPane();

		ioPane = new IOElement(myResources, this);
		myRoot.setBottom(ioPane.getBaseNode());

		canvasPane = new CanvasElement(DEFAULT_BACKGROUND, myStage.getWidth(),
				myStage.getHeight());
		myRoot.setCenter(canvasPane.getBaseNode());

		infoPane = new InfoElement();
		myRoot.setRight(infoPane.getBaseNode());

		myPen = new Pen(canvasPane.getCanvas(), Color.BLUE, true, this);
		tView = makeTurtleView(DEFAULT_TURTLE_IMAGE_PATH);

		propertiesPane = new TPropertiesElement(myResources, tView, myPen);
		myRoot.setLeft(propertiesPane.getMyBaseNode());

		menuPane = new MenuBarElement(myResources, canvasPane, ioPane, tView,
				DEFAULT_BACKGROUND, languages, DEFAULT_LANG, myStage, myPen, this);
		myRoot.setTop(menuPane.getBaseNode());

		myScene = new Scene(myRoot, myStage.getWidth(), myStage.getHeight());
		setupKeyboardCommands();
		myStage.setScene(myScene);
	}

	/**
	 * Makes and returns a TurtleView object
	 * 
	 * @param imagePath
	 *            : The path of the Turtle Image
	 * @return
	 */
	private TurtleView makeTurtleView(String imagePath) {
		Image turtleImage = new Image(GUI.class.getResourceAsStream(imagePath));

		return new TurtleView(turtleImage, canvasPane.getCanvas(), canvasPane
				.getCanvas().getWidth() / 2,
				canvasPane.getCanvas().getHeight() / 2,
				canvasPane.getBaseNode(), myPen);
	}

	/**
	 * Set up keyboard commands
	 */
	private void setupKeyboardCommands() {
		myScene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER)
				parseCommand();
		});
	}

	/**
	 * Sends a string slogo command through the Scene Updater and Controller,
	 * all the way to the back-end.
	 */
	public void parseCommand() {
		IOElement ioPaneCasted = (IOElement) ioPane;
		if (ioPaneCasted.getInputField().getText() != null)
			mySceneUpdater.sendCommands(ioPaneCasted.getInputField().getText(),
					menuPane.getSelectedLanguage());
		addHistory(); // for previous commands tab
		ioPaneCasted.getInputField().setText("");
	}

	/**
	 * Sets the output text field. Called from back-end (through controller)
	 * 
	 * @param outputText
	 */
	public void setOutputText(String outputText) {
		ioPane.getOutputField().setText(outputText);
	}

	/**
	 * Returns the TurtleView so that it can be linked to its model
	 * 
	 * @return
	 */
	public Observer getTurtleView() {
		return tView;
	}

	public void bindTable(String type, ObservableList<String> l) {
		List<TableView<String>> tables = infoPane.getTables();
		if (type.equals("Commands")) {
			tables.get(1).setItems(l);
		} else if (type.equals("User Commands")) {
			tables.get(2).setItems(l);
		}
	}

	public void createNewWorkspace() {
		mySceneUpdater.createNewWorkspace();
	}

	/**
	 * Updates the right side info tables
	 */
	private void addHistory() {
	}
}