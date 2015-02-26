package model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
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
            List<Object> paramList = new ArrayList<>();
            if (!isLeaf(node)){
                if(!(node.getType().equals("BRACKET"))){
                    for (CommandTreeNode child : node.getChildren()) { // can be refactored
                        interpretTree(child);
                        paramList.add(node.getType().equals("COMMAND.CONTROL") ? child : child.getValue());
                    }
                }
                }
            update(node, paramList);
//            variables.printThing();   
        }

    private boolean isLeaf (CommandTreeNode node){
        return node.getChildren().isEmpty(); 
    }

    public void executeCommand (CommandTreeNode node, List<Object> paramList) {
        Command c = factory.createCommand(node.getType(), node.getName());
        Class[] cArg = new Class[1];
        cArg[0] = List.class;
        Method method;
        try {
            method = c.getClass().getDeclaredMethod("calculateValue", cArg);
            Double value = (Double) method.invoke(c, paramList);
            node.setValue(value);
        }
        catch (NoSuchMethodException | SecurityException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            System.err.print("Error processing Command" + c.getClass().getName());
            e.printStackTrace();
        }
    }
//    public Double getDisplayValue()

    public void update(CommandTreeNode node, List<Object> paramList) {
        switch (node.getType()){
            case "COMMAND.TURTLE":
                paramList.add(myTurtle);
                executeCommand(node, paramList);
                break;
            case "COMMAND.CONTROL":
                paramList.add(this); //maybe should extract out for specific make/set variable commandg
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
