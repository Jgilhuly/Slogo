package ui;

import java.util.Observable;
import java.util.Observer;

import javafx.stage.Stage;

public class SceneUpdater implements Observer {

	private Stage myStage;
	private GUI myGUI;
//	private Controller myController;

	public SceneUpdater (Stage stageIn, String language) {
		myStage = stageIn;
		myGUI = new GUI (myStage, language, this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// Update TurtleView to move the image
	}
	
	public void drawLine (int x1, int y1, int x2, int y2) {
		// either call drawLine method in myGUI, or get the Canvas Graphics Context and draw it
	}
}
