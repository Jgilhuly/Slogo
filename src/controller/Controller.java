package controller;

import java.util.ArrayList;
import java.util.List;
import model.*;
import javafx.animation.KeyFrame;
import javafx.beans.property.SimpleListProperty;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.TreeInterpreter;
import model.Turtle;
import model.Variable;
import parser.CommandTreeNode;
import parser.Parser;
import ui.SceneUpdater;

public class Controller {
	private SceneUpdater sceneUpdater;
	private CommandList commands = new CommandList();
	private VariableList variables = new VariableList();
	private Turtle turtle = new Turtle();
	private TreeInterpreter interpreter;

	public void init(Stage s) {
		sceneUpdater = new SceneUpdater(s, this);
		sceneUpdater.initGUI();
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
		CommandTreeNode node = pp.makeTree(input);
		interpreter = new TreeInterpreter(commands, variables, turtle, this);
		interpreter.interpretTree(node);
	}

	/**
	 * Sends text to be outputed to the front-end from the back-end
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

	// public void play() {
	// frame = addKeyFrame(fps);
	// animation.getKeyFrames().add(frame);
	// animation.setCycleCount(Animation.INDEFINITE);
	// }
}