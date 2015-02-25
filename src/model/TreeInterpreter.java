package model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import command.Command;
import parser.CommandTreeNode;


public class TreeInterpreter {
    private CommandFactory factory;
    private CommandList commands;
    private VariableList variables;
    private Turtle myTurtle;
    
    
    public TreeInterpreter (CommandList c, VariableList v, Turtle turtle) {
        commands = c;
        variables = v;
        factory = new CommandFactory(variables);
    }
    
    public void interpretTree (CommandTreeNode node) {
            List<Object> paramList = new ArrayList<>();
            if (!isLeaf(node)){
                for (CommandTreeNode child : node.getChildren()) { // can be refactored
                    interpretTree(child);
                    paramList.add(child.getValue());
                }
            }
            update(node, paramList);
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
            System.out.println("CURRENT " + value);
        }
        catch (NoSuchMethodException | SecurityException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            System.err.print("Error processing Command" + c.getClass().getName());
            e.printStackTrace();
        }
    }

    public void update(CommandTreeNode node, List<Object> paramList) {
        switch (node.getType()){
            case "COMMAND.TURTLE":
                paramList.add(myTurtle);
                executeCommand(node, paramList);
                break;
            case "COMMAND.CONTROL":
                paramList.add(this);
                executeCommand(node,paramList);
            case "VARIABLE":
                factory.createVariable(node.getName(), variables);
                break;
            case "CONSTANT":
                break; //Do nothing
            default: //referring to commands that are not TURTLE type
                executeCommand(node,paramList);
        }
    }
    
    public VariableList getVariableList (){
        return variables;
    }
    
    public CommandList getCommandList(){
        return commands;
    }

}
