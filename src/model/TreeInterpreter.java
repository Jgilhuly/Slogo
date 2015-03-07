package model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import command.Command;
import parser.CommandTreeNode;


public class TreeInterpreter {
    private CommandFactory factory;
    private VariableList variables;
    private Turtle myTurtle;
    
    public TreeInterpreter (VariableList varList, Turtle turtle) {
        variables = varList;
        factory = new CommandFactory();
        myTurtle = turtle;
    }
    public void interpretTree (CommandTreeNode node) {
//        Method method; 
//        if (!(Arrays.asList("VARIABLE", "CONSTANT", "BRACKET").contains(node.getType()))){
//            method = getCommand(node);
//            
//        }
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
		// variables.printThing();
	}
    
    public Constructor[] getConstructors(Class<?> className){
        return className.getDeclaredConstructors();
    }
    
    public void executeCommand(CommandTreeNode node, List<Object> paramList){
      Class<?> c = factory.createCommand(node.getType(), node.getName());
      Constructor<?>[] constructors = getConstructors(c);
//      for(Constructor<?> d : constructors){
//          Parameter[] returntype = d.getParameters();
//          for(Parameter p : returntype){
//              System.out.println(p.toString());
//          }
//      }
      try {
        Command command = (Command) constructors[0].newInstance(20, 30); //testing sum 20 30; haven't type cast paramList yet
    }
    catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        e.printStackTrace();
    }
      Method method = null;
      try {
          method = c.getDeclaredMethod("calculateValue", null);
          try {
            Double value = (Double) method.invoke(null);
            node.setValue(value);
          }catch (IllegalAccessException | InvocationTargetException e) { 
            e.printStackTrace();
          }
      } catch (NoSuchMethodException | SecurityException | IllegalArgumentException e) {
          System.err.print("Error processing Command" + c.getClass().getName());
          e.printStackTrace();
      }
    }

    private boolean isLeaf (CommandTreeNode node){
        return node.getChildren().isEmpty(); 
    }

//    public void executeCommand (CommandTreeNode node, List<Object> paramList) {
//        Command c = factory.createCommand(node.getType(), node.getName());
//        Class[] cArg = new Class[1];
//        cArg[0] = List.class;
//        Method method;
//        try {
//            method = c.getClass().getDeclaredMethod("calculateValue", null);
//            System.out.println(c.getClass().toString());
//            Double value = (Double) method.invoke(c, paramList);
//            node.setValue(value);
//        }
//        catch (NoSuchMethodException | SecurityException | IllegalAccessException
//                | IllegalArgumentException | InvocationTargetException e) {
//            System.err.print("Error processing Command" + c.getClass().getName());
//            e.printStackTrace();
//        }
//    }

    public void update(CommandTreeNode node, List<Object> paramList) {
        switch (node.getType()){
            case "COMMAND.TURTLE":
                paramList.add(myTurtle);
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
    
    

    public VariableList getVariableList () {
        return variables;
    }

}
