package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.TreeInterpreter;
import model.Turtle;
import model.Variable;
import model.VariableList;
import parser.CommandTreeNode;
import parser.Parser;
import parser.TreeGenerator;
import ui.SceneUpdater;

public class Controller {
	private SceneUpdater sceneUpdater;
	private Map<String, CommandTreeNode> commands;

	private VariableList variables;
	private Turtle turtle = new Turtle();
	private TreeInterpreter interpreter;

	public void init(Stage s) {

		sceneUpdater = new SceneUpdater(s, this);
		sceneUpdater.initGUI();
		variables = new VariableList();
		commands = new HashMap<String, CommandTreeNode>();
	}

//	public void syncCommandandVariableLists() {
//		variables = new SimpleListProperty<Variable>();
//	}

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
		linkTurtles(turtle);
		interpreter = new TreeInterpreter(variables, turtle);
		linkTurtles(turtle);
		
		interpreter.interpretTree(node);
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
	
	public KeyFrame addKeyFrame(int frameRate) {
		return new KeyFrame(Duration.millis(1000 / frameRate), e -> update(e));
	}

	private void update(ActionEvent e) {
	}
	
	public List<Variable> getVariableList() {
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