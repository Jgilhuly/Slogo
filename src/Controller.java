import javafx.stage.Stage;
import parser.CommandTreeNode;
import parser.Parser;

public class Controller {
	private SceneHandler sceneUpdater;
	
	public void init(Stage s) {
		
		sceneUpdater = new SceneHandler(s);
		
		
		
		//FOR NOW PUTTING THIS IN INIT METHOD
		// get this from front end
		String input = "chongfu + 20 * 3 5 [ chongfu 2 [ chongfu 30 [ qj 1 yz 2 ] qj 120 ] yz 60 ]";
		parseCommand(input);
		// String input = "repeat + 20 10 [ fd 1 rt 2]";
		// String input = "make :random sum 1 random 100 ";
		// String input2 = "fd :random";
		
	
	
	}
	//TODO: get the current language for the parser from the view
	public CommandTreeNode parseCommand(String input) {
		Parser pp = new Parser("Chinese");
		return pp.makeTree(input);
	}
	
	
}