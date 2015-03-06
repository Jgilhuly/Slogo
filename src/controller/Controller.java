package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import parser.Parser;
import parser.TreeGenerator;
import ui.SceneUpdater;

public class Controller {
	private SceneUpdater sceneUpdater;
	private Map<String, CommandTreeNode> previousCommands;

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
		Parser pp = new Parser(language);
		List<String> inputList = pp.parseList(input);
		TreeGenerator tg = new TreeGenerator();
		CommandTreeNode node = tg.createCommands(inputList);	
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


	
//
//	public ObservableList<Variable> getVariableList() {
//		return variables.getList();
//	}

	public Set<String> getPrevCommandList() {
		return previousCommands.keySet();
	}
	

}