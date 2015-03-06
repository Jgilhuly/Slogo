

import controller.WorkspaceManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage s) throws Exception {
		WorkspaceManager wm = new WorkspaceManager();
		wm.createWorkspace(s);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
