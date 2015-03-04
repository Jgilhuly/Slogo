package ui;


import java.io.File;
import java.util.*;

import model.Variable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
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
	
	IOElement ioPane;
	CanvasElement canvasPane;
	InfoElement infoPane;
	MenuBarElement menuPane;

	private TableView prevCommandsTable;
	private TableView variablesTable;
	private TableView userCommandsTable;
	
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
		ioPane = new IOElement(myResources, this);
		canvasPane = new CanvasElement(DEFAULT_BACKGROUND, myStage.getWidth(), myStage.getHeight());
//		infoPane = new IOElement(myResources, this);
		menuPane = new MenuBarElement(myResources, canvasPane, ioPane, tView, DEFAULT_BACKGROUND, languages, DEFAULT_LANG, myStage);

		myStage.setTitle(myResources.getString("Title"));
		myRoot = new BorderPane();
		myRoot.setBottom(ioPane.getBaseNode());
		myRoot.setCenter(canvasPane.getBaseNode());
		myRoot.setRight(makeInfoPane());
		tView = makeTurtleView(DEFAULT_TURTLE_IMAGE_PATH);
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

		return new TurtleView(turtleImage, canvasPane.getCanvas(), Color.BLUE,
				canvasPane.getCanvas().getWidth() / 2, canvasPane.getCanvas().getHeight() / 2, canvasPane.getBaseNode());
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
		mySceneUpdater.sendCommands(ioPaneCasted.getInputField().getText(), menuPane.getSelectedLanguage());
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
	
	/**
	 * Makes the right side info panel, which contains the tables of previous
	 * commands, user commands, and user variables
	 * 
	 * @return
	 */
	private Node makeInfoPane() {
		VBox result = new VBox();
		result.setSpacing(5);

		DisplayPanel p = new DisplayPanel();

		ArrayList<String> cols = new ArrayList<String>();

		cols = new ArrayList<String>();
		cols.add("Commands");
		prevCommandsTable = makeTable("Previous Commands", cols);
		result.getChildren().add(prevCommandsTable);

		cols = new ArrayList<String>();
		cols.add("Names");
		cols.add("Values");
		variablesTable = makeTable("Variables", cols);
		result.getChildren().add(variablesTable);

		cols = new ArrayList<String>();
		cols.add("Commands");
		userCommandsTable = makeTable("User Commands", cols);
		result.getChildren().add(userCommandsTable);
		return result;
	}
	public void bindTable(String type, ObservableList<Variable> l) {
		if (type.equals("Variable")) {
			variablesTable.setItems(l);
		}  
	}

	private TableView makeTable(String title, List<String> columnNames) {
		// VBox labelAndTable = new VBox();
		// labelAndTable.setSpacing(5);
		//
		Label label = new Label(title);
		label.setFont(new Font("Arial", 14));

		TableView table = new TableView();

		for (String s : columnNames) {
			table.getColumns().add((new TableColumn(s)));
		}

		// labelAndTable.getChildren().addAll(label, table);
		return table;
	}
	
	/**
	 * Updates the right side info tables
	 */

	private void addHistory() {
		// ArrayList<TableColumn> cols = new ArrayList<TableColumn>();
		//
		// TableColumn<String, String> tc = new
		// TableColumn<>("Previous Commands");
		// tc.setCellValueFactory(e -> new SimpleStringProperty(inputField
		// .getText()));
		//
		// cols.add(tc);
		//
		// // table.getColumns().addAll(cols);
		//
		// inputField.getText();
		//
		TableColumn<Variable, String> tc = (TableColumn) variablesTable.getColumns().get(0);
		
		for (Variable s : mySceneUpdater.getVariableList()) {
			tc.setCellValueFactory(new PropertyValueFactory("name"));
			
		}
		
		TableColumn<Variable, Double> tc2 = (TableColumn) variablesTable.getColumns().get(1);
		for (Variable s : mySceneUpdater.getVariableList()) {
			tc2.setCellValueFactory(new PropertyValueFactory("value"));
		}
//
//		for (String s : mySceneUpdater.getPrevCommandList()) {
//			System.out.println(s);
//		}
//		
//		for (Variable v : mySceneUpdater.getVariableList()) {
//			System.out.println(v.getName() + " - " + v.getValue());
//		}
	}
}