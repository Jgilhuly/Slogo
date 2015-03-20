package controller;

import java.util.*;
import javafx.stage.Stage;
import main.WorkspaceManager;
import model.*;
import parser.*;
import ui.*;


public class Controller {
    private FrontEndInterface frontEnd;
    private Map<String, CommandTreeNode> previousCommands;
    private TreeGenerator generator;
    private TreeInterpreter interpreter;
    private WorkspaceManager myManager;

    public Controller (WorkspaceManager wm) {
        myManager = wm;
        interpreter = new TreeInterpreter();
        generator = new TreeGenerator();
        previousCommands = new HashMap<String, CommandTreeNode>();
    }

    public void init (Stage s) {
        frontEnd = new FrontEndLiason(s, this).initialize();
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
        frontEnd.setOutputText(Double.toString(node.getValue()));
        frontEnd.addCommandHistory(input);

    }

    public void addCommandHistory (String input) {
        frontEnd.addCommandHistory(input);
    }

    public Set<String> getPrevCommandList () {
        return previousCommands.keySet();
    }

    public void createNewWorkspace () {
        myManager.createWorkspace(null);
    }

}
