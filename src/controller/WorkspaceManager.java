package controller;

import javafx.stage.Stage;


public class WorkspaceManager {

	public WorkspaceManager() {
	}
	
	public void createWorkspace(Stage s) {
		if (s == null)
			s = new Stage();
		
		try {
			Controller master = new Controller(this);
			master.init(s);
			s.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
