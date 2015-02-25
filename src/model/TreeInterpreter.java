package model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import command.Command;
import parser.CommandTreeNode;

public class TreeInterpreter {
	private CommandFactory factory;
	
	public TreeInterpreter(){
		factory = new CommandFactory();

	}
	
	public void interpretTree(CommandTreeNode node){
	    if(node.getChildren().isEmpty()){ //Leaf node is reached
	        helper(node);
	    }else{
	        List<Object> paramList = new ArrayList<>();
	        for(CommandTreeNode child : node.getChildren()){ // can be refactored
	            interpretTree(child);
	            paramList.add(child.getValue());
	        }
	        executeCommand(node, paramList);
	    }
	}
	public void executeCommand(CommandTreeNode node, List<Object> paramList){
	    Command c = null;
	          if(node.getType() == "COMMAND"){
	              c = factory.createCommand(node.getName());
	          }
            Class[] cArg = new Class[1];
            cArg[0] = List.class;
            Method method;
            try {
                method = c.getClass().getDeclaredMethod("calculateValue", cArg);
                Double hello = (Double) method.invoke(c, paramList); 
                node.setValue(hello);
                System.out.println("CURRENT " + hello);
            }
            catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	}
	

	public CommandTreeNode helper(CommandTreeNode node){
	    switch (node.getType()){
	        case "COMMAND":
	            Command c = factory.createCommand(node.getName());
	            break;
	        case "VARIABLE":
	            Variable v = factory.createVariable(node.getName());
	            break;
	        case "BRACKET":
	            Command b = factory.createBracket(node.getName());
	            break;
	        case "CONSTANT":
	            double d = node.getValue();
	            break;       
	    }
	    return node;

	}
	
}
