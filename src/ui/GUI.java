package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class GUI {

	public static final String DEFAULT_RESOURCE_PACKAGE = "resources.displayText/";
	private static final boolean ACTIVE = true;
	private static final boolean INACTIVE = false;

	private ResourceBundle myResources; // for language support

	private Scene myScene;
	private Stage myStage;
	private BorderPane myRoot;
	private SceneUpdater mySceneUpdater;
	private TurtleView tView;

	private TextField inputField;
	private TextField outputField;
	private Button confirmInput;
	private Canvas canvas;
	private StackPane canvasHolder;

	private List<String> variables;

	private Color backgroundColor;

	/**
	 * TODO: MAKE INHERITANCE HIERACHY FOR GUI
	 * 
	 */

	/*
	 * Consider using enums vvvv
	 */
	/*****************************/
	private String[] languages = { "Chinese", "English", "French", "German",
			"Italian", "Japanese", "Korean", "Portuguese", "Russian", "Spanish" };
	// private String[] colors = { "Aqua", "Black", "Blue", "Cyan", "Fuchsia",
	// "Gray", "Green", "LightBlue", "LightGreen", "Lime", "Navy",
	// "Orange", "Red", "RoyalBlue", "Snow", "Teal", "Violet", "White",
	// "Yellow" };
	// private String[] hexValues = { "#00FFFF", "#000000", "#0000FF",
	// "#00FFFF",
	// "#FF00FF", "#808080", "#008000", "#ADD8E6", "90EE90", "#00FF00",
	// "#000080", "#FFA500", "#FF0000", "#4169E1" };
	/*****************************/
	private String selectedLanguage;

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
		// default values
		selectedLanguage = "English";
		backgroundColor = Color.FUCHSIA;

		myStage.setTitle(myResources.getString("Title"));
		myRoot = new BorderPane();
		myRoot.setBottom(makeIOFields());
		myRoot.setCenter(makeCanvas(backgroundColor));
		myRoot.setTop(makeTopBar());
		myRoot.setRight(makePrevCommandsPane());

		Image turtleImage = new Image(
				GUI.class
						.getResourceAsStream("/resources/images/turtleImage.png"));
		tView = new TurtleView(turtleImage, canvas, Color.BLUE,
				canvas.getWidth() / 2, canvas.getHeight() / 2, canvasHolder);

		selectedLanguage = "English"; // default

		myScene = new Scene(myRoot, myStage.getWidth(), myStage.getHeight());
		myStage.setScene(myScene);
	}

	/**
	 * Creates the input and output fields, and the input button
	 * 
	 * @return
	 */
	private Node makeIOFields() {
		VBox result = new VBox();

		outputField = new TextField();
		outputField.setPromptText(myResources.getString("OutputPrompt"));
		outputField.setEditable(INACTIVE);

		inputField = new TextField();
		inputField.setPromptText(myResources.getString("InputPrompt"));

		confirmInput = makeButton(myResources.getString("Enter"),
				e -> parseCommand());
		// confirmInput.setDisable(true);

		result.getChildren().addAll(inputField, outputField, confirmInput);
		return result;
	}

	/**
	 * Sends a string slogo command through the Scene Updater and Controller,
	 * all the way to the backend
	 */
	private void parseCommand() {
		if (inputField.getText() != null)
			mySceneUpdater.sendCommands(inputField.getText(), selectedLanguage);
		addHistory();
	}

	/**
	 * Helper method to create buttons with labels and handlers (Taken from the
	 * example_browser)
	 * 
	 * @param property
	 * @param handler
	 * @return
	 */
	private Button makeButton(String property, EventHandler<ActionEvent> handler) {
		Button result = new Button();
		// String label = myResources.getString(property);
		result.setText(property);
		result.setOnAction(handler);
		return result;
	}

	/**
	 * Creates the canvas that the turtle lives on
	 * 
	 * @return
	 */
	private Node makeCanvas(Color color) {
		canvasHolder = new StackPane();
		canvas = new Canvas(myStage.getWidth() / 1.5, myStage.getHeight() / 1.5);

		canvasHolder.getChildren().add(canvas);

		canvasHolder.setBackground(new Background(new BackgroundFill(color,
				null, null)));
		return canvasHolder;
	}

	private Node makeColorPicker() {
		ColorPicker colorPicker = new ColorPicker();
		final SVGPath svg = new SVGPath();
		svg.setContent("M70,50 L90,50 L120,90 L150,50 L170,50"
				+ "L210,90 L180,120 L170,110 L170,200 L70,200 L70,110 L60,120 L30,90"
				+ "L70,50");
		svg.setStroke(Color.DARKGREY);
		svg.setStrokeWidth(2);
		svg.setEffect(new DropShadow());
		svg.setFill(colorPicker.getValue());

		colorPicker.setOnAction(e -> {
			svg.setFill(colorPicker.getValue());
			changeColor(colorPicker.getValue());
		});
		return colorPicker;
	}

	private void changeColor(Color color) {
		canvasHolder.setBackground(new Background(new BackgroundFill(color,
				null, null)));
	}

	/**
	 * Creates top bar
	 */
	private Node makeTopBar() {
		ToolBar tb = new ToolBar();
		tb.getItems().addAll(makeMenuBar(), makeColorPicker());

		return tb;
	}

	/**
	 * Creates the top menu bar
	 * 
	 * @return
	 */
	private Node makeMenuBar() {
		Menu fileMenu = new Menu(myResources.getString("File"));

		MenuItem fileOp1 = new MenuItem(myResources.getString("FileOp1"));
		fileMenu.getItems().add(fileOp1);

		Menu optionsMenu = new Menu(myResources.getString("Options"));

		MenuItem htmlHelp = new MenuItem(myResources.getString("Help"));
		htmlHelp.setOnAction(e -> showHTMLHelp());
		optionsMenu.getItems().add(htmlHelp);

		// ***************************************
		// added languageMenu
		Menu languageMenu = makeMenu("Languages", languages);
		// ***************************************

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, optionsMenu, languageMenu);

		return menuBar;
	}

	/**
	 * Creates Menu - temporary method before inheritance hierarchy
	 */
	private Menu makeMenu(String name, String[] list) {
		Menu menu = new Menu(myResources.getString(name));
		for (String string : list) {
			CheckMenuItem cmi = new CheckMenuItem(string);
			menu.getItems().add(cmi);

			cmi.selectedProperty()
					.addListener(
							e -> checkLanguageMenuItems(string,
									cmi.isSelected(), menu));

		}
		return menu;
	}

	/**
	 * Shows the HTML Help window
	 */
	private void showHTMLHelp() {
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine
				.load("http://www.cs.duke.edu/courses/compsci308/spring15/assign/03_slogo/commands.php");

		VBox helpRoot = new VBox();
		helpRoot.getChildren().add(browser);

		Stage stage = new Stage();
		stage.setTitle(myResources.getString("HelpPageTitle"));
		stage.setScene(new Scene(helpRoot, 500, 500));
		stage.show();
	}

	/**
	 * Added the following two methods to have checkable language menu bar that
	 * disables the rest when one is clicked. Feel free to change them if there
	 * are other/better ways to choose a language, but I just need the language
	 * chosen by the user to be passed in the method "parseCommand" - Kei
	 * 
	 * @param language
	 * @param selected
	 * @param menu
	 */
	private void checkLanguageMenuItems(String language, boolean selected,
			Menu menu) {
		confirmInput.setDisable(false);
		if (selected) {
			selectedLanguage = language;
			// myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE
			// + selectedLanguage);
			System.out.println(language);
			toggleMenuItems(menu, language, ACTIVE);
			inputField.setEditable(ACTIVE);
		} else {
			confirmInput.setDisable(true);
			toggleMenuItems(menu, language, INACTIVE);
			inputField.setEditable(INACTIVE);
		}
	}

	private void toggleMenuItems(Menu menu, String input, boolean state) {
		for (int i = 0; i < languages.length; i++) {
			MenuItem temp = menu.getItems().get(i);
			if (!temp.getText().equals(input)) {
				temp.setDisable(state);
			}
		}
	}

	private Node makePrevCommandsPane() {
		VBox result = new VBox();
		result.setSpacing(5);

		DisplayPanel p = new DisplayPanel();

		ArrayList<String> cols = new ArrayList<String>();

		cols = new ArrayList<String>();
		cols.add("Commands");
		result.getChildren().add(p.makeTable("Previous Commands", cols));

		cols.add("Names");
		cols.add("Values");
		result.getChildren().add(p.makeTable("Variables", cols));

		cols = new ArrayList<String>();
		cols.add("Commands");

		result.getChildren().add(p.makeTable("User Commands", cols));

		return result;
	}

	public void setOutputText(String outputText) {
		outputField.setText(outputText);
	}

	private void addHistory() {
		ArrayList<TableColumn> cols = new ArrayList<TableColumn>();

		TableColumn<String, String> tc = new TableColumn<>("Previous Commands");
		tc.setCellValueFactory(e -> new SimpleStringProperty(inputField
				.getText()));

		cols.add(tc);

		// table.getColumns().addAll(cols);

		inputField.getText();
	}

	private Node makeTable(String title, List<String> columnNames) {
		VBox labelAndTable = new VBox();
		labelAndTable.setSpacing(5);

		Label label = new Label(title);
		label.setFont(new Font("Arial", 14));

		TableView table = new TableView();

		labelAndTable.getChildren().addAll(label, table);
		return labelAndTable;
	}

	public Observer getTurtleView() {
		return tView;
	}
}