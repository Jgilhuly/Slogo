package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javafx.stage.Stage;
import main.WorkspaceManager;
import model.TreeInterpreter;
import model.Turtle;
import parser.CommandTreeNode;
import parser.TreeGenerator;
import ui.WindowGUI;
import ui.iGUI;
import ui.TurtleView;

//THIS ENTIRE FILE IS A PART OF MY MASTERPIECE
//GEORGIA TSE

public class Controller {

    private Map<String, CommandTreeNode> previousCommands;
    private TreeGenerator generator;
    private TreeInterpreter interpreter;
    private WorkspaceManager myManager;
    private iGUI myGUI;

    public Controller (WorkspaceManager wm) {
        myManager = wm;
        interpreter = new TreeInterpreter();
        generator = new TreeGenerator();
        previousCommands = new HashMap<String, CommandTreeNode>();
    }

    public void init (Stage s) {
        myGUI = new WindowGUI(s, this);
        myGUI.initialize();
    }

    public void createTurtle (TurtleView view) {
        Turtle t = new Turtle();
        interpreter.getTurtleList().add(t);
        t.addObserver(view);

    }

    /**
     * Parses command from front-end and sends the result to back-end
     * 
     * @param input
     * @param language
     */
    public void parseCommand (String input, String language) {
        CommandTreeNode node = generator.createCommands(input, language);
        generator.getMethodsList(); //TODO: USE TO GET USER GENERATED COMMANDS
        interpreter.interpretTree(node);
        myGUI.setOutputText(Double.toString(node.getValue()));
        myGUI.addCommandHistory(input);

    }
    


    private void addCommandHistory (String name, CommandTreeNode prev) {
        if (!previousCommands.containsKey(name)) {
            previousCommands.put(name, prev);
        }

    }


    public void addCommandHistory (String input) {
        myGUI.addCommandHistory(input);
    }


    public Set<String> getPrevCommandList () {
        return previousCommands.keySet();
    }

    public void createNewWorkspace () {
        myManager.createWorkspace(null);
    }

}
