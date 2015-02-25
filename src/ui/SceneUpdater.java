package ui;

import java.util.Observable;
import java.util.Observer;

import javafx.stage.Stage;
import Controller.Controller;

public class SceneUpdater implements Observer {
	private final int SCREEN_WIDTH = 600;
	private final int SCREEN_HEIGHT = 600;
	private GUI myGUI;

	private Controller myController;


	public SceneUpdater(Stage s, Controller c) {
		s.setWidth(SCREEN_WIDTH);
		s.setHeight(SCREEN_HEIGHT);
		
		myGUI = new GUI(s, this);
		myController = c;	
		
	}
	
	public void initGUI() {
		myGUI.initialize();
	}

	public void sendCommands(String input, String language) {
		myController.parseCommand(input, language);
	}
	
	public void setOutputText(String outputText) {
		myGUI.setOutputText(outputText);
	}

	@Override
	public void update(Observable o, Object arg) {
		// Update things from GhostView?
	}

}
