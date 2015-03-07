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
        // Method method;
        // if (!(Arrays.asList("VARIABLE", "CONSTANT", "BRACKET").contains(node.getType()))){
        // method = getCommand(node);
        //
        // }
        List<Object> paramList = new ArrayList<>();
        if (!node.getChildren().isEmpty()) {
            if (!(node.getType().equals("BRACKET"))) {
                for (CommandTreeNode child : node.getChildren()) {
                    interpretTree(child);
                    paramList.add(node.getType().equals("COMMAND.CONTROL") ? child : child
                            .getValue());
                }
            }
        }
        update(node, paramList);
        // variables.printThing();
    }

    public Constructor[] getConstructors (Class<?> className) {
        return className.getDeclaredConstructors();
    }

    public void executeCommand (CommandTreeNode node, List<Object> paramList) {
        Class<?> c = factory.createCommand(node.getType(), node.getName());
        Constructor<?> constructor = getConstructors(c)[0];
        Parameter[] returntypes = constructor.getParameters();
        // for(int i = 0; i < returntypes.length; i++){
        // System.out.println(returntypes[i].toString());
        // }
        Command command = null;
        try {
            command = (Command) constructor.newInstance(paramList.toArray()); // testing sum 20 30;
                                                                              // haven't type cast
                                                                              // paramList yet
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            System.err.print("Illegal Argument present");
            e.printStackTrace();
        }
        Method method = null;
        try {
            method = c.getDeclaredMethod("calculateValue", null);
            try {
                Double value = (Double) method.invoke(command, null);
                node.setValue(value);
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        catch (NoSuchMethodException | SecurityException | IllegalArgumentException e) {
            System.err.print("Error processing Command" + c.getClass().getName());
            e.printStackTrace();
        }
    }

    public void update (CommandTreeNode node, List<Object> paramList) {
        switch (node.getType()) {
            case "COMMAND.TURTLE":
                paramList.add(myTurtle);
                executeCommand(node, paramList);
                break;
            case "COMMAND.CONTROL":
                paramList.add(this); // maybe should extract out for specific make/set variable
                                     // commands
                // the class is added to the last value
                executeCommand(node, paramList);
                break;
            case "VARIABLE":
                node.setValue((variables.get(node.getName())).getValue());
                break;
            case "CONSTANT":
                break; 
            case "BRACKET":
                break;
            default: // referring to commands that are not TURTLE type
                executeCommand(node, paramList);
        }
    }

    public VariableList getVariableList () {
        return variables;
    }

}
