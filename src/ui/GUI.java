package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	private Color backgroundColor;
	
	private String[] languages = { "Chinese", "English", "French", "German",
			"Italian", "Japanese", "Korean", "Portuguese", "Russian", "Spanish" };
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
		myStage.setTitle(myResources.getString("Title"));
		myRoot = new BorderPane();
		myRoot.setBottom(makeIOFields());
		myRoot.setCenter(makeCanvas());
		myRoot.setTop(makeMenuBar());
		myRoot.setRight(makePrevCommandsPane());

		Image turtleImage = new Image(
				GUI.class
						.getResourceAsStream("/resources/images/turtleImage.png"));
		tView = new TurtleView(turtleImage, canvas, Color.BLUE, canvas.getWidth() / 2,
				canvas.getHeight() / 2);
		tView.draw();
		
		// default
		selectedLanguage = "English";
		backgroundColor = Color.FUCHSIA;

		myScene = new Scene(myRoot, myStage.getWidth(), myStage.getHeight());
		myStage.setScene(myScene);
	}

	private Node makeIOFields() {
		VBox result = new VBox();

		outputField = new TextField();
		outputField.setPromptText(myResources.getString("OutputPrompt"));
		outputField.setEditable(false);

		inputField = new TextField();
		inputField.setPromptText(myResources.getString("InputPrompt"));

		confirmInput = makeButton(myResources.getString("Enter"), e -> parseCommand());
		// confirmInput.setDisable(true);
		
		result.getChildren().addAll(inputField,outputField,confirmInput);
		return result;
	}

	private void parseCommand() {
		if (inputField.getText() != null)
			mySceneUpdater.sendCommands(inputField.getText(), selectedLanguage);
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

	private Node makeCanvas() {
		canvasHolder = new StackPane();
		canvas = new Canvas(myStage.getWidth() / 1.5, myStage.getHeight() / 1.5);
		
		canvasHolder.getChildren().add(canvas);

		canvasHolder.setBackground(new Background (new BackgroundFill(backgroundColor, null, null)));
		return canvasHolder;
	}

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
		Menu languageMenu = new Menu(myResources.getString("Languages"));
		for (String string : languages) {
			CheckMenuItem cmi = new CheckMenuItem(string);
			languageMenu.getItems().add(cmi);
			cmi.selectedProperty()
					.addListener(
							e -> checkMenuItems(string, cmi.isSelected(),
									languageMenu));
		}
		// ***************************************

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, optionsMenu, languageMenu);

		return menuBar;
	}

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

	// *****************************
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
	private void checkMenuItems(String language, boolean selected, Menu menu) {
		confirmInput.setDisable(false);
		if (selected) {
			selectedLanguage = language;
//			 myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE
//					 + selectedLanguage);
			System.out.println(language);
			toggleMenuItems(menu, language, ACTIVE);
			inputField.setDisable(false);
		} else {
			confirmInput.setDisable(true);
			toggleMenuItems(menu, language, INACTIVE);
			inputField.setDisable(true);
		}
	}

	private void toggleMenuItems(Menu menu, String language, boolean state) {
		for (int i = 0; i < languages.length; i++) {
			MenuItem temp = menu.getItems().get(i);
			if (!temp.getText().equals(language)) {
				temp.setDisable(state);
			}
		}
	}

	// *******************************

	private Node makePrevCommandsPane() {
		VBox result = new VBox();
		result.setSpacing(5);
		
		ArrayList<String> cols = new ArrayList<String>();
		
		cols.add("Names");
		cols.add("Values");
		result.getChildren().add(makeTable("Variables", cols));
	
		cols = new ArrayList<String>();
		cols.add("Commands");
		result.getChildren().add(makeTable("Previous Commands", cols));
		
		cols = new ArrayList<String>();
		cols.add("Commands");
		result.getChildren().add(makeTable("User Commands", cols));
		
		return result;
	}
	
	public void setOutputText(String outputText) {
		outputField.setText(outputText);
	}

	private Node makeTable(String title, List<String> columnNames) {
		VBox labelAndTable = new VBox();
		labelAndTable.setSpacing(5);
		
		Label label = new Label(title);
		label.setFont(new Font("Arial", 14));
		
		TableView table = new TableView();
		
		ArrayList<TableColumn> cols = new ArrayList<TableColumn>();
		
		for (String s : columnNames) {
			cols.add(new TableColumn(s));
		}
		
		table.getColumns().addAll(cols);
		
		labelAndTable.getChildren().addAll(label, table);
		return labelAndTable;
	}

	public Observer getTurtleView() {
		return tView;
	}
}