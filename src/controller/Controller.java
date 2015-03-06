package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import model.*;
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
	private Map<String, CommandTreeNode> commands;
	private Turtle turtle;
	private VariableList variables;
	private TreeGenerator generator = new TreeGenerator();
	private TreeInterpreter interpreter;

	public void init(Stage s) {
		turtle = new Turtle();
		sceneUpdater = new SceneUpdater(s, this);
		sceneUpdater.initGUI();
		variables = new VariableList();
		commands = new HashMap<String, CommandTreeNode>();
		linkTurtles(turtle);
	}

	// public void syncCommandandVariableLists() {
	// variables = new SimpleListProperty<Variable>();
	// }

	/**
	 * Parses command from front-end and sends the result to back-end
	 * 
	 * @param input
	 * @param language
	 */
	public void parseCommand(String input, String language) {
		CommandTreeNode node = generator.createCommands(input, language);

		// get method list if needed - up to back-end on how to deal with this
		// case
//		generator.getMethodsList();

		interpreter = new TreeInterpreter(variables, turtle);
		interpreter.interpretTree(node);
		if (variables != null) {
			sceneUpdater.setListBind("Variable", variables.getList());
		}
		setOutputText(Double.toString(node.getValue()));
	}

	/**
	 * Sends text to be outputted to the front-end from the back-end
	 * 
	 * @param outputText
	 */
	public void setOutputText(String outputText) {
		sceneUpdater.setOutputText(outputText);
	}

	public void linkTurtles(Turtle turtleModel) {
		turtleModel.addObserver(sceneUpdater.getTurtleView());
	}

	public ObservableList<Variable> getVariableList() {
		return variables.getList();
	}

	public Set<String> getPrevCommandList() {
		return commands.keySet();
	}

	// public void play() {
	// frame = addKeyFrame(fps);
	// animation.getKeyFrames().add(frame);
	// animation.setCycleCount(Animation.INDEFINITE);
	// }
}