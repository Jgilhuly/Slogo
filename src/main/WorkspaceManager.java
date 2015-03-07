package main;

import javafx.stage.Stage;
import controller.Controller;

/**
 * A class used to represent one workspace, a sub-program with unique configurations, variables, and commands
 * @author OWNER
 *
 */
public class WorkspaceManager {

    public WorkspaceManager () {
    }

    public void createWorkspace (Stage s) {
        if (s == null)
            s = new Stage();

        try {
            Controller master = new Controller(this);
            master.init(s);
            s.show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
