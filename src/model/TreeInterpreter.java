package model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import command.Command;
import errors.IllegalParameterNumberException;
import parser.CommandTreeNode;


public class TreeInterpreter {
    private CommandFactory factory;
    private VariableList variables;
    private int activeTurtleIndex;
    private List<Turtle> listTurtles;
    
    public TreeInterpreter () {
        factory = new CommandFactory();
		variables = new VariableList();
		activeTurtleIndex = 1;
		listTurtles =new ArrayList<Turtle>();
    }
    
    public void interpretTree (CommandTreeNode node) {
		List<Object> paramList = new ArrayList<>();
		if (!isLeaf(node)) {
			if (!(node.getType().equals("BRACKET"))) {
				for (CommandTreeNode child : node.getChildren()) { 
					interpretTree(child);
					paramList.add(node.getType().equals("COMMAND.CONTROL") ? child : child.getValue());
				}
			}
		}
		update(node, paramList);
	
	}
    public void setActiveTurtle(int num) {
    	activeTurtleIndex = num;
    }

    private boolean isLeaf(CommandTreeNode node){
        return node.getChildren().isEmpty(); 
    }

    public void executeCommand (CommandTreeNode node, List<Object> paramList) {
        Command c = factory.createCommand(node.getType(), node.getName());
        Class<?>[] params;
        Method method;
        try {
			for (Method m: c.getClass().getDeclaredMethods()) {
				if (m.getName().equals("calculateValue")) {
					params = m.getParameterTypes();
					method = c.getClass().getDeclaredMethod("calculateValue", params);
					Object value = method.invoke(c, paramList.toArray());
					node.setValue(value);
				}
			}
			
            
            
        }
        catch (NoSuchMethodException | SecurityException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
        	e.printStackTrace();
        	System.err.print("Error processing Command" + c.getClass().getName());
        	throw new IllegalParameterNumberException();
            
            
        }
    }


    public void update(CommandTreeNode node, List<Object> paramList) {
        switch (node.getType()){
            case "COMMAND.TURTLE":
                paramList.add(listTurtles.get(activeTurtleIndex-1));
                executeCommand(node, paramList);
                break;
            case "COMMAND.CONTROL":
                paramList.add(this); //maybe should extract out for specific make/set variable commands
                //the class is added to the last value
                executeCommand(node,paramList);
                break;
            case "VARIABLE":
                node.setValue((variables.get(node.getName())).getValue()); 
                break;
            case "CONSTANT":
                break; //Do nothing
            case "BRACKET":
                break; 
            default: //referring to commands that are not TURTLE type
                executeCommand(node,paramList);
        }
    }
    
    

    public List<Turtle> getTurtleList() {
        return listTurtles;
    }

}
