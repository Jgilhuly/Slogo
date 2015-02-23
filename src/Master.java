import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import parser.CommandTreeNode;
import parser.Parser;

public class Master {
	private int fps = 10;
	private Timeline animation = new Timeline();
	private KeyFrame frame;

	public void init(Stage s) {
		Parser pp = new Parser("Chinese");
		
		// get this from front end
		String input = "chongfu + 20 * 3 5 [ chongfu 2 [ chongfu 30 [ qj 1 yz 2 ] qj 120 ] yz 60 ]";
		
		// String input = "repeat + 20 10 [ fd 1 rt 2]";
		// String input = "make :random sum 1 random 100 ";
		// String input2 = "fd :random";
		
		// send this to back end
		CommandTreeNode root = pp.makeTree(input);
	}

	public KeyFrame addKeyFrame(int frameRate) {
		return new KeyFrame(Duration.millis(1000 / frameRate), e -> evolve(e));
	}

	private void evolve(ActionEvent e) {

	}

	public void play() {
		frame = addKeyFrame(fps);
		animation.getKeyFrames().add(frame);
		animation.setCycleCount(Animation.INDEFINITE);
	}
}