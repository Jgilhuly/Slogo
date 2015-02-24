package ui;

import java.util.Observable;
import java.util.Observer;

import javafx.stage.Stage;
import Controller.Controller;

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
	
	public void drawLine (int x1, int y1, int x2, int y2) {
		// call drawLine method in myGUI
	}

}
