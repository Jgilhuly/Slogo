package Controller;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import parser.CommandTreeNode;
import parser.Parser;
import ui.SceneUpdater;

public class Controller {
	private final int SCREEN_WIDTH = 1000;
	private final int SCREEN_HEIGHT = 1000;
	private SceneUpdater sceneUpdater;
	private int fps = 10;
	private Timeline animation = new Timeline();
	private KeyFrame frame;

	public void init(Stage s) {
		s.setWidth(SCREEN_WIDTH);
		s.setHeight(SCREEN_HEIGHT);
		sceneUpdater = new SceneUpdater(s,this);
		
		sceneUpdater.initGUI();
		
		// get this from front end
		// String input = "repeat + 20 10 [ fd 1 rt 2]";

		// other possible examples:
		// String input =
		// "chongfu + 20 * 3 5 [ chongfu 2 [ chongfu 30 [ qj 1 yz 2 ] qj 120 ] yz 60 ]";
		// String input = "repeat + 20 10 [ fd 1 rt 2]";
		// String input = "make :random sum 1 random 100 ";
		// String input2 = "fd :random";
		play();
		animation.play();
	}

	// TODO: get the current language for the parser from the view
	public CommandTreeNode parseCommand(String input, String language) {
		Parser pp = new Parser(language);
		return pp.makeTree(input);
	}

	public KeyFrame addKeyFrame(int frameRate) {
		return new KeyFrame(Duration.millis(1000 / frameRate), e -> update(e));
	}

	private void update(ActionEvent e) {
		
	}

	public void play() {
		frame = addKeyFrame(fps);
		animation.getKeyFrames().add(frame);
		animation.setCycleCount(Animation.INDEFINITE);
	}
}