package ui;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	private MenuBar menuBar;
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
		result.getChildren().add(inputField);

		confirmInput = makeButton(myResources.getString("Enter"), e -> parseCommand());
		// confirmInput.setDisable(true);
		result.getChildren().add(confirmInput);
		
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
		canvas = new Canvas();
		canvas.setWidth(myStage.getWidth() / 1.5);
		canvas.setHeight(myStage.getHeight() / 1.5);
		canvas.getGraphicsContext2D().setFill(Color.LIGHTGRAY);
		canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(),
				canvas.getHeight());
		return canvas;
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

		menuBar = new MenuBar();
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
		return result;
	}
}