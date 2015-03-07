package ui;

import java.util.*;

import ui.elements.CanvasElement;
import ui.elements.IOElement;
import ui.elements.InfoElement;
import ui.elements.MenuBarElement;
import ui.elements.TPropertiesElement;
import ui_table.TableElements;
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
	public static final String DEFAULT_TURTLE_IMAGE_PATH = "/resources/images/turtleImage.png";
	private Color DEFAULT_BACKGROUND = Color.FUCHSIA;

	private ResourceBundle myResources; // for node text/names
	private Scene myScene;
	private Stage myStage;
	private BorderPane myRoot;
	private SceneUpdater mySceneUpdater;
	private List<TurtleView> turtleViews;
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

		infoPane = new InfoElement(this);
		myRoot.setRight(infoPane.getBaseNode());

		myPen = new Pen(canvasPane.getCanvas(), Color.BLUE, true);
		turtleViews = new ArrayList<TurtleView>();
		turtleViews.add(makeTurtleView(DEFAULT_TURTLE_IMAGE_PATH));

		propertiesPane = new TPropertiesElement(myResources, turtleViews.get(0), myPen, myStage, this);
		myRoot.setLeft(propertiesPane.getMyBaseNode());

		menuPane = new MenuBarElement(myResources, canvasPane, ioPane,
				DEFAULT_BACKGROUND, languages, DEFAULT_LANG, myPen, this);
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
	public TurtleView makeTurtleView(String imagePath) {
		Image turtleImage = new Image(GUI.class.getResourceAsStream(imagePath));

		TurtleView newTurtle = new TurtleView(turtleImage, canvasPane.getCanvas(), canvasPane
				.getCanvas().getWidth() / 2,
				canvasPane.getCanvas().getHeight() / 2,
				canvasPane.getBaseNode(), myPen);
		
		mySceneUpdater.createTurtle(newTurtle);
		return newTurtle;
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

	public void bindTable(String type, ObservableList<TableElements> l) {
		List<TableView<TableElements>> tables = infoPane.getTables();
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

	public void setInputText(String string) {
		// TODO Auto-generated method stub
		
	}
}