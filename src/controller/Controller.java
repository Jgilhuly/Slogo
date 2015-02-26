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
	// private int fps = 10;
	// private Timeline animation = new Timeline();
	// private KeyFrame frame;
	private CommandList commands = new CommandList();
//	private List<Variable> variables = new SimpleListProperty<Variable>(); //taken out of the method syncCommandListblah
//	private List<Variable> variables = new SimpleListProperty<Variable>(javafx.collections.FXCollections.observableList(new ArrayList<Variable>()));
	private VariableList variables = new VariableList();
	private Turtle turtle = new Turtle();
	private TreeInterpreter woot;

	public void init(Stage s) {
		sceneUpdater = new SceneUpdater(s, this);
		sceneUpdater.initGUI();
		
		// get this from front end
		// String input = "repeat + 20 10 [ fd 1 rt 2]";
		
		// TEST THIS CASE - DOESNT WORK RIGHT NOW
		// String input = fd product 100 tan 30
		
		// other possible examples:
		// String input =
		// "chongfu + 20 * 3 5 [ chongfu 2 [ chongfu 30 [ qj 1 yz 2 ] qj 120 ] yz 60 ]";
		// String input = "repeat + 20 10 [ fd 1 rt 2]";
		// String input = "make :random sum 1 random 100 ";
		// String input2 = "fd :random";
		// play();
		// animation.play();
	}

//	public void syncCommandandVariableLists() {
//		variables = new SimpleListProperty<Variable>();
//	}

	public void parseCommand(String input, String language) {
		Parser pp = new Parser(language);
		CommandTreeNode node = pp.makeTree(input);
		woot = new TreeInterpreter(commands, variables, turtle, this);
		woot.interpretTree(node);
	}

	public void setOutputText(String outputText) {
		sceneUpdater.setOutputText(outputText);
	}

	public KeyFrame addKeyFrame(int frameRate) {
		return new KeyFrame(Duration.millis(1000 / frameRate), e -> update(e));
	}

	public void linkTurtles(Turtle turtleModel) {
		turtleModel.addObserver(sceneUpdater.getTurtleView());
	}

	private void update(ActionEvent e) {
	}

	// public void play() {
	// frame = addKeyFrame(fps);
	// animation.getKeyFrames().add(frame);
	// animation.setCycleCount(Animation.INDEFINITE);
	// }
}