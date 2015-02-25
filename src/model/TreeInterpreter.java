package model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import command.Command;
import parser.CommandTreeNode;

public class TreeInterpreter {
	private Map<String, Integer> parametersMap;
	private CommandFactory factory;
	
	public TreeInterpreter(){
		factory = new CommandFactory();
		parametersMap = createParametersMap();

	}
	/**
	 * Ideally we would get rid of this method since it's duplicate to Parser.
	 * Could make it in the Controller? - Kei
	 * @return
	 */
	private HashMap<String, Integer> createParametersMap() {
		ResourceBundle resources = ResourceBundle.getBundle("parser/parameters");
		Enumeration<String> paramKeys = resources.getKeys();
		HashMap<String, Integer> newMap = new HashMap<>();

		while (paramKeys.hasMoreElements()) {
			String Key = paramKeys.nextElement();
			newMap.put(Key, Integer.parseInt(resources.getString(Key)));
		}
		return newMap;
	}
	
	public void interpretTree(CommandTreeNode root) {
		if (parametersMap.containsKey(root.getType())) { //aka if this string is a command 
			Command c = factory.createCommand(root.getType());
			int numParams = parametersMap.get(root.getType());

			Class[] parameters = new Class[numParams];
			for (int i = 0; i < numParams; i++) {
				parameters[i] = List.class;
			}
			try {
				Method method = c.getClass().getDeclaredMethod("calculateValue", parameters);
				method.invoke(c, root.getChildren()); //for now, pass in the list of children
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(root.getChildren().isEmpty()) { //you are at a leaf node, and that node could have been a command
			return;
		}
		
		
		
	}
}
