package ui.elements;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ui.GUI;
import util.ButtonMaker;


public class IOElement {
    private VBox myNode;

    private TextField inputField;
    private Text outputField;
    private Button confirmInput;
    private ResourceBundle myResources;

    public IOElement (ResourceBundle resourceBundleIn, GUI myGui) {
        myResources = resourceBundleIn;
        init(myGui);
    }

    private void init (GUI myGui) {
        ButtonMaker bm = new ButtonMaker();
        myNode = new VBox();

        outputField = new Text();

        inputField = new TextField();
        inputField.setPromptText(myResources.getString("InputPrompt"));

        confirmInput = bm.makeButton(myResources.getString("Enter"),
                                     e -> myGui.parseCommand());

        myNode.getChildren().addAll(inputField, outputField, confirmInput);
    }

    public Node getBaseNode () {
        return myNode;
    }

    public TextField getInputField () {
        return inputField;
    }

    public Text getOutputField () {
        return outputField;
    }

    public Button getButton () {
        return confirmInput;
    }
}
