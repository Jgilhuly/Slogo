package ui.elements;

import java.util.ResourceBundle;

import ui.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class IOElement {
	private VBox myNode;
	
	private TextField inputField;
	private Text outputField;
	private Button confirmInput;	
	private ResourceBundle myResources;
	
	public IOElement(ResourceBundle resourceBundleIn, GUI myGui) {
		myResources = resourceBundleIn;
		init(myGui);
	}

	private void init(GUI myGui) {
		myNode = new VBox();

		outputField = new Text();
		
		inputField = new TextField();
		inputField.setPromptText(myResources.getString("InputPrompt"));

		confirmInput = makeButton(myResources.getString("Enter"),
				e -> myGui.parseCommand());

		myNode.getChildren().addAll(inputField, outputField, confirmInput);
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

	public Node getBaseNode() {
		return myNode;
	}
	
	public TextField getInputField() {
		return inputField;
	}
	
	public Text getOutputField() {
		return outputField;
	}

	public Button getButton() {
		return confirmInput;
	}
}
