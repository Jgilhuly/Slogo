package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import model.*;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.TreeInterpreter;
import model.Turtle;
import model.Variable;
import parser.CommandTreeNode;
import parser.TreeGenerator;
import ui.SceneUpdater;

public class Controller {
	private SceneUpdater sceneUpdater;

	private Map<String, CommandTreeNode> previousCommands;
	private TreeGenerator generator;
	private TreeInterpreter interpreter;
	
	public void init(Stage s) {
//		turtle = new Turtle();
		sceneUpdater = new SceneUpdater(s, this);
		sceneUpdater.initGUI();
		previousCommands = new HashMap<String, CommandTreeNode>();
		interpreter = new TreeInterpreter();
		
	}

	/**
	 * Parses command from front-end and sends the result to back-end
	 * 
	 * @param input
	 * @param language
	 */
	public void parseCommand(String input, String language) {
		CommandTreeNode node = generator.createCommands(input, language);

		// get method list if needed - up to back-end on how to deal with this case
		generator.getMethodsList();
		interpreter.interpretTree(node);
//		if (interpreter.getVariableList() != null) {
//			sceneUpdater.setListBind("Variable", variables.getList());
//		}
		sceneUpdater.setOutputText(node.getValue().toString());
	}
	
	private void addCommandHistory(String name, CommandTreeNode prev) {
		if (!previousCommands.containsKey(name)) {
			previousCommands.put(name, prev);
		}
	}
	
//	/**
//	 * Sends text to be outputted to the front-end from the back-end
//	 * 
//	 * @param outputText
//	 */
//	public void setOutputText(String outputText) {
//		sceneUpdater.setOutputText(outputText);
//	}




	public Set<String> getPrevCommandList() {
		return previousCommands.keySet();
	}

}