package ui;

import java.util.Observable;
import java.util.Observer;

import controller.Controller;
import javafx.stage.Stage;

public class SceneUpdater implements Observer {

	private GUI myGUI;

	private Controller myController;


	public SceneUpdater(Stage s, Controller c) {
		myGUI = new GUI(s, this);
		myController = c;	
		
	}
	//SHOULD BE IN THE GUI
	public void initGUI() {
		myGUI.initialize();
		
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

	public Observer getTurtleView() {
		return myGUI.getTurtleView();
	}

}
