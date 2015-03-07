package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Observer;
import java.util.Set;

import javafx.stage.Stage;
import main.WorkspaceManager;
import model.TreeInterpreter;
import model.Turtle;
import parser.CommandTreeNode;
import parser.TreeGenerator;
import ui.SceneUpdater;
import ui.TurtleView;

public class Controller {
	private SceneUpdater sceneUpdater;
	private Map<String, CommandTreeNode> previousCommands;
	private TreeGenerator generator;
	private TreeInterpreter interpreter;
	private WorkspaceManager myManager;

	public Controller (WorkspaceManager wm) {
		myManager = wm;
	}
	
	public void init(Stage s) {

		sceneUpdater = new SceneUpdater(s, this);
		sceneUpdater.initGUI();
		previousCommands = new HashMap<String, CommandTreeNode>();
		interpreter = new TreeInterpreter();
		generator = new TreeGenerator();
		
		
	}
	public void createTurtle(TurtleView view) {
		Turtle t = new Turtle();
		interpreter.getTurtleList().add(t);
		t.addObserver(view);
		
	}


	/**
	 * Parses command from front-end and sends the result to back-end
	 * 
	 * @param input
	 * @param language
	 */
	public void parseCommand(String input, String language) {
		CommandTreeNode node = generator.createCommands(input, language);
		generator.getMethodsList();
		interpreter.interpretTree(node);
		sceneUpdater.setOutputText(node.getValue().toString());
	}
	
	private void addCommandHistory(String name, CommandTreeNode prev) {
		if (!previousCommands.containsKey(name)) {
			previousCommands.put(name, prev);
		}

	}




	public Set<String> getPrevCommandList() {
		return previousCommands.keySet();
	}
	
	public void createNewWorkspace() {
		myManager.createWorkspace(null);
	}


}