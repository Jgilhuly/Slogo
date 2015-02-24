package ui;

import java.util.Observable;
import java.util.Observer;

import javafx.stage.Stage;
import Controller.Controller;

public class SceneUpdater implements Observer {

	private Stage myStage;
	private GUI myGUI;
	private String title = "SLogo";
	private Controller myController;

	public SceneUpdater(Stage stageIn, Controller controller) {
		myStage = stageIn;
		myGUI = new GUI(myStage, this);
		myController = controller;
	}

	public void newScene() {
		// TODO Auto-generated method stub
		myStage.setScene(myGUI.getScene());
		myStage.setTitle(title);
	}

	// TEMPORARY METHOD UNTIL WE FIGURE OUT A BETTER WAY TO SEND STRING BACK
	public void sendCommands(String input, String language) {
		myController.parseCommand(input, language);
	}

	@Override
	public void update(Observable o, Object arg) {
		// Update TurtleView to move the image
	}

	public void drawLine(int x1, int y1, int x2, int y2) {
		// either call drawLine method in myGUI, or get the Canvas Graphics
		// Context and draw it
	}

}
