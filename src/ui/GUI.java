package ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUI {

	public static final String DEFAULT_RESOURCE_PACKAGE = "resources.displayText/";
	private static final String DEFAULT_TURTLE_IMAGE_PATH = "/resources/images/turtleImage.png";
	private static final boolean ACTIVE = true;
	private static final boolean INACTIVE = false;

	private ResourceBundle myResources; // for node text/names

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

	private Color backgroundColor;
	private String[] languages = { "Chinese", "English", "French", "German",
			"Italian", "Japanese", "Korean", "Portuguese", "Russian", "Spanish" };
	private String selectedLanguage;

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
		// default values
		selectedLanguage = "English";
		backgroundColor = Color.FUCHSIA;

		myStage.setTitle(myResources.getString("Title"));
		myRoot = new BorderPane();
		myRoot.setBottom(makeIOFields());
		myRoot.setCenter(makeCanvas(backgroundColor));
		myRoot.setRight(makeInfoPane());
		tView = makeTurtleView(DEFAULT_TURTLE_IMAGE_PATH);
		myRoot.setTop(makeTopBar());

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

		return new TurtleView(turtleImage, canvas, Color.BLUE,
				canvas.getWidth() / 2, canvas.getHeight() / 2, canvasHolder);
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

		result.getChildren().addAll(inputField, outputField, confirmInput);
		return result;
	}

	/**
	 * Sends a string slogo command through the Scene Updater and Controller,
	 * all the way to the back-end.
	 */
	private void parseCommand() {
		if (inputField.getText() != null)
			mySceneUpdater.sendCommands(inputField.getText(), selectedLanguage);
		addHistory(); // for previous commands tab
		inputField.setText("");
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

	private Node makeBackgroundColorPicker(Color defaultColor) {
		ColorPicker colorPicker = new ColorPicker(defaultColor);
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

	private Node makePenColorPicker(Color defaultColor) {
		ColorPicker colorPicker = new ColorPicker(defaultColor);
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
			changePenColor(colorPicker.getValue());
		});
		return colorPicker;
	}

	private void changeColor(Color color) {
		canvasHolder.setBackground(new Background(new BackgroundFill(color,
				null, null)));
	}

	private void changePenColor(Color color) {
		tView.setColor(color);
	}

	/**
	 * Creates top bar
	 */
	private Node makeTopBar() {
		ToolBar tb = new ToolBar();
		tb.getItems().addAll(makeMenuBar(),
				new Label(myResources.getString("BackgroundColor")),
				makeBackgroundColorPicker(backgroundColor),
				new Label(myResources.getString("PenColor")),
				makePenColorPicker(tView.getColor()));

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
		fileOp1.setOnAction(e -> selectAndChangeTurtleImage());
		fileMenu.getItems().add(fileOp1);
		Menu optionsMenu = new Menu(myResources.getString("Options"));
		MenuItem htmlHelp = new MenuItem(myResources.getString("Help"));
		htmlHelp.setOnAction(e -> showHTMLHelp());
		optionsMenu.getItems().add(htmlHelp);

		Menu languageMenu = makeMenu("Languages", languages);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, optionsMenu, languageMenu);

		return menuBar;
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
		}
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
		webEngine.load("http://www.cs.duke.edu/courses/compsci308/spring15/assign/03_slogo/commands.php");

		VBox helpRoot = new VBox();
		helpRoot.getChildren().add(browser);

		Stage stage = new Stage();
		stage.setTitle(myResources.getString("HelpPageTitle"));
		stage.setScene(new Scene(helpRoot, 800, 800));
		stage.show();
	}

	/**
	 * Checks a single menu item within a given menu
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

	/**
	 * Toggles a menuItem
	 * 
	 * @param menu
	 * @param input
	 * @param state
	 */
	private void toggleMenuItems(Menu menu, String input, boolean state) {
		for (int i = 0; i < languages.length; i++) {
			MenuItem temp = menu.getItems().get(i);
			if (!temp.getText().equals(input)) {
				temp.setDisable(state);
			}
		}
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




		result.getChildren().add(p.makeTable("Previous Commands"));
		
		result.getChildren().add(p.makeTable("Variables"));
		

		result.getChildren().add(p.makeTable("User Commands"));

		return result;
	}

	/**
	 * Sets the output text field. Called from back-end (through controller)
	 * 
	 * @param outputText
	 */
	public void setOutputText(String outputText) {
		outputField.setText(outputText);
	}

	/**
	 * Updates the right side info tables
	 */
	@SuppressWarnings("rawtypes")
	private void addHistory() {
		ArrayList<TableColumn> cols = new ArrayList<TableColumn>();

		TableColumn<String, String> tc = new TableColumn<>("Previous Commands");
		tc.setCellValueFactory(e -> new SimpleStringProperty(inputField
				.getText()));

		cols.add(tc);

		// table.getColumns().addAll(cols);

		inputField.getText();
	}

	/**
	 * Returns the TurtleView so that it can be linked to its model
	 * 
	 * @return
	 */
	public Observer getTurtleView() {
		return tView;
	}

	// private Node makeTable(String title, List<String> columnNames) {
	// VBox labelAndTable = new VBox();
	// labelAndTable.setSpacing(5);
	//
	// Label label = new Label(title);
	// label.setFont(new Font("Arial", 14));
	//
	// TableView table = new TableView();
	//
	// labelAndTable.getChildren().addAll(label, table);
	// return labelAndTable;
	// }
}