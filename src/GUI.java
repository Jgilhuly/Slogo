import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI {

	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private ResourceBundle myResources; // for language support

	private Scene myScene;
	private Stage myStage;
	private BorderPane myRoot;

	private TurtleView tView;

	private TextField inputField;
	private TextField outputField;
	private Button confirmInput;
	private Canvas canvas;
	private MenuBar menuBar;

	public GUI(Stage stageIn, String language) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE
				+ language);
		myStage = stageIn;
		tView = new TurtleView();

		myRoot = new BorderPane();
		myRoot.setBottom(makeIOFields());
		myRoot.setCenter(makeCanvas());
		myRoot.setTop(makeMenuBar());

		myScene = new Scene(myRoot, myStage.getWidth(), myStage.getHeight());
	}

	private Node makeIOFields() {
		VBox result = new VBox();

		inputField = new TextField();
		inputField.setPromptText("Enter your Logo Command");
		result.getChildren().add(inputField);

		outputField = new TextField();
		outputField.setPromptText("Output will be displayed here");
		outputField.setEditable(false);
		result.getChildren().add(outputField);

		confirmInput = makeButton("EnterCommand", e -> parseCommand());
		result.getChildren().add(confirmInput);

		return result;
	}

	private Object parseCommand() {
		return null;
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
		String label = myResources.getString(property);
		result.setText(label);
		result.setOnAction(handler);
		return result;
	}

	private Node makeCanvas() {
		canvas = new Canvas();
		canvas.getGraphicsContext2D().setFill(Color.TURQUOISE);
		canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(),
				canvas.getHeight());
		return canvas;
	}

	private Node makeMenuBar() {
		Menu fileMenu = new Menu(myResources.getString("File"));

		MenuItem Option1 = new MenuItem(myResources.getString("Option1"));
		fileMenu.getItems().add(Option1);

		menuBar = new MenuBar();
		menuBar.getMenus().add(fileMenu);

		return menuBar;
	}

	/**
	 * Returns scene for this view so it can be added to stage.
	 */
	public Scene getScene() {
		return myScene;
	}
}